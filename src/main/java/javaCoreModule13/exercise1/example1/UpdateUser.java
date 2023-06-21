package javaCoreModule13.exercise1.example1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UpdateUser {

    String url = "https://jsonplaceholder.typicode.com/users/";

    public String updateUser(int i, String string) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url+i))
                .PUT(HttpRequest.BodyPublishers.ofString(string))
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("StatusCode = " + httpResponse.statusCode());
        return httpResponse.body();
    }
}
