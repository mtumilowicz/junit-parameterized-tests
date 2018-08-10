import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by mtumilowicz on 2018-08-10.
 */
class CalculatorTest {

    @DisplayName("Test of add method")
    @ParameterizedTest(name = "{index} ==> {0} + {1} = {2}")
    @MethodSource
    void add(int a, int b, int expectedSum) {
        assertEquals(expectedSum, Calculator.add(a, b));
    }

    static Stream<Arguments> add() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(1, 1, 2),
                Arguments.of(2, 1, 3),
                Arguments.of(1, 3, 4),
                Arguments.of(-4, 4, 0),
                Arguments.of(-5, -5, -10),
                Arguments.of(-3, 6, 3)
        );
    }
}