package me.vzhilin.rasp

class Parser(program: String) {
    private val chars = program.toCharArray()
    private var pos = 0

    fun parse(): List<Operation> {
        val instructions = mutableListOf<Operation>()

        do {
            instructions.add(nextInstruction())
            if (hasNext()) {
                readNewLine()
            }
        } while (hasNext())
        return instructions;
    }

    private fun nextInstruction(): Operation {
        val type: OperationType = when(current()) {
            'L' -> {
                read("LOD")
                OperationType.LOD
            }
            'A' -> {
                read("ADD")
                OperationType.ADD
            }
            'S' -> {
                read('S')
                when(current()) {
                    'U' -> {
                        read("UB")
                        OperationType.SUB
                    }
                    'T' -> {
                        read("TO")
                        OperationType.STO
                    }
                    else -> error("expected: U | T")
                }
            }
            'B' -> {
                read("BPA")
                OperationType.BPA
            }
            'R' -> {
                read("RD")
                OperationType.RD
            }
            'P' -> {
                read("PRI")
                OperationType.PRI
            }
            'H' -> {
                read("HLT")
                OperationType.HLT
            }
            else -> error("expected: LOD | ADD | SUB | STO | BPA | RD | PRI | HLT, got \"${current()}\"")
        }
        return if (type != OperationType.HLT) {
            if (hasNext() && current() == ',') {
                read(',')
            }
            readSpaces()
            val arg = readInt()
            Operation(type, arg)
        } else {
            Operation(OperationType.HLT, 0)
        }
    }

    private fun read(expected: Char) {
        if (current() != expected) {
            error("expected: \"$expected\", got: \"${current()}\"")
        }
        next()
    }

    private fun read(expected: String) = expected.forEach { read(it) }
    private fun current() = chars[pos]
    private fun hasNext() = pos <= chars.lastIndex
    private fun next() = ++pos

    private fun readSpaces() {
        while (current() in listOf(' ', '\t', '\r')) {
            next()
        }
    }

    private fun readNewLine() {
        do {
            read('\n')
        } while (hasNext() && current() == '\n')
    }

    private fun readInt(): Int {
        var neg = false
        if (current() == '-') {
            read('-')
            neg = true
        }

        fun matches(ch: Char) = ch in arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        if (!matches(current())) {
            error("expected: - | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9")
        }

        var accu = 0
        while (hasNext() && matches(current())) {
            accu = accu * 10 + (current() - '0')
            next()
        }
        if (neg) {
            accu = -accu
        }
        return accu
    }
}