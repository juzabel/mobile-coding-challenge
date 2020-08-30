package net.juzabel.domain.core

interface UseCase<Param, Value> {
     suspend fun invoke(params: Param) : Value
}