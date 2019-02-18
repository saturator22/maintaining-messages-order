package exercise.solution.Resource;

import exercise.solution.Model.Messageable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class ResourceStorage {

    final static ResourceNode EMPTY = new ResourceNode(null, null);
    AtomicMarkableReference<ResourceNode> head;

    public ResourceStorage() {
        this.head = new AtomicMarkableReference<>(EMPTY, false);
    }

    public boolean insert(Messageable message) {
        //TODO REFACTOR OF MULTIPLIED PIECES OF CODE
        boolean isInserted = false;
        AtomicMarkableReference<ResourceNode> currentAtomicNode = head;

        while(!isInserted) {
            ResourceNode currentNode = currentAtomicNode.getReference();
            if(head.getReference().key == null) {
                ResourceNode nodeToInsert = new ResourceNode(message, EMPTY);
                isInserted = head.compareAndSet(head.getReference(), nodeToInsert,false, false);
            } else{
                if(message.compare(message, currentNode.key) < 0) {
                    if(currentAtomicNode == head) {
                        ResourceNode nextNode = currentAtomicNode.getReference();
                        ResourceNode nodeToInsert = new ResourceNode(message, nextNode);
                        isInserted = head.compareAndSet(head.getReference(), nodeToInsert, false, false);
                    } else {
                        ResourceNode nextNode = currentAtomicNode.getReference();
                        ResourceNode nodeToInsert = new ResourceNode(message, nextNode);
                        isInserted = currentAtomicNode.compareAndSet(currentNode, nodeToInsert,
                                false, false);
                    }
                } else if(message.compare(message, currentNode.key) >= 0) {
                    if(currentNode.key != null) {
                        currentAtomicNode = currentNode.next;
                    } else {
                        ResourceNode nextNode = currentAtomicNode.getReference();
                        ResourceNode newNode = new ResourceNode(message, nextNode);
                        isInserted = currentAtomicNode.compareAndSet(nextNode, newNode, false, false);
                    }
                }
            }
        }
        return true;
    }

    public List<Messageable> getState() {
        List<Messageable> state = new ArrayList<>();

        AtomicMarkableReference<ResourceNode> currentAtomicNode = head;

        while(currentAtomicNode.getReference().key != null) {
            state.add(currentAtomicNode.getReference().key);
            currentAtomicNode = currentAtomicNode.getReference().next;
        }

        return state;
    }



    //TODO CAS()
}
