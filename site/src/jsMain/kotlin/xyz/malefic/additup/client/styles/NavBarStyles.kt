package xyz.malefic.additup.client.styles

import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.translateY
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.textDecoration
import xyz.malefic.additup.client.styles.ThemeColor.OnBackground
import xyz.malefic.additup.client.styles.ThemeColor.OnSecondaryContainer
import xyz.malefic.additup.client.styles.ThemeColor.Primary
import xyz.malefic.additup.client.styles.ThemeColor.SecondaryContainer
import xyz.malefic.additup.client.util.TopLevelPages

val BaseNavItemStyle =
    CssStyle.base {
        Modifier
            .padding(12.px, 20.px)
            .margin(0.px, 4.px)
            .borderRadius(6.px)
            .styleModifier { textDecoration("none") }
            .color(Color of OnBackground)
            .fontSize(14.px)
            .fontWeight(500)
            .transition(Transition.all(0.2.s))
            .whiteSpace(WhiteSpace.NoWrap)
    }

val HoverNavItemStyle =
    BaseNavItemStyle.extendedBy {
        hover {
            Modifier
                .background(Color of SecondaryContainer)
                .color(Color of OnSecondaryContainer)
                .translateY((-1).px)
        }
    }

val ActiveNavItemStyle =
    BaseNavItemStyle.extendedByBase {
        Modifier
            .background(Color of SecondaryContainer)
            .color(Color of Primary)
            .fontWeight(600)
    }

fun TopLevelPages.isCurrentPage(currentRoute: String): Boolean =
    when (this) {
        TopLevelPages.INDEX -> currentRoute == "" || currentRoute == "/"
        else -> currentRoute == route
    }
