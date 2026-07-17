package xyz.malefic.additup.util

enum class TopLevelPages(
    val value: String,
    val route: String,
    val sectionId: String,
) {
    ABOUT_US("About Us", "/#about", "about"),
    WHY_CHOOSE_US("Why Choose Us", "/#why-choose-us", "why-choose-us"),
    LOCATIONS("Locations & Schedule", "/#locations", "locations"),
    TEAM("Our Team", "/#team", "team"),
    CONTACT("Contact", "/#contact", "contact"),
}
