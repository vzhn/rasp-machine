package me.vzhilin.rasp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.Exception

class ParserTest {
    @Test
    fun testAdd() = testParser("ADD 1", listOf(Operation(OperationType.ADD, 1)))

    @Test
    fun testLod() = testParser("LOD 2", listOf(Operation(OperationType.LOD, 2)))

    @Test
    fun testSub() = testParser("SUB 3", listOf(Operation(OperationType.SUB, 3)))

    @Test
    fun testSto() = testParser("STO -4", listOf(Operation(OperationType.STO, -4)))

    @Test
    fun testBpa() = testParser("BPA 54", listOf(Operation(OperationType.BPA, 54)))

    @Test
    fun testRd() = testParser("RD 6", listOf(Operation(OperationType.RD, 6)))

    @Test
    fun testPri() = testParser("PRI 42", listOf(Operation(OperationType.PRI, 42)))

    @Test
    fun testHlt() = testParser("HLT", listOf(Operation(OperationType.HLT, 0)))

    @Test
    fun testMultipleLines() = testParser(
        """
            LOD 1
            ADD 2
            SUB 3
            STO 4
            BPA 5
            RD  60
            PRI 7
            HLT
        """.trimIndent(),
        listOf(
            Operation(OperationType.LOD, 1),
            Operation(OperationType.ADD, 2),
            Operation(OperationType.SUB, 3),
            Operation(OperationType.STO, 4),
            Operation(OperationType.BPA, 5),
            Operation(OperationType.RD, 60),
            Operation(OperationType.PRI, 7),
            Operation(OperationType.HLT, 0),
        )
    )

    private fun testParser(text: String, expected: List<Operation>, expectedError: Exception? = null) {
        Assertions.assertEquals(expected, Parser(text).parse())
    }
}