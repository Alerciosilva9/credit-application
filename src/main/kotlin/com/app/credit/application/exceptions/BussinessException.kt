package com.app.credit.application.exceptions

data class BussinessException(override val message: String?) : RuntimeException(message)