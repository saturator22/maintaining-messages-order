package exercise.solution.Repository;

import exercise.solution.Controller.MessageController;
import exercise.solution.Model.Commit;
import exercise.solution.Resource.ResourceState;
import exercise.solution.Resource.ResourceStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceRepository {

    @Autowired
    ResourceStorage resourceStorage;

    @Autowired
    ResourceState resourceState;

    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    public void appendMessage(Commit commit) {
        log.info("APPEND MESSAGE REPOSITORY");

        this.resourceStorage.insert(commit);
    }

    public String getMessages() {

        log.info("GET MESSAGES FROM REPO");
        return resourceState.getState();
    }
}
