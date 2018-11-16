package fun.peri.enumtest;

/**
 * 注解定义方式2
 */
public enum Operator2 {
    ADD {
        public int calculate(int a, int b) {
            return a + b;
        }
    },
    SUBTRACT {
        public int calculate(int a, int b) {
            return a - b;
        }
    },
    MULTIPLY {
        public int calculate(int a, int b) {
            return a * b;
        }
    },
    DIVIDE {
        public int calculate(int a, int b) {
            return a / b;
        }
    };

    public abstract int calculate(int a, int b);

}
