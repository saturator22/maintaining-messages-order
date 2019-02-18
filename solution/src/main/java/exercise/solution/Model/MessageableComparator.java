package exercise.solution.Model;

import java.util.Comparator;

public class MessageableComparator<T extends Messageable> implements Comparator<T> {

    @Override
    public int compare(T m1, T m2) {

        if(m2 == null) {
            return 1;
        }

        Long m1offSet = m1.getOffSet();
        Long m2offSet = m2.getOffSet();

        if(m1offSet == 0 && m1offSet > m2offSet) {
            return -1;
        }

        int offSetComp = m1offSet.compareTo(m2offSet);

        if (offSetComp != 0) {
            return offSetComp;
        }

        Long m1timeStamp = m1.getTimeStampMillis();
        Long m2timeStamp = m2.getTimeStampMillis();

        return m1timeStamp.compareTo(m2timeStamp);

    }
}
