package exercise.solution.Service;

import exercise.solution.Model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitService {

    @Autowired
    private ResourceService resourceService;

    public void storeMessage(Commit commit) {
        resourceService.storeMessage(commit);
    }
}
