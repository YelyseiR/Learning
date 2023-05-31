package javaCoreModule11.exercise2;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpperCaseStringReturner {
    public static void main(String[] args) {
        final String result = names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
    public static List<String> names =
            Arrays.asList("Mark", "Anna", "Ivan", "Viktor", "Orest", "Olga", "Toma", "Ganna", "Petro", "Max");
}
