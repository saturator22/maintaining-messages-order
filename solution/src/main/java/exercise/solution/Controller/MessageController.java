package exercise.solution.Controller;

import exercise.solution.Model.Commit;
import exercise.solution.Service.CommitService;
import exercise.solution.Service.ResourceService;
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

    @Async("asyncWorker")
    @PostMapping("/message")
    public @ResponseBody CompletableFuture<String> storeMessage(@RequestBody Commit commit) {
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
