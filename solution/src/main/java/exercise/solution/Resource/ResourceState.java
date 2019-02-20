package exercise.solution.Resource;

import exercise.solution.Model.Commit;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicMarkableReference;

@Component
public class ResourceState {

    StringBuffer state;

    public ResourceState() {
        this.state = new StringBuffer();
    }

    void updateState(AtomicMarkableReference<ResourceNode> currentNode) {

        StringBuffer currentState = state;
        currentState.setLength(0);

        while(currentNode.getReference().key != null) {
            Commit currentCommit = currentNode.getReference().key;

            int offSet = currentCommit.getOffSet().getOffSet();
            String message = currentCommit.getMessage().getMessage();

            offSet = setOffSet(currentState, offSet);

            currentState.insert(offSet, message);
            currentNode = currentNode.getReference().next;
        }
        System.out.println(state);
    }

    private int setOffSet(StringBuffer currentState, int offSet) {

        if(currentState.length() < offSet || currentState.length() == 0) {
            offSet = currentState.length();
        } else if(offSet < 0) {
            offSet = currentState.length() + offSet + 1;
        }

        return offSet;
    }

    public String getState() {
        return state.toString();
    }
}
