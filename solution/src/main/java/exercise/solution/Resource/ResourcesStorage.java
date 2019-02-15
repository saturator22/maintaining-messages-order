package exercise.solution.Resource;

import static exercise.solution.Resource.ResourceNodeColorEnum.BLACK;

public class ResourcesStorage <T extends Comparable<T>>{

    //Initial root
    ResourceNode<T> emptyNode = new ResourceNode<>();
    ResourceNode<T> root = emptyNode;


    private boolean isEmpty(ResourceNode<T> node) {
        return node == emptyNode;
    }

    public void appendNode(T item) {
        appendNode(new ResourceNode<T>(item));
    }

    private void appendNode(ResourceNode<T> node) {

        ResourceNode<T> currentParent = emptyNode;
        ResourceNode<T> currentNode = root;

        //Traversing to the right place
        while(!isEmpty(currentNode)) {

            currentParent = currentNode;

            //If node item < currentNode item then go to the left
            if(node.item.compareTo(currentNode.item) < 0) {

                currentNode.leftLeafsCounter++;
                currentNode = currentNode.leftLeaf;

            } else {

                currentNode.rightLeafsCounter++;
                currentNode = currentParent.rightLeaf;
            }
        }

        node.parentNode = currentParent;

        //Assigning node to the root if it was first insertion or to the proper side of parent
        if(isEmpty(currentNode)) {
            root = node;
        } else if(node.item.compareTo(currentParent.item) < 0) {
            currentParent.leftLeaf = node;
        } else {
            currentParent.rightLeaf = node;
        }

        //Switching node to RED and initializing it's children
        node.nodeColor = BLACK.color;
        node.leftLeaf = emptyNode;
        node.rightLeaf = emptyNode;
    }

}
