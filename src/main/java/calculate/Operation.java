package calculate;

import java.util.function.BinaryOperator;

public enum Operation {
    SUM((a, b) -> a + b),
    MULTIPLICATION((a, b) -> a * b),
    DIVISION((a, b) -> a / b);

    private BinaryOperator<Float> operator;

    Operation(BinaryOperator<Float> operator) {
        this.operator = operator;
    }

    public float apply(float a, float b) {
        return operator.apply(a, b);
    }

    public static Operation from(String operation) {
        switch (operation) {
            case "1":
                return SUM;
            case "2":
                return MULTIPLICATION;
            case "3":
                return DIVISION;
            default:
                throw new UnsupportedOperationException(operation);
        }
    }
}
