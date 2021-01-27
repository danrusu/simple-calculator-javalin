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

    private SimpleCalculator() {
    }

    private static ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    public static void calculateHandler(Context ctx) {
        String resultText = "One number format is invalid";
        String firstNumber = ctx.queryParam(FIRST_NUMBER_QUERY_VAR_NAME);
        String secondNumber = ctx.queryParam(SECOND_NUMBER_QUERY_VAR_NAME);
        String operation = ctx.queryParam(OPERATION_QUERY_VAR_NAME);

        if ((areValid(firstNumber, secondNumber))) {
            float result = Operation.from(operation).apply(
                    toFloat(firstNumber),
                    toFloat(secondNumber));
            resultText = String.valueOf(result);
        }

        List<String> numbers = asList(firstNumber, secondNumber);
        OperationResult resultObj = new OperationResult(numbers, operation, resultText);

        ctx.result(jsonMapper.writeValueAsString(resultObj));
    }

    private static boolean areValid(String ...numbersAsText) {
        return stream(numbersAsText).allMatch(SimpleCalculator::isValidNumber);
    }

    private static boolean isValidNumber(String numberAsText) {
        return ! numberAsText.isEmpty() && numberAsText.matches("^\\d+$");
    }

    private static float toFloat(String number) {
        return parseFloat(number.replace("e", ""));
    }
}
