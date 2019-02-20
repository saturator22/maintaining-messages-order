package exercise.solution.Model;


public class OffSet implements Comparable<OffSet> {

    private int offSet;

    public int getOffSet() {
        return offSet;
    }

    @Override
    public int compareTo(OffSet other) {
        return Integer.compare(this.offSet, other.offSet);
    }
}
