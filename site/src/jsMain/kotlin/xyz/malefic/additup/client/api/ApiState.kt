package xyz.malefic.additup.client.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import arrow.core.raise.Raise
import arrow.core.raise.either
import com.varabyte.kobweb.browser.uri.encodeURIComponent
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.PageContext
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.additup.client.components.Spinner
import xyz.malefic.additup.common.model.Issue

sealed interface ApiState<out T> {
    data object Loading : ApiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : ApiState<T>

    data class Error(
        val issue: Issue,
    ) : ApiState<Nothing>
}

@Composable
fun <T> produceApiState(
    vararg keys: Any?,
    request: suspend context(Raise<Issue>) () -> T,
) = produceState<ApiState<T>>(ApiState.Loading, *keys) {
    value = ApiState.Loading
    either { request() }.fold(
        { value = ApiState.Error(it) },
        { value = ApiState.Success(it) },
    )
}

@Composable
fun <T> PageContext.Request(
    vararg keys: Any?,
    request: suspend context(Raise<Issue>) () -> T,
    content: @Composable (T) -> Unit,
) {
    val state by produceApiState(*keys) { request() }
    when (state) {
        is ApiState.Loading -> {
            Spinner()
        }

        is ApiState.Error -> {
            when (val error = (state as ApiState.Error).issue) {
                is Issue.Auth -> {
                    router.navigateTo("/login?redirect=${encodeURIComponent(route.path)}")
                }

                is Issue.Server.RateLimited -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Text("Too many requests. Please wait ${error.retryAfter}.")
                    }
                }

                else -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Text("Error: ${error.message}")
                    }
                }
            }
        }

        is ApiState.Success -> {
            content((state as ApiState.Success<T>).data)
        }
    }
}
