package calculate;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class OperationResult {
    List<String> numbers;
    String operation;
    String result;
}
