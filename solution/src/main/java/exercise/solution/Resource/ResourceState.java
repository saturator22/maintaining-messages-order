package exercise.solution.Resource;

import exercise.solution.Model.Commit;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicMarkableReference;

@Component
public class ResourceState {

    private StringBuffer state;

    public ResourceState() {
        this.state = new StringBuffer();
    }

    synchronized void updateState(AtomicMarkableReference<ResourceNode> currentNode) {

        StringBuffer currentState = state;
        currentState.setLength(0);

        while(currentNode.getReference().getKey() != null) {
            Commit currentCommit = currentNode.getReference().getKey();

            Integer offSet = currentCommit.getOffSet().getOffSet();
            String message = currentCommit.getMessage().getMessage();

            offSet = setOffSet(currentState, offSet);
            currentState.insert(offSet, message);

            currentNode = currentNode.getReference().getNext();
        }
    }

    private int setOffSet(StringBuffer currentState, int offSet) {

        if(currentState.length() <= offSet) {
            offSet = currentState.length();
        } else if(offSet < 0) {
            offSet = currentState.length() + offSet + 1;
            if(offSet < currentState.length() && offSet < 0) {
                offSet = currentState.length();
            }
        }
        return offSet;
    }

    public String getState() {
        return state.toString();
    }
}
