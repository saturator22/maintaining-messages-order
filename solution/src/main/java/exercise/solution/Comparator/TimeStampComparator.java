package exercise.solution.Comparator;

import exercise.solution.Model.TimeStampMillis;

import java.util.Comparator;

public class TimeStampComparator implements Comparator<TimeStampMillis> {

    @Override
    public int compare(TimeStampMillis m1, TimeStampMillis m2) {

        if(m2 == null) {
            return 1;
        }

        Long m1timeStamp = m1.getTimeStampMillis();
        Long m2timeStamp = m2.getTimeStampMillis();

        return m1timeStamp.compareTo(m2timeStamp);
    }
}
