package exercise.solution.Model;


public abstract class Messageable extends MessageableComparator{

    private long timeStampMillis;
    private String message;
    private long offSet;

    public long getTimeStampMillis() {
        return timeStampMillis;
    }

    public String getMessage() {
        return message;
    }

    public long getOffSet() {
        return offSet;
    }
}
