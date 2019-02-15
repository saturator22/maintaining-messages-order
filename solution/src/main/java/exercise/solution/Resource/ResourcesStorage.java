package exercise.solution.Resource;

import static exercise.solution.Resource.ResourceNodeColorEnum.BLACK;
import static exercise.solution.Resource.ResourceNodeColorEnum.RED;

public class ResourcesStorage <T extends Comparable<T>>{

    //Initial root
    ResourceNode<T> emptyNode = new ResourceNode<>();
    ResourceNode<T> root = emptyNode;


    private boolean isEmpty(ResourceNode<T> node) {
        return node == emptyNode;
    }

    public int size() {
        if(root == emptyNode) {
            return 0;
        }
        return root.rightLeafsCounter + root.leftLeafsCounter + 1;
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
        if(isEmpty(currentParent)) {
            root = node;
        } else if(node.item.compareTo(currentParent.item) < 0) {
            currentParent.leftLeaf = node;
        } else {
            currentParent.rightLeaf = node;
        }

        //Switching node to RED and initializing it's leafs
        node.nodeColor = RED.color;
        node.leftLeaf = emptyNode;
        node.rightLeaf = emptyNode;

        validateAfterAppending(node);
    }

    private void validateAfterAppending(ResourceNode<T> node) {

        ResourceNode<T> uncle = emptyNode;
        ResourceNode<T> grandParent = emptyNode;
        ResourceNode<T> parent = node.parentNode;

        //While parent of node is still RED
        while(parent.nodeColor == RED.color) {
            grandParent = parent.parentNode;

            //If parent of node is left leaf
            if(parent == grandParent.leftLeaf) {

                uncle = grandParent.rightLeaf;

                if(uncle.nodeColor == RED.color) {
                    uncle.nodeColor = BLACK.color;
                    parent.nodeColor = BLACK.color;
                    grandParent.nodeColor = RED.color;
                    node = grandParent;
                //If node is parent left leaf & uncle is BLACK
                } else if(node == parent.leftLeaf) {
                    parent.nodeColor = BLACK.color;
                    grandParent.nodeColor = RED.color;
                    //TODO rotateRight()
                //If node is parent right leaf & uncle is BLACK
                } else {
                    node = parent;
                    //TODO rotateLeft()
                }
            //If parent of node is right leaf
            } else {

                uncle = grandParent.leftLeaf;

                if(uncle.nodeColor == RED.color) {
                    uncle.nodeColor = BLACK.color;
                    parent.nodeColor = BLACK.color;
                    grandParent.nodeColor = RED.color;
                    node = grandParent;
                    //If node is parent left leaf & uncle is BLACK
                } else if(node == parent.leftLeaf) {
                    node = parent;
                    //TODO rotateRight()
                    //If node is parent right leaf & uncle is BLACK
                } else {
                    parent.nodeColor = BLACK.color;
                    grandParent.nodeColor = RED.color;
                    //TODO rotateLeft()
                }
            }
        }
        //Keeping root BLACK
        root.nodeColor = BLACK.color;
    }
}
