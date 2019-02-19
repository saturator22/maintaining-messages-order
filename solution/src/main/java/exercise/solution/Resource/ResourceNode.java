package exercise.solution.Resource;

import exercise.solution.Model.Commit;

import java.util.concurrent.atomic.AtomicMarkableReference;

class ResourceNode {

    Commit key;
    AtomicMarkableReference<ResourceNode> next;

    ResourceNode(Commit key, ResourceNode next) {
        this.next = new AtomicMarkableReference<>(next, false);
        this.key = key;
    }

}
