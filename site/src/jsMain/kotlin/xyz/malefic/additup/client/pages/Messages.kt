package xyz.malefic.additup.client.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.additup.client.api.ApiState
import xyz.malefic.additup.client.api.body
import xyz.malefic.additup.client.api.get
import xyz.malefic.additup.client.api.produceApiState
import xyz.malefic.additup.common.model.Message

@Page
@Composable
fun MessagesPage() {
    val messages by produceApiState(request = { get("messages").body<List<Message>>() })

    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Column(Modifier.padding(16.px)) {
            when (messages) {
                is ApiState.Loading -> {
                    Text("Loading messages...")
                }

                is ApiState.Error -> {
                    Text("Error: ${(messages as ApiState.Error).issue}")
                }

                emptyList<Message>() -> {
                    Text("No messages")
                }

                else -> {
                    val data = (messages as ApiState.Success<List<Message>>).data
                    Text("Messages from Server (${data.size})")
                    data.forEach { msg ->
                        Column(
                            Modifier.padding(16.px),
                            Arrangement.spacedBy(8.px),
                        ) {
                            P { Text("ID: ${msg.id}") }
                            P(Modifier.padding(left = 8.px).toAttrs()) { Text("Text: ${msg.text}") }
                            P(Modifier.padding(left = 8.px).toAttrs()) { Text("Time: ${msg.timestamp}") }
                        }
                    }
                }
            }
        }
    }
}
