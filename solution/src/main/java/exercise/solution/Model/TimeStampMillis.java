package exercise.solution.Model;

public class TimeStampMillis implements Comparable<TimeStampMillis> {

    private long timeStampMillis;

    public long getTimeStampMillis() {
        return timeStampMillis;
    }

    @Override
    public int compareTo(TimeStampMillis other) {
        return Long.compare(this.timeStampMillis, other.timeStampMillis);
    }
}
