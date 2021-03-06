package me.vzhilin.rasp

class Machine(
    program: List<Int>,
    private val input: List<Int>) {

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
            /* LOD */ 1 -> accu = arg
            /* ADD */ 2 -> accu += registers[arg]!!
            /* SUB */ 3 -> accu -= registers[arg]!!
            /* STO */ 4 -> registers[arg] = accu
            /* BPA */ 5 -> if (accu > 0) programCounter = arg
            /* RD  */ 6 -> registers[arg] = nextInput()
            /* PRI */ 7 -> output.add(registers[arg]!!)
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