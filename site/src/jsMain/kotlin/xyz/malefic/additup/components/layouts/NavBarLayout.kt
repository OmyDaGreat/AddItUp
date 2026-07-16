package xyz.malefic.additup.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.additup.styles.ActiveNavItemStyle
import xyz.malefic.additup.styles.HoverableNavItemStyle
import xyz.malefic.additup.styles.isCurrentPage
import xyz.malefic.additup.util.TopLevelPages

@Layout
@Composable
fun NavBarLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val currentRoute = ctx.route.path

    Column(Modifier.fillMaxWidth().height(100.vh)) {
        Box(Modifier.fillMaxWidth().height(60.px).boxShadow(0.px, 0.px, 4.px, 0.1.px), Alignment.Center) {
            Row(Modifier.padding(0.px, 20.px).fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.flexGrow(1).fillMaxHeight(), Alignment.CenterStart) {
                    Image("/logo.jpeg", Modifier.fillMaxHeight().display(DisplayStyle.Block).objectFit(ObjectFit.Contain))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TopLevelPages.entries.forEach { page ->
                        val isActive = page.isCurrentPage(currentRoute)

                        Link(
                            page.route,
                            if (isActive) {
                                ActiveNavItemStyle.toModifier()
                            } else {
                                HoverableNavItemStyle.toModifier()
                            },
                        ) {
                            Text(page.value)
                        }
                    }
                }
            }
        }

        Box(Modifier.fillMaxSize()) {
            content()
        }
    }
}
