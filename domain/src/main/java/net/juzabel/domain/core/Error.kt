package net.juzabel.domain.core

sealed class Error {
    data class GenericError(val message: String) : Error()
}