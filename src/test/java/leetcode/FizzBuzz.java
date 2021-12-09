package leetcode;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FizzBuzz {

    @Test
    public void testName() {
        int n = 10000000;

        List<String> strings = fizzBuzz(n);

        System.out.println(Arrays.toString(strings.toArray()));

    }

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String answer = String.valueOf(i);

            if (i % 3 == 0 && i % 5 == 0) {
                answer = "FizzBuzz";
            } else if (i % 3 == 0) {
                answer = "Fizz";
            } else if (i % 5 == 0) {
                answer = "Buzz";
            }

            result.add(answer);
        }

        return result;
    }
}
