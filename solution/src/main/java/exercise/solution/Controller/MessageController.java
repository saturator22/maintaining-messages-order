package exercise.solution.Controller;

import exercise.solution.Model.Commit;
import exercise.solution.Service.CommitService;
import exercise.solution.Service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class MessageController{

    @Autowired
    CommitService commitService;

    @Autowired
    ResourceService resourceService;

    private static Logger log = LoggerFactory.getLogger(MessageController.class);

    @Async("asyncWorker")
    @PostMapping("/message")
    public @ResponseBody CompletableFuture<String> storeMessage(@RequestBody Commit commit) {
        log.info("STORE MESSAGE STARTED CONTROLLER WITH MESSAGE: " + commit);
        commitService.storeMessage(commit);
        return getState();
    }

    @Async("asyncWorker")
    @GetMapping("/resource")
    public @ResponseBody
    CompletableFuture<String> getState() {
        String state = resourceService.getMessages();

        return CompletableFuture.completedFuture(state);
    }

}
