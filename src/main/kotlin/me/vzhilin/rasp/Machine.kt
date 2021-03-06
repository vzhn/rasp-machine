package me.vzhilin.rasp

class Machine(program: List<Int>, private val input: List<Int>) {
    private val registers = mutableMapOf<Int, Int>()
    private val output = mutableListOf<Int>()

    private var inputPos = 0
    private var programCounter = 0
    private var accu = 0

    init {
        program.forEachIndexed { index, registerValue ->
            registers[index] = registerValue
        }
    }

    fun next(): Boolean {
        val ins = registers.getOrDefault(programCounter, 0)
        val arg = registers[programCounter + 1]!!

        when (ins) {
            /* LOD */ OperationType.LOD.code -> accu = arg
            /* ADD */ OperationType.ADD.code -> accu += registers[arg]!!
            /* SUB */ OperationType.SUB.code -> accu -= registers[arg]!!
            /* STO */ OperationType.STO.code -> registers[arg] = accu
            /* BPA */ OperationType.BPA.code -> if (accu > 0) programCounter = arg
            /* RD  */ OperationType.RD.code -> registers[arg] = nextInput()
            /* PRI */ OperationType.PRI.code -> output.add(registers[arg]!!)
            /* HLT */ else -> return false
        }

        if (ins != 5) {
            programCounter += 2
        }
        return true
    }

    fun start(): MutableList<Int> {
        while (true) {
            if (!next()) {
                return output
            }
        }
    }

    private fun nextInput(): Int {
        return input.elementAt(inputPos++)
    }
}