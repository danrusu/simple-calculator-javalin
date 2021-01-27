package calculate;

import java.util.function.BinaryOperator;

public enum Operation {
    SUM((a, b) -> a + b),
    MULTIPLICATION((a, b) -> a * b),
    DIVISION((a, b) -> a / b);

    private final BinaryOperator<Float> operator;

    Operation(final BinaryOperator<Float> operator) {
        this.operator = operator;
    }

    public float apply(final float a, final float b) {
        return operator.apply(a, b);
    }

    public static Operation from(final String operation) {
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
