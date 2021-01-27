package app;

import calculate.Operation;
import calculate.OperationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.lang.Float.parseFloat;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

@Slf4j
public class SimpleCalculator {

    private static final String FIRST_NUMBER_QUERY_VAR_NAME = "firstNumber";
    private static final String SECOND_NUMBER_QUERY_VAR_NAME = "secondNumber";
    private static final String OPERATION_QUERY_VAR_NAME = "operation";
    private static final String INVALID_NUMBER_FORMAT_MESSAGE = "One number format is invalid";

    private SimpleCalculator() {
    }

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    public static void calculateHandler(final Context ctx) {
        String resultText = INVALID_NUMBER_FORMAT_MESSAGE;
        final String firstNumber = ctx.queryParam(FIRST_NUMBER_QUERY_VAR_NAME);
        final String secondNumber = ctx.queryParam(SECOND_NUMBER_QUERY_VAR_NAME);
        final String operation = ctx.queryParam(OPERATION_QUERY_VAR_NAME);

        if ((areValid(firstNumber, secondNumber))) {
            final float result = Operation.from(operation).apply(
                    toFloat(firstNumber),
                    toFloat(secondNumber));
            resultText = String.valueOf(result);
        }

        final List<String> numbers = asList(firstNumber, secondNumber);
        final OperationResult resultObj = new OperationResult(numbers, operation, resultText);

        ctx.result(jsonMapper.writeValueAsString(resultObj));
    }

    private static boolean areValid(final String... numbersAsText) {
        return stream(numbersAsText).allMatch(SimpleCalculator::isValidNumber);
    }

    private static boolean isValidNumber(final String numberAsText) {
        return !numberAsText.isEmpty() && numberAsText.matches("^\\d+$");
    }

    private static float toFloat(final String number) {
        return parseFloat(number.replace("e", ""));
    }
}
