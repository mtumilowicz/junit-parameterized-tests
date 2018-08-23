[![Build Status](https://travis-ci.com/mtumilowicz/junit-parameterized-tests.svg?token=PwyvjePQ7aiAX51hSYLE&branch=master)](https://travis-ci.com/mtumilowicz/junit-parameterized-tests)

# junit-parameterized-tests
The main goal of this project is to compare parametrized tests in `JUnit4` and `JUnit5`.

_Reference_: https://github.com/junit-team/junit4/wiki/parameterized-tests  
_Reference_: https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests

# manual
## JUnit 4
* `build.gradle`
    ```
    testCompile group: 'junit', name: 'junit', version: '4.12'
    ```

* parametrized test
    1. method
        ```
        @Test
        public void add() {
            assertEquals(expectedSum, Calculator.add(a, b));
        }    
        ```
    1. constructor of a test class + fields (matching parameters)
        ```
        private int a;
        private int b;
        private int expectedSum;
    
        public CalculatorTest(int a, int b, int expectedSum) {
            this.a = a;
            this.b = b;
            this.expectedSum = expectedSum;
        }    
        ```
    1. data supplier
        ```
        @Parameters(name = "{index}: sum({0} + {1})={2}")
        public static Iterable<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { 0, 0, 0 }, 
                    { 1, 1, 2 }, 
                    { 2, 1, 3 }, 
                    { 1, 3, 4 }, 
                    { -4, 4, 0 }, 
                    { -5, -5, -10 }, 
                    { -3, 6, 3 }
            });
        }    
        ```
        where:
        ```
        @Parameters(name = "{index}: sum({0} + {1})={2}")
        ```
        is a label for test cases:
        ```
        [0:sum(0+0)=0]
            add[0:sum(0+0)=0]
        [1:sum(1+1)=2]
            add[1:sum(1+1)=2]
        [2:sum(2+1)=3]
            add[2:sum(2+1)=3]
        ```

## Junit 5
* `build.gradle`
    ```
    testCompile group: 'org.junit.jupiter', name: 'junit-jupiter-api', version: '5.2.0'
    testCompile group: 'org.junit.jupiter', name: 'junit-jupiter-params', version: '5.2.0'    
    ```

* parametrized test
    1. method
        ```
        @DisplayName("Test of add method")
        @ParameterizedTest(name = "{index} ==> {0} + {1} = {2}")
        @MethodSource
        void add(int a, int b, int expectedSum) {
            assertEquals(expectedSum, Calculator.add(a, b));
        }    
        ```
    1. data supplier
        ```
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
        ```
        **Remark**: default name for supplier method is same as tested method