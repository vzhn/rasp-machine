package me.vzhilin.rasp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompilerTest {
    @Test
    fun testCompiler() {
        val program = """
            ADD 1
            SUB 2
            HLT
        """.trimIndent()

        val operations = Parser(program).parse()
        val code = Compiler().compile(operations)
        Assertions.assertEquals(listOf(2, 1, 3, 2, 8, 0), code)
    }
}