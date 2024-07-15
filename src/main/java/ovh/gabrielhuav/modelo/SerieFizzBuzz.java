package ovh.gabrielhuav.modelo;

import org.springframework.stereotype.Service;

@Service
public class SerieFizzBuzz {
    public String fizzbuzz(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            result.append(fizzBuzzFunction(i)).append("\n");
        }

        return result.toString();
    }

    private String fizzBuzzFunction(int n) {
        if (n % 3 == 0 && n % 5 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(n);
        }
    }
}