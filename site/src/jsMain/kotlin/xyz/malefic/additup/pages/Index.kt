package xyz.malefic.additup.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.userSelect
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaInstagram
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.icons.ms.MsGroups
import com.varabyte.kobweb.silk.components.icons.ms.MsLocationOn
import com.varabyte.kobweb.silk.components.icons.ms.MsSchedule
import com.varabyte.kobweb.silk.components.icons.ms.MsSchool
import com.varabyte.kobweb.silk.components.icons.ms.MsVolunteerActivism
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.additup.styles.AppPalette

const val PLACEHOLDER_IMAGE =
    "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fas2.ftcdn.net%2Fv2%2Fjpg%2F05%2F97%2F47%2F95%2F1000_F_597479556_7bbQ7t4Z8k3xbAloHFHVdZIizWK1PdOo.jpg&f=1&nofb=1&ipt=79d3925f63155dcf1045f4d84f8d4116794323dadbb6cbcacec9feb9f8ef4a6d"

@Page
@Composable
fun HomePage() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        HeroSection()
        ImpactStatsBar()
        AboutWhySection()
        LocationsSection()
        LeadershipSection()
        FooterSection()
    }
}

@Composable
fun HeroSection() {
    val ctx = rememberPageContext()
    Box(
        Modifier
            .id("about")
            .fillMaxWidth()
            .minHeight(80.vh)
            .padding(topBottom = 100.px, leftRight = 10.percent)
            .backgroundColor(if (ColorMode.current.isLight) AppPalette.background.light.color else AppPalette.background.dark.color)
            .position(Position.Relative)
            .overflow(Overflow.Hidden),
        Alignment.Center,
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .opacity(0.05)
                .position(Position.Absolute)
                .zIndex(0)
                .userSelect(UserSelect.None),
        ) {
            Text("a² + b² = c²   ∑x   πr²   E=mc²   ∫f(x)dx")
        }

        Row(
            Modifier.fillMaxWidth().maxWidth(1200.px).zIndex(1),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Column(Modifier.fillMaxWidth(55.percent)) {
                H1 { Text("Empowering Students, One Equation at a Time.") }
                P(
                    Modifier
                        .fontSize(1.25.cssRem)
                        .margin(top = 20.px, bottom = 40.px)
                        .opacity(0.8)
                        .toAttrs(),
                ) {
                    Text("Completely free, high-quality K-12 math tutoring run by passionate high school leaders.")
                }
                Row(Modifier.gap(20.px)) {
                    Button(onClick = { ctx.router.navigateTo("/#locations") }) {
                        Text("Find a Location Near You")
                    }
                    Button(
                        onClick = { ctx.router.navigateTo("/#contact") },
                        Modifier.backgroundColor(
                            if (ColorMode.current.isLight) AppPalette.secondary.light.color else AppPalette.secondary.dark.color,
                        ),
                    ) {
                        Text("Become a Tutor")
                    }
                }
            }

            Image(
                PLACEHOLDER_IMAGE,
                Modifier.fillMaxWidth(40.percent).borderRadius(20.px).boxShadow(0.px, 10.px, 30.px, 0.px),
            )
        }
    }
}

@Composable
fun ImpactStatsBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .backgroundColor(if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color)
            .color(if (ColorMode.current.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color)
            .padding(topBottom = 30.px, leftRight = 10.percent)
            .gap(40.px),
        Arrangement.Center,
        Alignment.CenterVertically,
    ) {
        StatItem("5+", "Locations")
        StatItem("500+", "Students Helped")
        StatItem("80+", "Tutors")
        StatItem("100% Free", "501(c)(3)")
    }
}

@Composable
fun StatItem(
    number: String,
    label: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        H3(Modifier.margin(0.px).toAttrs()) { Text(number) }
        P(
            Modifier
                .fontSize(0.9.cssRem)
                .margin(0.px)
                .opacity(0.9)
                .toAttrs(),
        ) { Text(label) }
    }
}

@Composable
fun AboutWhySection() {
    SimpleGrid(
        numColumns(1, md = 2),
        Modifier
            .fillMaxWidth()
            .maxWidth(1200.px)
            .margin(left = 20.px, right = 20.px)
            .padding(topBottom = 100.px, leftRight = 40.px)
            .gap(60.px),
    ) {
        Column {
            H2 { Text("Who We Are") }
            P(Modifier.margin(top = 20.px).toAttrs()) {
                Text(
                    "Add It Up is a nationally registered 501(c)(3) non-profit math tutoring organization offering academic support for scholars in grades K-12.",
                )
            }
            P(
                Modifier
                    .margin(top = 20.px)
                    .fontWeight(FontWeight.Bold)
                    .fontStyle(FontStyle.Italic)
                    .toAttrs(),
            ) {
                Text(
                    "\"Completely free of charge, we provide opportunities for students to both obtain the support they need and help their peers do the same!\"",
                )
            }
        }

        Column(Modifier.id("why-choose-us")) {
            H2 { Text("Why Parents & Students Love Us") }
            Column(Modifier.margin(top = 20.px).gap(15.px)) {
                WhyItem(
                    { MsGroups(it) },
                    "Student-to-Peer Connection",
                    "Tutors are highly motivated high schoolers who understand how to make math engaging and accessible.",
                )
                WhyItem(
                    { MsVolunteerActivism(it) },
                    "Completely Free",
                    "Absolutely no financial barrier to high-quality academic support.",
                )
                WhyItem(
                    { MsSchool(it) },
                    "Structured Facilities",
                    "Hosted in safe, quiet local libraries and schools.",
                )
            }
        }
    }
}

@Composable
fun WhyItem(
    icon: @Composable (Modifier) -> Unit,
    title: String,
    description: String,
) {
    Row(Modifier.gap(15.px)) {
        Box(Modifier.margin(top = 5.px)) {
            icon(
                Modifier
                    .fontSize(
                        24.px,
                    ).color(if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color),
            )
        }
        Column {
            H3(Modifier.fontSize(1.2.cssRem).margin(0.px).toAttrs()) { Text(title) }
            P(Modifier.fontSize(0.95.cssRem).opacity(0.8).toAttrs()) { Text(description) }
        }
    }
}

data class Location(
    val name: String,
    val address: String,
    val schedule: String,
    val city: String,
)

val locationsList =
    listOf(
        Location("Valencia Park Elementary", "3441 W Valencia Dr, Fullerton, CA 92833", "Every Thursday, 4:00 – 5:00 PM", "Fullerton"),
        Location("Euclid Library", "1340 S Euclid St, Anaheim, CA 92802", "Every Monday, 4:30 – 5:30 PM", "Anaheim"),
        Location("Haskett Library", "2650 W Broadway, Anaheim, CA 92804", "Every Monday, 4:30 – 5:30 PM", "Anaheim"),
        Location("La Mirada Library", "13800 La Mirada Blvd, La Mirada, CA 90638", "Every Saturday, 12:00 – 1:30 PM", "La Mirada"),
        Location("Oxford Academy Library", "5172 Orange Ave, Cypress, CA 90630", "Every Monday, 8:15 – 9:15 AM", "Cypress"),
    )

@Composable
fun LocationsSection() {
    var filter by remember { mutableStateOf("") }
    val cities = locationsList.map { it.city }.distinct()

    Column(
        Modifier
            .id("locations")
            .fillMaxWidth()
            .backgroundColor(if (ColorMode.current.isLight) AppPalette.surfaceVariant.light.color else AppPalette.surfaceVariant.dark.color)
            .padding(topBottom = 100.px, leftRight = 40.px),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        H2 { Text("Find a Tutoring Session") }

        Row(Modifier.margin(top = 40.px).gap(10.px).flexWrap(FlexWrap.Wrap), horizontalArrangement = Arrangement.Center) {
            Button(
                { filter = "" },
                Modifier.backgroundColor(
                    if (filter == "") {
                        (if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color)
                    } else {
                        (if (ColorMode.current.isLight) AppPalette.secondary.light.color else AppPalette.secondary.dark.color)
                    },
                ),
            ) { Text("All") }
            cities.forEach { city ->
                Button(
                    { filter = city },
                    Modifier.backgroundColor(
                        if (filter == city) {
                            (if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color)
                        } else {
                            (if (ColorMode.current.isLight) AppPalette.secondary.light.color else AppPalette.secondary.dark.color)
                        },
                    ),
                ) { Text(city) }
            }
        }

        SimpleGrid(
            numColumns(1, md = 2, lg = 3),
            Modifier.maxWidth(1200.px).margin(top = 40.px).gap(20.px),
        ) {
            locationsList.filter { filter == "" || it.city == filter }.forEach { loc ->
                LocationCard(loc)
            }
        }
    }
}

@Composable
fun LocationCard(loc: Location) {
    Column(
        Modifier
            .padding(25.px)
            .backgroundColor(if (ColorMode.current.isLight) AppPalette.surface.light.color else AppPalette.surface.dark.color)
            .borderRadius(15.px)
            .boxShadow(0.px, 4.px, 15.px, 0.px, Color.rgba(0, 0, 0, 0.05f))
            .transition(Transition.all(0.3.s)),
    ) {
        H3(Modifier.margin(bottom = 15.px).toAttrs()) { Text("📍 ${loc.name}") }
        Row(Modifier.gap(10.px).margin(bottom = 10.px)) {
            MsLocationOn(Modifier.color(if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color))
            P(Modifier.fontSize(0.9.cssRem).toAttrs()) { Text(loc.address) }
        }
        Row(Modifier.gap(10.px)) {
            MsSchedule(Modifier.color(if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color))
            P(Modifier.fontSize(0.9.cssRem).toAttrs()) { Text(loc.schedule) }
        }
    }
}

@Composable
fun LeadershipSection() {
    Column(
        Modifier
            .id("team")
            .fillMaxWidth()
            .maxWidth(1200.px)
            .margin(left = 20.px, right = 20.px)
            .padding(topBottom = 100.px, leftRight = 40.px),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        H2 { Text("Leadership Team") }

        SimpleGrid(numColumns(1, md = 2), Modifier.margin(top = 60.px).gap(60.px)) {
            LeaderCard(
                "Shriya Gupta",
                "Co-President",
                "Junior at Oxford Academy",
                "Working to build confidence and motivation in students.",
            )
            LeaderCard(
                "Zara Shah",
                "Co-President",
                "Senior at Sunny Hills High",
                "Working to build confidence and motivation in students.",
            )
        }
    }
}

@Composable
fun LeaderCard(
    name: String,
    title: String,
    grade: String,
    quote: String,
    image: String? = null,
) {
    Row(Modifier.gap(30.px), verticalAlignment = Alignment.CenterVertically) {
        Image(
            image ?: PLACEHOLDER_IMAGE,
            Modifier.size(150.px).borderRadius(100.percent).objectFit(ObjectFit.Cover),
        )
        Column {
            H3(Modifier.margin(0.px).toAttrs()) { Text(name) }
            P(
                Modifier
                    .fontWeight(FontWeight.Bold)
                    .color(if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color)
                    .toAttrs(),
            ) {
                Text(title)
            }
            P(Modifier.fontSize(0.9.cssRem).fontStyle(FontStyle.Italic).toAttrs()) { Text(grade) }
            P(Modifier.margin(top = 10.px).opacity(0.8).toAttrs()) { Text("\"$quote\"") }
        }
    }
}

@Composable
fun FooterSection() {
    Column(
        Modifier
            .id("contact")
            .fillMaxWidth()
            .backgroundColor(if (ColorMode.current.isLight) AppPalette.primary.light.color else AppPalette.primary.dark.color)
            .color(if (ColorMode.current.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color)
            .padding(top = 80.px, bottom = 40.px, leftRight = 10.percent),
    ) {
        SimpleGrid(numColumns(1, md = 3), Modifier.fillMaxWidth().gap(40.px)) {
            Column {
                H3(Modifier.margin(bottom = 20.px).toAttrs()) { Text("Add It Up") }
                P(Modifier.opacity(0.8).toAttrs()) {
                    Text("Empowering students and building academic confidence. Registered 501(c)(3) nonprofit.")
                }
            }

            Column {
                H3(Modifier.margin(bottom = 20.px).toAttrs()) { Text("Quick Links") }
                Column(Modifier.gap(10.px)) {
                    Link(
                        "/#about",
                        Modifier.color(
                            if (ColorMode.current.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color,
                        ),
                    ) {
                        Text("About")
                    }
                    Link(
                        "/#locations",
                        Modifier.color(
                            if (ColorMode.current.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color,
                        ),
                    ) {
                        Text("Locations")
                    }
                    Link(
                        "/#contact",
                        Modifier.color(
                            if (ColorMode.current.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color,
                        ),
                    ) {
                        Text("Join as a Tutor")
                    }
                }
            }

            Column {
                H3(Modifier.margin(bottom = 20.px).toAttrs()) { Text("Contact") }
                P(Modifier.margin(bottom = 10.px).toAttrs()) { Text("📧 additupinsta@gmail.com") }
                P(Modifier.margin(bottom = 20.px).toAttrs()) { Text("📧 shriyag054@gmail.com") }
                Row(Modifier.gap(15.px)) {
                    SocialIcon({ m, s -> FaInstagram(m, s) }, "https://www.instagram.com/additup_official/")
                    SocialIcon({ m, s -> FaInstagram(m, s) }, "https://www.instagram.com/shhsadditup/")
                    SocialIcon({ m, s -> FaInstagram(m, s) }, "https://www.instagram.com/additup_tutoring/")
                }
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .height(1.px)
                .backgroundColor(if (ColorMode.current.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color)
                .opacity(0.2)
                .margin(topBottom = 40.px),
        )

        P(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fontSize(0.8.cssRem)
                .opacity(0.6)
                .toAttrs(),
        ) {
            Text("© 2026 Add It Up. All rights reserved.")
        }
    }
}

@Composable
fun SocialIcon(
    icon: @Composable (Modifier, IconSize) -> Unit,
    url: String,
) {
    Link(url) {
        icon(
            Modifier
                .fontSize(24.px)
                .color(if (ColorMode.current.isLight) AppPalette.onPrimary.light.color else AppPalette.onPrimary.dark.color),
            IconSize.LG,
        )
    }
}
