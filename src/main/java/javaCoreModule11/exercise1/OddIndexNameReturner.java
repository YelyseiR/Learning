package javaCoreModule11.exercise1;

import javaCoreModule11.Names;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OddIndexNameReturner{
    public static void main(String[] args) {
        List<String> oddIndexNames = namesList.stream().filter(person -> person.getNumber() % 2 != 0)
                .map(Names::getName)
                .collect(Collectors.toList());
        System.out.println(String.join(", ", oddIndexNames));
}

    public static List<Names> namesList = Arrays.asList(
            new Names(1, "Mykhailo"),
            new Names(2, "Bohdan"),
            new Names(3, "Roman"),
            new Names(4, "Vasyl"),
            new Names(5, "Oleksiy"),
            new Names(6, "Olga"),
            new Names(7, "Viktor"),
            new Names(8, "Volodymyr"),
            new Names(9, "John"),
            new Names(10, "Mike"));

}



