package javaCoreModule13.httpPractice;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class HttpDemo {

    private static final String CREATE_USER_URL = "https://pingponggoit.herokuapp.com/createUser";
    private static final String GET_USER_BY_ID_URL = "https://pingponggoit.herokuapp.com/getUserById";
    private static final String GET_USERS_URL = "https://pingponggoit.herokuapp.com/getUsers";
    private static final String REMOVE_USER_URL = "https://pingponggoit.herokuapp.com/removeUser";
    public static void main(String[] args) throws IOException, InterruptedException {
        //Task1
        User user = createDefaultUser();
        final User createdUser = HttpUtil.sendPost(URI.create(CREATE_USER_URL), user);
        System.out.println("Task 1 = "+createdUser);
        // Task2
        String string = String.format("%s?id=%d", GET_USER_BY_ID_URL, createdUser.getId());
        User task2 = HttpUtil.sendGet(URI.create(string));
        System.out.println("Task 2 = " + task2);

        //Task 3
        // 1.create a user; 2. get users list; 3. remove user; 4. get users list to verify
        User userTask3 = createDefaultUser();
        User createdUserTask3 = HttpUtil.sendPost(URI.create(CREATE_USER_URL), userTask3);
        System.out.println("Created user = " + createdUserTask3);
        List<User> users = HttpUtil.sendGetWithListOfUsers(URI.create(GET_USERS_URL));
        System.out.println("Users list before delete = " + users);
        HttpUtil.sendDelete(URI.create(REMOVE_USER_URL), createdUserTask3);
        List<User> usersAfterDelete = HttpUtil.sendGetWithListOfUsers(URI.create(GET_USERS_URL));
        System.out.println("Users list after delete = " + usersAfterDelete);

    }

    public static User createDefaultUser(){
        User user = new User();
        user.setId(1);
        user.setName("Ihor");
        user.setSurname("Beztsennyi");
        user.setSalary(68000);
        user.setGender("male");
        return user;
    }
}