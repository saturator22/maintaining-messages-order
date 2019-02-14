package exercise.solution.Repository;

import exercise.solution.Model.Message;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ResourceRepository {

    Set<Message> messageSet = new TreeSet<>();

    public void appendMessage(Message message) {
        this.messageSet.add(message);
    }

    public List<Message> getMessages() {
        List<Message> messageList = new ArrayList<>();
        Iterator<Message> iterator = this.messageSet.iterator();

        while(iterator.hasNext()) {
            messageList.add(iterator.next());
        }

        return messageList;
    }

    public void empty() {
        this.messageSet.clear();
    }
}
