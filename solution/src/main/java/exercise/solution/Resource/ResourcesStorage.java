package exercise.solution.Resource;

public class ResourcesStorage <T extends Comparable<T>>{

    //Initial root
    ResourceNode<T> emptyNode = new ResourceNode<>();
    ResourceNode<T> root = emptyNode;


    private boolean isEmpty(ResourceNode<T> node) {
        return node == emptyNode;
    }


}
