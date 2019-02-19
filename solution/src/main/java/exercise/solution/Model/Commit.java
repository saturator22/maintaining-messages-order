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

    public void setTimeStampMillis(TimeStampMillis timeStampMillis) {
        this.timeStampMillis = timeStampMillis;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setOffSet(OffSet offSet) {
        this.offSet = offSet;
    }

    @Override
    public String toString() {
        return getMessage().getMessage();
    }
}
