package xyz.malefic.additup.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.malefic.additup.common.model.Issue.Server.Internal
import kotlin.time.Duration

@Serializable
sealed class Issue : Error() {
    abstract override val message: String

    companion object {
        infix fun from(e: Throwable) = e as? Issue ?: Internal(e.stackTraceToString())
    }

    @Serializable
    sealed class Auth : Issue()

    @Serializable
    sealed class Server : Issue() {
        @Serializable
        class Internal(
            @SerialName("cause") val trace: String,
        ) : Server() {
            override val message: String = "Internal server error"
        }

        @Serializable
        class RateLimited(
            @SerialName("retry_after_ms") val retryAfter: Duration,
        ) : Server() {
            override val message = "Rate limited (retry after $retryAfter)"
        }
    }
}
