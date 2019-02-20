package exercise.solution.Resource;

import exercise.solution.Model.Commit;

import java.util.concurrent.atomic.AtomicMarkableReference;

class ResourceNode {

    private Commit key;
    private AtomicMarkableReference<ResourceNode> next;

    ResourceNode(Commit key, ResourceNode next) {
        this.next = new AtomicMarkableReference<>(next, false);
        this.key = key;
    }

    public Commit getKey() {
        return key;
    }

    public AtomicMarkableReference<ResourceNode> getNext() {
        return next;
    }
}
