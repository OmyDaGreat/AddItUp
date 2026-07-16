package xyz.malefic.additup.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun AboutPage() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        P {
            Text("THIS TEMPLATE WAS CREATED BY ")
            Link("https://github.com/OmyDaGreat", "MALEFIC")
            Text("!")
        }
    }
}
