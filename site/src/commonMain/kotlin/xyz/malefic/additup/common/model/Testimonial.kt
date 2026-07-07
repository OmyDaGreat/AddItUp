package xyz.malefic.additup.common.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Testimonial(
    val id: Int,
    val text: String,
    val date: LocalDate,
)
