package javaCoreModule13.exercise2;

public class Post {
    public int userId;
    public int id;
    public String title;
    public String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "{" +
                "userId: " + userId +
                ", id: " + id +
                ", title: '" + title + '\'' +
                ", body: '" + body + '\'' +
                '}';
    }

}
