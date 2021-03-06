package me.vzhilin.rasp

class Decompiler {
    fun decompile(code: List<Int>): List<Operation> {
        val result = mutableListOf<Operation>()
        val it = code.iterator()
        while (it.hasNext()) {
            val (op, arg) = it.next() to it.next()
            result.add(when(op) {
                OperationType.LOD.code -> Operation(OperationType.LOD, arg)
                OperationType.ADD.code -> Operation(OperationType.ADD, arg)
                OperationType.SUB.code -> Operation(OperationType.SUB, arg)
                OperationType.STO.code -> Operation(OperationType.STO, arg)
                OperationType.BPA.code -> Operation(OperationType.BPA, arg)
                OperationType.RD.code -> Operation(OperationType.RD, arg)
                OperationType.PRI.code -> Operation(OperationType.PRI, arg)
                else -> Operation(OperationType.HLT, 0)
            })
        }
        return result
    }
}