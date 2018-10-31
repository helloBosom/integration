package enumtest;

public enum Operator3 {
    ADD("+") {
        public int calculate(int a, int b) {
            return a + b;
        }
    },
    SUBTRACT("-") {
        public int calculate(int a, int b) {
            return a - b;
        }
    },
    MULTIPLY("*") {
        public int calculate(int a, int b) {
            return a * b;
        }
    },
    DIVIDE("/") {
        public int calculate(int a, int b) {
            return a / b;
        }
    };

    Operator3(String operator) {
        this.operator = operator;
    }

    private String operator;

    public abstract int calculate(int a, int b);

    public String getOperator() {
        return operator;
    }

}