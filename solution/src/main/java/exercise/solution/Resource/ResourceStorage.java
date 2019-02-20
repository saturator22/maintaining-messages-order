package exercise.solution.Resource;

import exercise.solution.Model.Commit;
import exercise.solution.Model.OffSet;
import exercise.solution.Model.TimeStampMillis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicMarkableReference;

@Component
public class ResourceStorage {

    @Autowired
    ResourceState resourceState;

    private final static ResourceNode EMPTY = new ResourceNode(null, null);
    private AtomicMarkableReference<ResourceNode> head;

    public ResourceStorage() {
        this.head = new AtomicMarkableReference<>(EMPTY, false);
    }

    public void insert(Commit newCommit) {
        boolean isInserted = false;
        ResourceNode headNode = head.getReference();
        AtomicMarkableReference<ResourceNode> currentAtomicNode = head;

        while(!isInserted) {
            ResourceNode currentNode = currentAtomicNode.getReference();
            ResourceNode nodeToInsert = new ResourceNode(newCommit, currentNode);

            TimeStampMillis currentCommitTimeStamp;
            TimeStampMillis newCommitTimeStamp;

            OffSet currentCommitOffSet;
            OffSet newCommitOffSet;

            if(headNode == EMPTY) {
                isInserted = insertNode(currentAtomicNode, nodeToInsert, headNode);
            } else{
                currentCommitTimeStamp = currentNode.key.getTimeStampMillis();
                newCommitTimeStamp = newCommit.getTimeStampMillis();

                if(newCommitTimeStamp.compareTo(currentCommitTimeStamp) < 0) {
                    if(currentAtomicNode == head) {
                        isInserted = insertNode(currentAtomicNode, nodeToInsert, headNode);
                    } else {
                        isInserted = insertNode(currentAtomicNode, nodeToInsert, currentNode);
                    }
                } else if(newCommitTimeStamp.compareTo(currentCommitTimeStamp) >= 0) {
                    currentCommitOffSet = currentNode.key.getOffSet();
                    newCommitOffSet = newCommit.getOffSet();

                    if(currentNode.next.getReference().key != null) {
                        currentAtomicNode = currentNode.next;
                    } else if(newCommitOffSet.compareTo(currentCommitOffSet) == 0) {
                        isInserted = insertNode(currentAtomicNode, nodeToInsert, currentNode);
                    }else {
                        ResourceNode nextOfCurrent = currentNode.next.getReference();
                        ResourceNode newNode = new ResourceNode(newCommit, nextOfCurrent);
                        isInserted = insertNode(currentAtomicNode.getReference().next, newNode, nextOfCurrent);
                    }
                }
            }
        }
        resourceState.updateState(head);
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
}