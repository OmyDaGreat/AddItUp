package xyz.malefic.additup.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.rgba
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.navigation.LinkVars
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssStyleScopeBase
import com.varabyte.kobweb.silk.style.vars.color.BackgroundColorVar
import com.varabyte.kobweb.silk.style.vars.color.BorderColorVar
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.style.vars.color.FocusOutlineColorVar
import com.varabyte.kobweb.silk.style.vars.color.PlaceholderColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.cssClass
import com.varabyte.kobweb.silk.theme.colors.loadFromLocalStorage
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.border
import com.varabyte.kobweb.silk.theme.colors.palette.button
import com.varabyte.kobweb.silk.theme.colors.palette.checkbox
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.focusOutline
import com.varabyte.kobweb.silk.theme.colors.palette.input
import com.varabyte.kobweb.silk.theme.colors.palette.link
import com.varabyte.kobweb.silk.theme.colors.palette.overlay
import com.varabyte.kobweb.silk.theme.colors.palette.placeholder
import com.varabyte.kobweb.silk.theme.colors.palette.switch
import com.varabyte.kobweb.silk.theme.colors.palette.tab
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobweb.silk.theme.colors.palette.tooltip
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import xyz.malefic.kutint.AdaptiveColor
import xyz.malefic.kutint.BasePalette
import xyz.malefic.kutint.color
import xyz.malefic.kutint.darkTransform
import xyz.malefic.kutint.parseHex

val floralWhite = parseHex("#F8F5EA")
val paleSky = parseHex("#C6DAEC")
val babyBlueIce = parseHex("#94BBEB")
val prussianBlue = parseHex("#0D2645")
val carbonBlack = parseHex("#1F1F1F")

open class AddItUpPalette : BasePalette() {
    var primary by color(prussianBlue darkTransform { it.lighten(0.4f) })
    var onPrimary by color { primary map { it.contrast() } }
    var primaryContainer by color(prussianBlue.lighten(0.6f) darkTransform { it.darken(0.2f) })
    var onPrimaryContainer by color { primaryContainer map { it.contrast() } }

    var secondary by color(paleSky darkTransform { it.lighten(0.2f) })
    var onSecondary by color { secondary map { it.contrast() } }
    var secondaryContainer by color(paleSky.lighten(0.2f) darkTransform { it.darken(0.2f) })
    var onSecondaryContainer by color { secondaryContainer map { it.contrast() } }

    var tertiary by color(babyBlueIce darkTransform { it.lighten(0.2f) })
    var onTertiary by color { tertiary map { it.contrast() } }
    var tertiaryContainer by color(babyBlueIce.lighten(0.2f) darkTransform { it.darken(0.2f) })
    var onTertiaryContainer by color { tertiaryContainer map { it.contrast() } }

    var background by color(floralWhite, carbonBlack)
    var onBackground by color { background map { it.contrast() } }
    var surface by color(floralWhite, carbonBlack)
    var onSurface by color { surface map { it.contrast() } }

    var surfaceVariant by color(floralWhite.darken(0.05f), carbonBlack.lighten(0.1f))
    var onSurfaceVariant by color { surfaceVariant map { it.contrast() } }

    var outline by color(paleSky.darken(0.2f), paleSky.lighten(0.2f))
    var outlineVariant by color(paleSky.lighten(0.1f), paleSky.darken(0.1f))

    var error by color(parseHex("#BA1A1A"), parseHex("#FFB4AB"))
    var onError by color { error map { it.contrast() } }
}

val AppPalette = AddItUpPalette()

fun CssStyleScopeBase.of(adaptive: AdaptiveColor): Color = adaptive.run { this@of.value() }.color

@InitSilk
fun initColor(ctx: InitSilkContext) {
    ctx.config.initialColorMode = ColorMode.loadFromLocalStorage() ?: ColorMode.systemPreference

    ctx.theme.palettes.light.apply {
        background = AppPalette.background.light.color
        color = AppPalette.onBackground.light.color
        border = AppPalette.outlineVariant.light.color
        focusOutline = AppPalette.primary.light.color
        placeholder = AppPalette.onSurfaceVariant.light.color
        overlay = rgba(0, 0, 0, 0.5f)

        input.set(
            hoveredBorder = AppPalette.primary.light.color,
            invalidBorder = AppPalette.error.light.color,
            filled = AppPalette.surfaceVariant.light.color,
            filledHover =
                AppPalette.surfaceVariant.light.color
                    .darkened(0.05f),
            filledFocus = AppPalette.primary.light.color,
        )
        button.set(
            default = AppPalette.primary.light.color,
            hover =
                AppPalette.primary.light.color
                    .darkened(0.1f),
            focus = AppPalette.primary.light.color,
            pressed =
                AppPalette.primary.light.color
                    .darkened(0.2f),
        )
        checkbox.set(
            background = AppPalette.primary.light.color,
            hover =
                AppPalette.primary.light.color
                    .darkened(0.1f),
            color = AppPalette.onPrimary.light.color,
        )
        switch.set(
            backgroundOn = AppPalette.primary.light.color,
            backgroundOff = AppPalette.surfaceVariant.light.color,
            thumb = AppPalette.onPrimary.light.color,
        )
        tab.set(
            color = AppPalette.onSurfaceVariant.light.color,
            background = AppPalette.surface.light.color,
            selectedColor = AppPalette.primary.light.color,
            hover = AppPalette.surfaceVariant.light.color,
            pressed =
                AppPalette.surfaceVariant.light.color
                    .darkened(0.1f),
            disabled = AppPalette.outlineVariant.light.color,
        )
        tooltip.set(
            background = AppPalette.onSurface.light.color, // Approximate inverseSurface
            color = AppPalette.surface.light.color, // Approximate inverseOnSurface
        )
        link.set(
            default = AppPalette.primary.light.color,
            visited =
                AppPalette.primary.light.color
                    .darkened(0.1f),
        )
    }

    ctx.theme.palettes.dark.apply {
        background = AppPalette.background.dark.color
        color = AppPalette.onBackground.dark.color
        border = AppPalette.outlineVariant.dark.color
        focusOutline = AppPalette.primary.dark.color
        placeholder = AppPalette.onSurfaceVariant.dark.color
        overlay = rgba(0, 0, 0, 0.5f)

        input.set(
            hoveredBorder = AppPalette.primary.dark.color,
            invalidBorder = AppPalette.error.dark.color,
            filled = AppPalette.surfaceVariant.dark.color,
            filledHover =
                AppPalette.surfaceVariant.dark.color
                    .darkened(0.05f),
            filledFocus = AppPalette.primary.dark.color,
        )
        button.set(
            default = AppPalette.primary.dark.color,
            hover =
                AppPalette.primary.dark.color
                    .darkened(0.1f),
            focus = AppPalette.primary.dark.color,
            pressed =
                AppPalette.primary.dark.color
                    .darkened(0.2f),
        )
        checkbox.set(
            background = AppPalette.primary.dark.color,
            hover =
                AppPalette.primary.dark.color
                    .darkened(0.1f),
            color = AppPalette.onPrimary.dark.color,
        )
        switch.set(
            backgroundOn = AppPalette.primary.dark.color,
            backgroundOff = AppPalette.surfaceVariant.dark.color,
            thumb = AppPalette.onPrimary.dark.color,
        )
        tab.set(
            color = AppPalette.onSurfaceVariant.dark.color,
            background = AppPalette.surface.dark.color,
            selectedColor = AppPalette.primary.dark.color,
            hover = AppPalette.surfaceVariant.dark.color,
            pressed =
                AppPalette.surfaceVariant.dark.color
                    .darkened(0.1f),
            disabled = AppPalette.outlineVariant.dark.color,
        )
        tooltip.set(
            background = AppPalette.onSurface.dark.color,
            color = AppPalette.surface.dark.color,
        )
        link.set(
            default = AppPalette.primary.dark.color,
            visited =
                AppPalette.primary.dark.color
                    .darkened(0.1f),
        )
    }

    ColorMode.entries.forEach { colorMode ->
        ctx.stylesheet.registerStyleBase(".${colorMode.cssClass}") {
            val palette = colorMode.toPalette()
            Modifier
                .setVariable(ButtonVars.Color, if (colorMode.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color)
                .setVariable(BackgroundColorVar, palette.background)
                .setVariable(ColorVar, palette.color)
                .setVariable(BorderColorVar, palette.border)
                .setVariable(FocusOutlineColorVar, palette.focusOutline)
                .setVariable(PlaceholderColorVar, palette.placeholder)
                .setVariable(LinkVars.DefaultColor, palette.link.default)
                .setVariable(LinkVars.VisitedColor, palette.link.visited)
        }
    }
}
