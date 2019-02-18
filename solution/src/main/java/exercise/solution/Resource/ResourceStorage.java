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
        boolean isInserted = false;
        AtomicMarkableReference<ResourceNode> currentAtomicNode = head;
        ResourceNode headNode = head.getReference();

        while(!isInserted) {
            ResourceNode currentNode = currentAtomicNode.getReference();
            ResourceNode nodeToInsert = new ResourceNode(message, currentNode);

            if(headNode.key == null) {
                currentAtomicNode.attemptMark(currentNode, true);
                isInserted = compareAndSet(currentAtomicNode, nodeToInsert, headNode);
            } else{
                if(message.compare(message, currentNode.key) < 0) {
                    if(currentAtomicNode == head) {
                        currentAtomicNode.attemptMark(currentNode, true);
                        isInserted = compareAndSet(currentAtomicNode, nodeToInsert, headNode);
                    } else {
                        currentAtomicNode.attemptMark(currentNode, true);
                        isInserted = compareAndSet(currentAtomicNode, nodeToInsert, currentNode);
                    }
                } else if(message.compare(message, currentNode.key) >= 0) {
                    if(currentNode.key != null) {
                        currentAtomicNode = currentNode.next;
                    } else {
                        currentAtomicNode.attemptMark(currentNode, true);
                        isInserted = compareAndSet(currentAtomicNode, nodeToInsert, currentNode);
                    }
                }
            }
        }
        return true;
    }

//    public boolean beforeInsert(Messageable messageable) {
//        long offset = messageable.getOffSet();
//        ResourceNode currentNode = head.getReference();
//
//        for(long i = 0; i <= offset; i++) {
//            if()
//        }
//
//        return true;
//    }

    private boolean compareAndSet(AtomicMarkableReference<ResourceNode> currentReferencedNode,
                                 ResourceNode nodeToInsert, ResourceNode currentNode) {
        return currentReferencedNode.compareAndSet(currentNode, nodeToInsert, true, false);
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
}
