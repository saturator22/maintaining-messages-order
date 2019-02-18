package exercise.solution.Resource;

import exercise.solution.Model.Messageable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class ResourceStorage {

    private final static ResourceNode EMPTY = new ResourceNode(null, null);
    private AtomicMarkableReference<ResourceNode> head;
    private AtomicInteger size = new AtomicInteger(0);

    public ResourceStorage() {
        this.head = new AtomicMarkableReference<>(EMPTY, false);
    }

    private boolean insert(Messageable message, AtomicMarkableReference<ResourceNode> currentAtomicNode) {
        boolean isInserted = false;
        ResourceNode headNode = head.getReference();

        while(!isInserted) {
            ResourceNode currentNode = currentAtomicNode.getReference();
            ResourceNode nodeToInsert = new ResourceNode(message, currentNode);

            if(headNode == EMPTY) {
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
                    if(currentNode.key != null && message.compare(message, currentNode.next.getReference().key) < 0) {
                        currentAtomicNode = currentNode.next;
                    } else {
                        ResourceNode nextOfCurrent = currentNode.next.getReference();
                        ResourceNode newNode = new ResourceNode(message, nextOfCurrent);
                        currentAtomicNode.getReference().next.attemptMark(nextOfCurrent, true);
                        isInserted = compareAndSet(currentAtomicNode.getReference().next, newNode, nextOfCurrent);
                    }
                }
            }
        }
        System.out.println(getSize());
        System.out.println(getState());
        return true;
    }

    public boolean insert(Messageable messageable) {
        long offset = messageable.getOffSet();
        AtomicMarkableReference<ResourceNode> currentNode = head;

        if(offset < 0) {
            offset = size.get() + 1;
        }
        if(offset > size.get()){
            offset = size.get();
        }

        for(long i = 1; i < offset; i++) {
            if(currentNode.getReference().key != null) {
                currentNode = currentNode.getReference().next;
            }
        }
        return insert(messageable, currentNode);
    }

    private boolean compareAndSet(AtomicMarkableReference<ResourceNode> currentReferencedNode,
                                  ResourceNode nodeToInsert, ResourceNode currentNode) {
        if (currentReferencedNode.compareAndSet(currentNode, nodeToInsert, true, false)) {
            size.getAndIncrement();
            return true;
        }
        return false;
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

    public AtomicMarkableReference<ResourceNode> getHead() {
        return head;
    }

    public int getSize() {
        return size.get();
    }
}