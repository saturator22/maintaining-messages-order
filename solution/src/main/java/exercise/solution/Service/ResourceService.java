package exercise.solution.Service;

import exercise.solution.Controller.MessageController;
import exercise.solution.Model.Commit;
import exercise.solution.Repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    ResourceRepository resourceRepository;

    public void storeMessage(Commit commit) {
        log.info("STORE MESSAGE FROM SERVICE");
        resourceRepository.appendMessage(commit);
    }

    public String getMessages() {
        log.info("GET MESSAGES FROM SERVICE");
        return resourceRepository.getMessages();
    }
}
