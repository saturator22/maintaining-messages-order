package exercise.solution.Repository;

import exercise.solution.Model.Commit;
import exercise.solution.Resource.ResourceState;
import exercise.solution.Resource.ResourceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceRepository {

    @Autowired
    private ResourceStorage resourceStorage;

    @Autowired
    private ResourceState resourceState;

    public void appendMessage(Commit commit) {
        this.resourceStorage.insert(commit);
    }

    public String getMessages() {
        return resourceState.getState();
    }
}
