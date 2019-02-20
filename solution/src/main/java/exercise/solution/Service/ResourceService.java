package exercise.solution.Service;

import exercise.solution.Model.Commit;
import exercise.solution.Repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    public void storeMessage(Commit commit) {
        resourceRepository.appendMessage(commit);
    }

    public String getMessages() {
        return resourceRepository.getMessages();
    }
}
