package me.vzhilin.rasp

enum class OperationType(val code: Int) {
    LOD(1), ADD(2), SUB(3), STO(4), BPA(5), RD(6), PRI(7), HLT(8)
}