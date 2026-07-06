package xyz.malefic.additup.client.api

import arrow.core.raise.context.Raise
import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.browser.http.bodyOf
import kotlinx.browser.window
import kotlinx.coroutines.await
import xyz.malefic.additup.client.util.extensions.catching
import xyz.malefic.additup.common.json
import xyz.malefic.additup.common.model.Issue

context(_: Raise<Issue>)
suspend fun get(path: String) =
    catching {
        window.api
            .get(path)
            .text()
            .await()
    }

context(_: Raise<Issue>)
suspend fun post(path: String) =
    catching {
        window.api
            .post(path)
            .text()
            .await()
    }

context(_: Raise<Issue>)
suspend inline fun <reified T> post(
    path: String,
    body: T,
) = catching {
    window.api
        .post(path, bodyOf(json.encodeToString(body), "application/json"))
        .text()
        .await()
}

context(_: Raise<Issue>)
inline fun <reified T> String.body() = catching { json.decodeFromString<T>(this) }
