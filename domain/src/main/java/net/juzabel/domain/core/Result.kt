package net.juzabel.domain.core

sealed class Result<Val> {
    data class Ok<Val>(val value : Val) : Result<Val>()
    data class Err<Val>(val err: Error) : Result<Val>()
}