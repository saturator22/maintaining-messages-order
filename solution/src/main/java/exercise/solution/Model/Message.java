package exercise.solution.Model;


import java.util.Objects;

public class Message implements Messegeable, Comparable<Message>{

    private int id;
    private String message;

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return getId() == message1.getId() &&
                Objects.equals(getMessage(), message1.getMessage());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getMessage());
    }

    @Override
    public int compareTo(Message otherMessage) {
        return otherMessage == null ? 1 : this.id - otherMessage.getId();
    }
}
