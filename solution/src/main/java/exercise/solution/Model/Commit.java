package exercise.solution.Model;


public class Commit {

    private TimeStampMillis timeStampMillis;
    private Message message;
    private OffSet offSet;

    public TimeStampMillis getTimeStampMillis() {
        return timeStampMillis;
    }

    public Message getMessage() {
        return message;
    }

    public OffSet getOffSet() {
        return offSet;
    }

    @Override
    public String toString() {
        return getMessage().getMessage();
    }
}
