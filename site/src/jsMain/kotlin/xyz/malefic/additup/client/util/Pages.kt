package xyz.malefic.additup.client.util

enum class Pages(
    val value: String,
    val route: String,
) {
    INDEX("Index", "/"),
    ABOUT("About", "/about"),
    MESSAGES("Messages", "/messages"),
}
