package exercise.solution.Resource;

import exercise.solution.Model.Messageable;

public class ResourceNode {

    Messageable key;
    ResourceNode next;

    ResourceNode(Messageable key) {
        this.key = key;
    }

    public Messageable getKey() {
        return key;
    }

    public void setKey(Messageable key) {
        this.key = key;
    }

    public ResourceNode getNext() {
        return next;
    }

    public void setNext(ResourceNode next) {
        this.next = next;
    }
}
