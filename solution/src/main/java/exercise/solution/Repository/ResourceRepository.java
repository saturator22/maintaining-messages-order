package exercise.solution.Repository;

import exercise.solution.Controller.MessageController;
import exercise.solution.Model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ResourceRepository {

    Set<Message> messageSet = new TreeSet<>();

    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    public void appendMessage(Message message) {
        log.info("APPEND MESSAGE REPOSITORY");
        this.messageSet.add(message);
    }

    public List<Message> getMessages() {
        List<Message> messageList = new ArrayList<>();
        Iterator<Message> iterator = this.messageSet.iterator();

        while(iterator.hasNext()) {
            messageList.add(iterator.next());
        }
        log.info("GET MESSAGES FROM REPO");
        return messageList;
    }

    public void empty() {
        this.messageSet.clear();
    }
}
