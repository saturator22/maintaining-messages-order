package exercise.solution.Service;

import exercise.solution.Model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitService {

    @Autowired
    ResourceService resourceService;

    public void storeMessage(Commit messageable) {
        resourceService.storeMessage(messageable);
    }
}
