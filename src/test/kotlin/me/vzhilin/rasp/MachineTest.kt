package me.vzhilin.rasp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MachineTest {
    @Test
    fun testSum() = check(
        // 11 + 12 = 23
        """
            RD -1
            RD -2
            ADD -1
            ADD -2
            STO -1
            PRI -1
            HLT
        """.trimIndent(), listOf(11, 12), listOf(23)
    )

    private fun check(program: String, input: List<Int>, expected: List<Int>) {
        val code = Compiler().compile(Parser(program).parse())
        Assertions.assertEquals(expected, Machine(code, input).start())
    }
}