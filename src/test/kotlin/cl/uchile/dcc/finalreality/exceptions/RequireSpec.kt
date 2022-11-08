package cl.uchile.dcc.finalreality.exceptions

import io.kotest.core.spec.style.FunSpec
import io.kotest.property.PropTestConfig
import io.kotest.property.assume
import io.kotest.property.checkAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class RequireSpec : FunSpec({
    test("'Require.Stat(...).atLeast' throws an exception when the 'stat' is less than the 'least'.") {
        checkAll<Int, Int, String>( // We generate 3 random values (Int, Int, String)
            // We will allow a maximum of 55% of discarded values (see assume below)
            PropTestConfig(maxDiscardPercentage = 55)
        ) { least, stat, name ->
            // We discard all tests where the stat is greater than the least.
            assume(stat < least)
            assertThrows<InvalidStatValueException> {
                Require.Stat(stat, name) atLeast least
            }
        }
    }

    test("'Require.Stat(...).atLeast' does not throw an exception when the 'stat' is greater than the 'least'") {
        checkAll<Int, Int, String>(PropTestConfig(maxDiscardPercentage = 55)) {
            least, stat, name ->
            assume(stat >= least)
            assertDoesNotThrow {
                Require.Stat(stat, name) atLeast least
            }
        }
    }

    test("'Require.Stat(...).atLeast' does not throw an exception if the 'stat' is equal to 'least'") {
        checkAll<Int, String> { least, name ->
            assertDoesNotThrow {
                Require.Stat(least, name) atLeast least
            }
        }
    }

    test("'Require.Stat(...).inRange' throws an exception when the 'stat' is not in the 'range'") {
        checkAll<Int, Int, Int, String>(PropTestConfig(maxDiscardPercentage = 55)) {
            start, end, stat, name ->
            assume(stat !in start..end)
            assertThrows<InvalidStatValueException> {
                Require.Stat(stat, name) inRange start..end
            }
        }
    }

    test("'Require.Stat(...).inRange' does not throw an exception when the 'stat' is in the 'range'") {
        checkAll<Int, Int, String>(PropTestConfig(maxDiscardPercentage = 55)) {
            start, end, name ->
            assume(start < end)
            val stat = (start..end).random()
            assertDoesNotThrow {
                Require.Stat(stat, name) inRange start..end
            }
        }
    }
})
