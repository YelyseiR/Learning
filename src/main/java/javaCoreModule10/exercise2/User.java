package javaCoreModule10.exercise2;

public class User {
    private String name; //приватне поле імені користувача
    private int age; //приватне поле віку користувача

    public User(String name, int age) { //конструктор, що приймає ім'я та вік користувача
        this.name = name;
        this.age = age;
    }

    public String getName() { //метод, що повертає ім'я користувача
        return name;
    } //повертаємо ім'я
}
