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

        when (OperationType.of(ins)) {
            OperationType.LOD-> accu = arg
            OperationType.ADD -> accu += registers[arg]!!
            OperationType.SUB -> accu -= registers[arg]!!
            OperationType.STO -> registers[arg] = accu
            OperationType.BPA -> if (accu > 0) programCounter = arg
            OperationType.RD -> registers[arg] = nextInput()
            OperationType.PRI -> output.add(registers[arg]!!)
            OperationType.HLT -> return false
        }

        if (ins != OperationType.BPA.code) {
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