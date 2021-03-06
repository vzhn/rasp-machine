package me.vzhilin.rasp

class Compiler {
    fun compile(code: List<Operation>) =
        code.map { it.type.code to it.param }
            .flatMap { listOf(it.first, it.second) }
            .toList()
}