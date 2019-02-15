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

    //TODO handling size after rotation
    public int size() {
        if(root == emptyNode) {
            return 0;
        }
        return root.rightLeafsCounter + root.leftLeafsCounter + 1;
    }

    public void appendNode(T item) {
        appendNode(new ResourceNode<>(item));
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
        //TODO CHECK REFERENCES ON EACH STEP OF TEST
        while(parent.nodeColor == RED.color) {
            grandParent = parent.parentNode;

            //If parent of node is left leaf of its parent
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
                    rotateRight(grandParent);
                //If node is parent right leaf & uncle is BLACK
                } else {
                    node = parent;
                    rotateLeft(parent);
                }
            //If parent of node is right leaf of its parent
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
                    rotateRight(parent);
                    //If node is parent right leaf & uncle is BLACK
                } else {
                    parent.nodeColor = BLACK.color;
                    grandParent.nodeColor = RED.color;
                    rotateLeft(grandParent);
                }
            }
        }
        //Keeping root BLACK
        root.nodeColor = BLACK.color;
    }

    private void rotateRight(ResourceNode<T> pivotNode) {

        //Rotating pivotNode with newParent
        //and assigning right leaf of newParent to left leaf of pivotNode
        ResourceNode<T> newParent = pivotNode.leftLeaf;
        pivotNode.leftLeaf = newParent.rightLeaf;

        //Assiging pivotNode as parent of right leaf of newParent
        //if it's not empty
        if(!isEmpty(newParent.rightLeaf)) {
            newParent.rightLeaf.parentNode = pivotNode;
        }

        //Making parent of pivotNode
        //the parent of newParent
        newParent.parentNode = pivotNode.parentNode;

        //Assigning newParent to the root if parent of pivotNode is empty
        if(isEmpty(pivotNode.parentNode)) {
            root = newParent;
        //Assigning newParent as a rightLeaf of parent for pivotNode
        } else if(pivotNode.parentNode.rightLeaf == pivotNode) {
            pivotNode.parentNode.rightLeaf = newParent;
        //Assigning newParent as a leftLeaf of parent for pivotNode
        } else {
            pivotNode.parentNode.leftLeaf = newParent;
        }
        //Assigning newParent as a parent of pivotNode
        //and making pivotNode the rightLeaf of newParent
        newParent.rightLeaf = pivotNode;
        pivotNode.parentNode = newParent;
    }

    private void rotateLeft(ResourceNode<T> pivotNode) {

        //Rotating pivotNode with newParent
        //and assigning leftLeaf of newParent to rightLeaf of pivotNode
        ResourceNode<T> newParent = pivotNode.rightLeaf;
        pivotNode.rightLeaf = newParent.leftLeaf;

        //Assigning parent of newParent leftLeaf to pivotNode
        if(!isEmpty(newParent.leftLeaf)) {
            newParent.leftLeaf.parentNode = pivotNode;
        }
        //Assigning parentNode of pivotNode to parentNode of pivotNode
        newParent.parentNode = pivotNode.parentNode;

        //Assigning root to newParent if parent of pivotNode was empty
        if(isEmpty(pivotNode.parentNode)) {
            root = newParent;
        //Making newParent parent of pivotNode if pivotNode was leftLeaf
        } else if(pivotNode.parentNode.leftLeaf == pivotNode) {
            pivotNode.parentNode.leftLeaf = newParent;
        //Making newParent parent of pivotNode if pivotNode was leftLeaf
        } else {
            pivotNode.parentNode.rightLeaf = newParent;
        }

        //Assigning newParent as a parent of pivotNode
        //and making pivotNode the leftLeaf of newParent
        pivotNode.parentNode = newParent;
        newParent.leftLeaf = pivotNode;
    }

    public T getItem(T item) {

        ResourceNode<T> current = root;

        while(!isEmpty(current)) {

            if(item.equals(current.item)) {
                return current.item;
            } else if(item.compareTo(current.item) > 0) {
                current = current.rightLeaf;
            } else {
                current = current.leftLeaf;
            }
        }

        return null;
    }

    //For test purposes
    public ResourceNode<T> getNode(T item) {
        ResourceNode<T> current = root;

        while(!isEmpty(current)) {

            if(item.equals(current.item)) {
                return current;
            } else if(item.compareTo(current.item) > 0) {
                current = current.rightLeaf;
            } else {
                current = current.leftLeaf;
            }
        }

        return null;
    }
}
