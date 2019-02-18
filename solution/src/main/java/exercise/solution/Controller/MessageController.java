package exercise.solution.Controller;

import exercise.solution.Model.Message;
import exercise.solution.Model.Messageable;
import exercise.solution.Service.MessageService;
import exercise.solution.Service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    ResourceService resourceService;

    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    @Async("asyncWorker")
    @PostMapping("/message")
    public void storeMessage(@RequestBody Message message) {
        log.info("STORE MESSAGE STARTED CONTROLLER WITH MESSAGE: " + message);
        resourceService.storeMessage(message);
    }

    @Async("asyncWorker")
    @GetMapping("/resource")
    public @ResponseBody
    CompletableFuture<String> getMessages() {
        List<Messageable> messageList = resourceService.getMessages();
        log.info("GET MESSAGES CONTROLLER WITH STATE : " + messageList);

        StringBuilder sb = new StringBuilder();
        for(Messageable message1 : messageList) {
            sb.append(message1.getMessage());
        }

        return CompletableFuture.completedFuture(sb.toString());
    }

}
