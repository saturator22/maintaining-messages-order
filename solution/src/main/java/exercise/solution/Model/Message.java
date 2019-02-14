package exercise.solution.Model;

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
    public int compareTo(Message otherMessage) {
        return otherMessage == null ? 1 : this.id - otherMessage.getId();
    }
}
