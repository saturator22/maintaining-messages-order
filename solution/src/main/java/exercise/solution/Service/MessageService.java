package exercise.solution.Service;

import exercise.solution.Model.Message;
import exercise.solution.Repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    ResourceService resourceService;

    public void storeMessage(Message message) {
        resourceService.storeMessage(message);
    }
}
