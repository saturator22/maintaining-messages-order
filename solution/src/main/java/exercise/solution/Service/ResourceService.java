package exercise.solution.Service;

import exercise.solution.Controller.MessageController;
import exercise.solution.Model.Message;
import exercise.solution.Repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    ResourceRepository resourceRepository;

    public void storeMessage(Message message) {
        log.info("STORE MESSAGE FROM SERVICE");
        resourceRepository.appendMessage(message);
    }

    public List<Message> getMessages() {
        log.info("GET MESSAGES FROM SERVICE");
        return resourceRepository.getMessages();
    }

    public void clearResource() {
        resourceRepository.empty();
    }
}
