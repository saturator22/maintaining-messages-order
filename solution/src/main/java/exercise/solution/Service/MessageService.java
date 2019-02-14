package exercise.solution.Service;

import exercise.solution.Model.Message;
import exercise.solution.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public void storeMessage(Message message) {
        messageRepository.appendMessage(message);
    }
}
