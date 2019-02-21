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
        int stateLength = currentState.length();

        if(stateLength <= offSet) {
            offSet = stateLength;
        } else if(offSet < 0) {
            offSet = stateLength + offSet + 1;
            offSet = validateOffSet(stateLength, offSet);
        }
        return offSet;
    }

    private int validateOffSet(int stateLength, int offSet) {
        if(offSet < stateLength && offSet < 0) {
            offSet = stateLength;
        }
        return offSet;
    }

    public String getState() {
        return state.toString();
    }
}
