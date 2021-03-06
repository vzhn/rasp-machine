package me.vzhilin.rasp

enum class OperationType(val code: Int) {
    LOD(1), ADD(2), SUB(3), STO(4), BPA(5), RD(6), PRI(7), HLT(8);

    companion object {
        fun of(ins: Int) = when(ins) {
            LOD.code -> LOD
            ADD.code -> ADD
            SUB.code -> SUB
            STO.code -> STO
            BPA.code -> BPA
            RD.code -> RD
            PRI.code -> PRI
            else -> HLT
        }
    }
}