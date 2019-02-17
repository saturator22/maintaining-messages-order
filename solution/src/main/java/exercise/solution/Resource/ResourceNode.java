package exercise.solution.Resource;

import exercise.solution.Model.Messageable;

import java.util.concurrent.atomic.AtomicMarkableReference;

class ResourceNode {

    Messageable key;
    AtomicMarkableReference<ResourceNode> next;

    ResourceNode(Messageable key, ResourceNode next) {
        this.next = new AtomicMarkableReference<>(next, false);
        this.key = key;
    }

}
