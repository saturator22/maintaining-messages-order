package exercise.solution.Resource;

class ResourceNode<T extends Comparable<T>> {

    public static final ResourceNodeColorEnum RED = ResourceNodeColorEnum.RED;
    public static final ResourceNodeColorEnum BLACK = ResourceNodeColorEnum.BLACK;

    //E.g. Message object
    T item;

    //Counters of nodes under the given node leftLeaf/rightLeaf
    int leftLeafsCounter;
    int rightLeafsCounter;

    //Node color
    boolean nodeColor;

    //References to neighbour nodes
    ResourceNode<T> leftLeaf;
    ResourceNode<T> rightLeaf;
    ResourceNode<T> parentNode;

    //Initial constructor
    ResourceNode() {
        leftLeaf = null;
        rightLeaf = null;
        parentNode = null;
        nodeColor = BLACK.color;
        leftLeafsCounter = 0;
        rightLeafsCounter = 0;
    }

    //Constructor to assign item
    ResourceNode(T item) {
        this();
        this.item = item;
    }

}
