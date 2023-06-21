package javaCoreModule13.exercise1.example1;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {


        //          ДОДАВАННЯ НОВОГО ЮЗЕРА
        CreateUser createUserForTask1 = new CreateUser();
        System.out.println(createUserForTask1.createUser(requestBody));
//
        //       ОНОВЛЕННЯ ІНФОРМАЦІЇ ПРО КОНКРЕТНОГО ЮЗЕРА

//        UpdateUserForTask1 updateUserForTask1 = new UpdateUserForTask1();
//        System.out.println(updateUserForTask1.updateUser(2, string));
//
        //       ВИДАЛЕННЯ ЮЗЕРА

//        DeleteUserForTask1 deleteUserForTask1 = new DeleteUserForTask1();
//        System.out.println(deleteUserForTask1.deleteUser(1));
//
        //          ОТРИМАННЯ ВСіХ ЮЗЕРіВ
//
//        GetAllUsersForTask1 getAllUsersForTask1 = new GetAllUsersForTask1();
//        System.out.println(getAllUsersForTask1.getAllUsers());
//
        //          ОТРИМАННЯ ЮЗЕРА ЗА ID

//        GetUserInfoById getUserByIdForTask1 = new GetUserInfoById();
//        System.out.println(getUserByIdForTask1.GetUserById(1));
////
        //          ОТРИМАННЯ ЮЗЕРА ЗА ІМ'ЯМ

        GetUserInfoByName getUserInfoByName = new GetUserInfoByName();
        System.out.println(getUserInfoByName.getUserByName("Yelysei"));


    }
    static String requestBody = "{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Vasyl Bebra\",\n" +
            "    \"username\": \"Bebra\",\n" +
            "    \"email\": \"bebra@.com\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"Khreschatyk\",\n" +
            "      \"suite\": \"Apt. 777\",\n" +
            "      \"city\": \"Kyiv\",\n" +
            "      \"zipcode\": \"92998-3874\",\n" +
            "      \"geo\": {\n" +
            "        \"lat\": \"50.449365002409486\",\n" +
            "        \"lng\": \"30.523878336473537\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"phone\": \"1-770-736-8031 x56442\",\n" +
            "    \"website\": \"bebra.org\",\n" +
            "    \"company\": {\n" +
            "      \"name\": \"Vasyl Bebra\",\n" +
            "      \"catchPhrase\": \"Smell the bebra\",\n" +
            "      \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "  }";
}