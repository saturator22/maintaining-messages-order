package exercise.solution.Resource;

import exercise.solution.Model.Commit;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;

@Component
public class ResourceStorage {

    private final static ResourceNode EMPTY = new ResourceNode(null, null);
    StringBuffer state = new StringBuffer();
    private AtomicMarkableReference<ResourceNode> head;
    private AtomicInteger size = new AtomicInteger(0);

    public ResourceStorage() {
        this.head = new AtomicMarkableReference<>(EMPTY, false);
    }

    public String insert(Commit commit) {
        boolean isInserted = false;
        ResourceNode headNode = head.getReference();
        AtomicMarkableReference<ResourceNode> currentAtomicNode = head;

        while(!isInserted) {
            ResourceNode currentNode = currentAtomicNode.getReference();
            ResourceNode nodeToInsert = new ResourceNode(commit, currentNode);

            if(headNode == EMPTY) {
                currentAtomicNode.attemptMark(currentNode, true);
                isInserted = compareAndSet(currentAtomicNode, nodeToInsert, headNode);
            } else{
                if(commit.getTimeStampMillis().compare(commit.getTimeStampMillis(), currentNode.key.getTimeStampMillis()) < 0) {
                    if(currentAtomicNode == head) {
                        currentAtomicNode.attemptMark(currentNode, true);
                        isInserted = compareAndSet(currentAtomicNode, nodeToInsert, headNode);
                    } else {
                        currentAtomicNode.attemptMark(currentNode, true);
                        isInserted = compareAndSet(currentAtomicNode, nodeToInsert, currentNode);
                    }
                } else if(commit.getTimeStampMillis().compare(commit.getTimeStampMillis(), currentNode.key.getTimeStampMillis()) >= 0) {
                    if(currentNode.next.getReference().key != null) {
                        currentAtomicNode = currentNode.next;
                    } else {
                        ResourceNode nextOfCurrent = currentNode.next.getReference();
                        ResourceNode newNode = new ResourceNode(commit, nextOfCurrent);
                        currentAtomicNode.getReference().next.attemptMark(nextOfCurrent, true);
                        isInserted = compareAndSet(currentAtomicNode.getReference().next, newNode, nextOfCurrent);
                    }
                }
            }
        }
        return getState();
    }

    private boolean compareAndSet(AtomicMarkableReference<ResourceNode> currentReferencedNode,
                                  ResourceNode nodeToInsert, ResourceNode currentNode) {
        if (currentReferencedNode.compareAndSet(currentNode, nodeToInsert, true, false)) {
            size.getAndIncrement();
            return true;
        }
        return false;
    }

    private void updateState() {
        state.setLength(0);
        AtomicMarkableReference<ResourceNode> currentAtomicNode = this.head;

        while(currentAtomicNode.getReference().key != null) {
            Commit currentCommit = currentAtomicNode.getReference().key;

            int offSet = currentCommit.getOffSet().getOffSet();
            String message = currentCommit.getMessage().getMessage();

            if(state.length() == 0) {
                offSet = state.length();
            } else if(state.length() < offSet) {
                offSet = state.length();
            } else if(offSet < 0) {
                offSet = state.length() + offSet + 1;
            }

            state.insert(offSet, message);
            currentAtomicNode = currentAtomicNode.getReference().next;
        }

    }

    public String getState() {
        updateState();
        return state.toString();
    }

    public AtomicMarkableReference<ResourceNode> getHead() {
        return head;
    }

    public int getSize() {
        return size.get();
    }
}