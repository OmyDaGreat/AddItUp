package xyz.malefic.additup.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.additup.styles.ActiveNavItemStyle
import xyz.malefic.additup.styles.AppPalette
import xyz.malefic.additup.styles.HoverableNavItemStyle
import xyz.malefic.additup.styles.isCurrentPage
import xyz.malefic.additup.util.TopLevelPages

@Layout
@Composable
fun NavBarLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    var activeSectionId by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        window.onscroll = {
            val sections =
                TopLevelPages.entries.mapNotNull { page ->
                    window.document.getElementById(page.sectionId)?.let { page.sectionId to it.getBoundingClientRect().top }
                }
            val current = sections.filter { it.second <= 150 }.maxByOrNull { it.second }
            activeSectionId = current?.first ?: ""
        }
    }

    Column(Modifier.fillMaxWidth().minHeight(100.vh)) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(80.px)
                .boxShadow(0.px, 2.px, 10.px, 0.px)
                .position(Position.Sticky)
                .top(0.px)
                .zIndex(100)
                .backgroundColor(if (ColorMode.current.isLight) AppPalette.background.light.color else AppPalette.background.dark.color),
            Alignment.Center,
        ) {
            Row(Modifier.padding(0.px, 40.px).fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Link("/") {
                    Box(Modifier.height(60.px), Alignment.CenterStart) {
                        Image("/logo.jpeg", Modifier.fillMaxHeight().display(DisplayStyle.Block).objectFit(ObjectFit.Contain))
                    }
                }

                Box(Modifier.flexGrow(1))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TopLevelPages.entries.forEach { page ->
                        val isActive = page.isCurrentPage(activeSectionId)

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

                    Box(Modifier.padding(left = 20.px)) {
                        Button(onClick = { ctx.router.navigateTo("/#contact") }) {
                            Text("Get Tutoring")
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
