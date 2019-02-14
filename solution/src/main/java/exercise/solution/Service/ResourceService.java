package exercise.solution.Service;

import exercise.solution.Model.Message;
import exercise.solution.Repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    public void storeMessage(Message message) {
        resourceRepository.appendMessage(message);
    }

    public List<Message> getMessages() {
        return resourceRepository.getMessages();
    }

    public void clearResource() {
        resourceRepository.empty();
    }
}
