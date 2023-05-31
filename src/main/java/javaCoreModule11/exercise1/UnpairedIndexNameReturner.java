package javaCoreModule11.exercise1;

import java.util.List;

public class UnpairedIndexNameReturner {
    public static String getOddIndexedNames(List<String> names) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < names.size(); i++) {
            if (i % 2 != 0) {
                stringBuilder.append((i+1) + ". " + names.get(i) + ", ");
            }
        }

        // Видаляємо останній кому та пробіл з рядку
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return stringBuilder.toString();
    }

}
