package exercise.solution.Model;

import exercise.solution.Comparator.TimeStampComparator;

public class TimeStampMillis extends TimeStampComparator {

    private long timeStampMillis;

    public long getTimeStampMillis() {
        return timeStampMillis;
    }

    public void setTimeStampMillis(long timeStampMillis) {
        this.timeStampMillis = timeStampMillis;
    }
}
