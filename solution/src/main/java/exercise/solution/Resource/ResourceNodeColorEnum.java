package exercise.solution.Resource;

public enum ResourceNodeColorEnum {

    RED(true),
    BLACK(false);

    boolean color;

    ResourceNodeColorEnum(boolean color) {
        this.color = color;
    }

    public boolean colorValue() {
        return color;
    }
}
