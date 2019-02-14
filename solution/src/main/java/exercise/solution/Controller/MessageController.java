package exercise.solution.Controller;

import exercise.solution.Model.Message;
import exercise.solution.Service.MessageService;
import exercise.solution.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    ResourceService resourceService;

    @PostMapping("/message")
    public void storeMessage(@RequestBody Message message) {
        messageService.storeMessage(message);
    }

    @GetMapping("/resource")
    public @ResponseBody List<Message> getMessages() {
        return resourceService.getMessages();
    }

    @DeleteMapping("/empty")
    public void clearMessages() {
        resourceService.clearResource();
    }
}
