package exercise.solution.Resource;

import exercise.solution.Model.Commit;
import exercise.solution.Model.TimeStampMillis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicMarkableReference;

@Component
public class ResourceStorage {

    @Autowired
    private ResourceState resourceState;

    private final static ResourceNode EMPTY = new ResourceNode(null, null);
    private AtomicMarkableReference<ResourceNode> head;

    public ResourceStorage() {
        this.head = new AtomicMarkableReference<>(EMPTY, false);
    }

    public void insert(Commit newCommit) {
        boolean isInserted = false;
        AtomicMarkableReference<ResourceNode> currentAtomicNode = head;
        TimeStampMillis currentCommitTimeStamp;
        TimeStampMillis newCommitTimeStamp;

        while(!isInserted) {
            ResourceNode currentNode = currentAtomicNode.getReference();
            ResourceNode nodeToInsert = new ResourceNode(newCommit, currentNode);

            if(getHeadNode() == EMPTY) {
                isInserted = insertNode(currentAtomicNode, nodeToInsert, currentNode);
            } else{
                currentCommitTimeStamp = currentNode.getKey().getTimeStampMillis();
                newCommitTimeStamp = newCommit.getTimeStampMillis();

                if(newCommitTimeStamp.compareTo(currentCommitTimeStamp) < 0) {
                    isInserted = insertBefore(currentAtomicNode, nodeToInsert, currentNode);
                } else {
                    if(currentNode.getNext().getReference().getKey() != null) {
                        currentAtomicNode = currentNode.getNext();
                    }
                    isInserted = insertAfter(currentAtomicNode, nodeToInsert, currentNode);
                }
            }
        }
        resourceState.updateState(head);
    }

    private boolean insertAfter(AtomicMarkableReference<ResourceNode> currentAtomicNode,
                                ResourceNode nodeToInsert, ResourceNode currentNode) {
        boolean isInserted;

        ResourceNode nextOfCurrent = currentNode.getNext().getReference();
        ResourceNode newNode = new ResourceNode(nodeToInsert.getKey(), nextOfCurrent);
        isInserted = insertNode(currentAtomicNode.getReference().getNext(), newNode, nextOfCurrent);

        return isInserted;
    }

    private boolean insertBefore(AtomicMarkableReference<ResourceNode> currentAtomicNode,
                                 ResourceNode nodeToInsert, ResourceNode currentNode) {
        boolean isInserted;
        ResourceNode headNode = head.getReference();

        if(currentAtomicNode == head) {
            isInserted = insertNode(currentAtomicNode, nodeToInsert, headNode);
        } else {
            isInserted = insertNode(currentAtomicNode, nodeToInsert, currentNode);
        }

        return isInserted;
    }

    private boolean insertNode(AtomicMarkableReference<ResourceNode> currentAtomicNode,
                               ResourceNode nodeToInsert, ResourceNode currentNode) {
        currentAtomicNode.attemptMark(currentNode, true);

        return compareAndSet(currentAtomicNode, nodeToInsert, currentNode);
    }

    private boolean compareAndSet(AtomicMarkableReference<ResourceNode> currentReferencedNode,
                                  ResourceNode nodeToInsert, ResourceNode currentNode) {

        return currentReferencedNode.compareAndSet(currentNode, nodeToInsert, true, false);
    }

    private ResourceNode getHeadNode() {
        return head.getReference();
    }
}