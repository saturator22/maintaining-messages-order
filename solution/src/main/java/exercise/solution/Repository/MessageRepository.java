package exercise.solution.Repository;

import exercise.solution.Model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {

    List<Message> messageList = new ArrayList<>();

    public void appendMessage(Message message) {
        this.messageList.add(message);
    }
}
