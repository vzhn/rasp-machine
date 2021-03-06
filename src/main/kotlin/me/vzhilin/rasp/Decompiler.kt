package me.vzhilin.rasp

class Decompiler {
    fun decompile(code: List<Int>): List<Operation> {
        val result = mutableListOf<Operation>()
        val it = code.iterator()
        while (it.hasNext()) {
            val (op, arg) = it.next() to it.next()
            result.add(Operation(OperationType.of(op), arg))
        }
        return result
    }
}