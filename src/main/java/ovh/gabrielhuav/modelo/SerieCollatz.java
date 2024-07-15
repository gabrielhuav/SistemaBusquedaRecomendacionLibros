package ovh.gabrielhuav.modelo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class SerieCollatz {
    public String collatz(int number) {
        StringBuilder sequence = new StringBuilder();
        sequence.append(number).append(" ");
        while (number != 1) {
            if (number % 2 == 0) {
                number = number / 2;
            } else {
                number = 3 * number + 1;
            }
            sequence.append(number).append(" ");
        }
        return sequence.toString();
    }
    
    public Map<String, String> collatzSequence(Integer input) {
        StringBuilder sequence = new StringBuilder();
        sequence.append(input).append(" ");
        while (input != 1) {
            if (input % 2 == 0) {
                input = input / 2;
            } else {
                input = 3 * input + 1;
            }
            sequence.append(input).append(" ");
        }
        Map<String, String> response = new HashMap<>();
        response.put("sequence", sequence.toString());
        return response;
    }
    
}