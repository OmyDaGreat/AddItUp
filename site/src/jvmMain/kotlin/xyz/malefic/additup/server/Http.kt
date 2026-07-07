package xyz.malefic.additup.server

import kotlinx.datetime.LocalDate
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.HttpHandler
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Method.PUT
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.AllowAllOriginPolicy
import org.http4k.filter.CorsPolicy
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import xyz.malefic.additup.common.json
import xyz.malefic.additup.common.model.Testimonial
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

val corsPolicy =
    CorsPolicy(
        headers = listOf("Content-Type"),
        methods = listOf(GET, POST, PUT, DELETE),
        originPolicy = AllowAllOriginPolicy,
    )

private val staticRoots =
    listOf(
        Paths.get("build", "dist", "js", "productionExecutable"),
        Paths.get("site", "build", "dist", "js", "productionExecutable"),
        Paths.get(".kobweb", "site"),
        Paths.get("site", ".kobweb", "site"),
        Paths.get("/app", "site", "build", "dist", "js", "productionExecutable"),
        Paths.get("/app", "site", ".kobweb", "site"),
    )

private fun isAssetRequest(requestPath: String): Boolean =
    requestPath
        .substringAfterLast('/')
        .contains('.')

private fun contentTypeFor(fileName: String): String =
    when (fileName.substringAfterLast('.', "").lowercase()) {
        "html" -> "text/html; charset=utf-8"
        "js" -> "application/javascript; charset=utf-8"
        "css" -> "text/css; charset=utf-8"
        "json" -> "application/json; charset=utf-8"
        "svg" -> "image/svg+xml"
        "ico" -> "image/x-icon"
        else -> "text/plain; charset=utf-8"
    }

private fun candidatePaths(requestPath: String): List<String> {
    val normalized = requestPath.trim('/').trim()

    return if (normalized.isEmpty()) {
        listOf("index.html")
    } else {
        listOf(
            normalized,
            "$normalized.html",
            "$normalized/index.html",
        )
    }
}

private fun findStaticFile(requestPath: String): Path? {
    val candidates = candidatePaths(requestPath)

    for (root in staticRoots) {
        for (candidate in candidates) {
            val direct = root.resolve(candidate).normalize()
            if (Files.exists(direct) && Files.isRegularFile(direct)) {
                return direct
            }

            val publicFile = root.resolve("public").resolve(candidate).normalize()
            if (Files.exists(publicFile) && Files.isRegularFile(publicFile)) {
                return publicFile
            }
        }
    }

    return null
}

private fun serveFile(filePath: Path): Response =
    try {
        val content = Files.readAllBytes(filePath)
        Response(OK)
            .header("Content-Type", contentTypeFor(filePath.fileName.toString()))
            .body(String(content, Charsets.UTF_8))
    } catch (e: Exception) {
        Response(NOT_FOUND).body(e.toString())
    }

private fun serveStaticFile(req: Request): Response {
    val requestPath = req.uri.path.removePrefix("/")
    findStaticFile(requestPath)?.let { return serveFile(it) }

    if (requestPath.isNotBlank() && !isAssetRequest(requestPath)) {
        findStaticFile("")?.let { return serveFile(it) }
    }

    return Response(NOT_FOUND)
}

val apiRoutes: RoutingHttpHandler =
    routes(
        "/api/ping" bind GET to { Response(OK).body("pong") },
        "/api/health" bind GET to { Response(OK).body("healthy") },
        "/api/testimonials" bind GET to {
            val testimonials =
                listOf(
                    Testimonial(1, "Hello from the server!", LocalDate(2026, 1, 1)),
                    Testimonial(2, "This data is shared via commonMain!", LocalDate(2025, 9, 28)),
                    Testimonial(3, "Fetched via JSON API", LocalDate(2026, 5, 3)),
                )
            Response(OK)
                .header("Content-Type", APPLICATION_JSON.value)
                .body(json.encodeToString(testimonials))
        },
    )

val http: HttpHandler =
    object : HttpHandler {
        override fun invoke(req: Request): Response =
            if (req.uri.path.startsWith("/api/")) {
                try {
                    apiRoutes(req)
                } catch (e: Exception) {
                    Response(NOT_FOUND).body(e.toString())
                }
            } else {
                serveStaticFile(req)
            }
    }
