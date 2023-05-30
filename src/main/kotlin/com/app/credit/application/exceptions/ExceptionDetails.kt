package com.app.credit.application.exceptions

data class ExceptionDetails(
        val title: String,
        val timestamp: LocalDateTime,
        val status: Int,
        val exception: String,
        val details: MutableMap<String, String?>
)