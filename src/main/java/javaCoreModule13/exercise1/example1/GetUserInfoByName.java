package javaCoreModule13.exercise1.example1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class GetUserInfoByName {

    public String getUserByName (String username) throws URISyntaxException, IOException, InterruptedException {

        String url = "https://jsonplaceholder.typicode.com/users?username=";

        HttpRequest request = HttpRequest.newBuilder(new URI(url+username))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code = " + response.statusCode());

        return response.body();
    }
}