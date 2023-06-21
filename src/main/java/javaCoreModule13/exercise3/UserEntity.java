package javaCoreModule13.exercise3;

public class UserEntity {
    private int userId;
    private int id;
    private String title;
    private String completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n Users{");
        sb.append("userId=").append(userId);
        sb.append(", id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", completed='").append(completed).append('\'');
        sb.append('}');
        return sb.toString();
    }
}