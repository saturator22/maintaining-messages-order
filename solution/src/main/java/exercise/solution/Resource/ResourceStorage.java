package exercise.solution.Resource;

import exercise.solution.Model.Messageable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class ResourceStorage {

    final static ResourceNode EMPTY = new ResourceNode(null, null);
    AtomicMarkableReference<ResourceNode> head;
    AtomicMarkableReference<ResourceNode> tail;
    AtomicInteger size = new AtomicInteger(0);

    public ResourceStorage() {
        this.head = new AtomicMarkableReference<>(EMPTY, false);
        this.tail = new AtomicMarkableReference<>(EMPTY, false);
    }

    private boolean insert(Messageable message, AtomicMarkableReference<ResourceNode> currentAtomicNode) {
        boolean isInserted = false;
//        AtomicMarkableReference<ResourceNode> currentAtomicNode = head;
        ResourceNode headNode = head.getReference();
        ResourceNode tailNode = tail.getReference();

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
                        isInserted = compareAndSet(currentAtomicNode, nodeToInsert, tailNode);
                    }
                }
            }
        }
        size.getAndIncrement();
        System.out.println(size);
        return true;
    }

    public boolean insert(Messageable messageable) {
        long offset = messageable.getOffSet();
        AtomicMarkableReference<ResourceNode> currentNode = head;

        if(offset < 0) {
            offset = size.get();

        }

        if(offset > size.get()){
            offset = size.get();
        }

        for(long i = 0; i < offset; i++) {
            if(currentNode.getReference().key != null) {
                currentNode = currentNode.getReference().next;
            }
        }

        return insert(messageable, currentNode);
    }

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

    public AtomicMarkableReference<ResourceNode> getHead() {
        return head;
    }

    public AtomicMarkableReference<ResourceNode> getTail() {
        return tail;
    }

    public int getSize() {
        return size.get();
    }
}