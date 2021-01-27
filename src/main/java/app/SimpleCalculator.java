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
        log.info(ctx.method() + " " + ctx.fullUrl());

        String firstNumber = ctx.queryParam(FIRST_NUMBER_QUERY_VAR_NAME);
        String secondNumber = ctx.queryParam(SECOND_NUMBER_QUERY_VAR_NAME);
        String operation = ctx.queryParam(OPERATION_QUERY_VAR_NAME);
        float result = Operation.from(operation).apply(
                parseFloat(firstNumber),
                parseFloat(secondNumber));

        List<String> numbers = asList(firstNumber, secondNumber);
        OperationResult resultObj = new OperationResult(numbers, operation, String.valueOf(result));

        log.info(resultObj.toString());
        ctx.result(jsonMapper.writeValueAsString(resultObj));
    }
}
