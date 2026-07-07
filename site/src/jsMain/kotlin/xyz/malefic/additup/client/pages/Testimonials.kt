package xyz.malefic.additup.client.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.additup.client.api.ApiState
import xyz.malefic.additup.client.api.body
import xyz.malefic.additup.client.api.get
import xyz.malefic.additup.client.api.produceApiState
import xyz.malefic.additup.common.model.Testimonial

@Page
@Composable
fun Testimonials() {
    val testimonials by produceApiState(request = { get("testimonials").body<List<Testimonial>>() })

    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Column(Modifier.padding(16.px)) {
            when (testimonials) {
                is ApiState.Loading -> {
                    Text("Loading testimonials...")
                }

                is ApiState.Error -> {
                    Text("Error: ${(testimonials as ApiState.Error).issue}")
                }

                else -> {
                    val data = (testimonials as ApiState.Success<List<Testimonial>>).data
                    if (data == emptyList<Testimonial>()) {
                        return@Column Text("No messages")
                    }
                    Text("Testimonials from Server (${data.size})")
                    data.forEach { msg ->
                        Column(
                            Modifier.padding(16.px).borderRadius(8.px),
                            Arrangement.spacedBy(8.px),
                        ) {
                            H3 { Text(msg.text) }
                            P { Text(msg.date.toString()) }
                        }
                    }
                }
            }
        }
    }
}
