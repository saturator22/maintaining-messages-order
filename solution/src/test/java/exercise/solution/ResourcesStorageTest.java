package exercise.solution;


import exercise.solution.Resource.ResourceNode;
import exercise.solution.Resource.ResourcesStorage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.gen5.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourcesStorageTest {
    ResourcesStorage<Integer> resourcesStorage;

    @Before
    public void setup() {
        this.resourcesStorage = new ResourcesStorage<>();

        int[] testCases = {50, 40, 60, 35, 65, 45, 55, 52, 56, 51};

        for(int i : testCases) {
            resourcesStorage.appendNode(i);
        }
    }

//    @Test
//    public void sizeTest() {
//        assertEquals(0, resourcesStorage.size());
//
//        String[] testCases = {"A", "B", "C", "D", "E", "F"};
//        for(String s : testCases) {
//            resourcesStorage.appendNode(s);
//        }
//
//        assertEquals(6, resourcesStorage.size());
//    }

    @Test
    public void appendNodeTest() {
        assertEquals(10, resourcesStorage.size());
    }

    @Test
    public void searchTest() {

        Integer a = 40;
        Integer b = 35;

        assertAll(
                () -> assertEquals(a, resourcesStorage.getItem(40)),
                () -> assertEquals(b, resourcesStorage.getItem(35)),
                () -> assertNull(null, resourcesStorage.getItem(0))
        );
    }

    @Test
    public void rotatingTest() {
        ResourceNode<Integer> root = resourcesStorage.getNode(55);
        ResourceNode<Integer> rightLeaf = resourcesStorage.getNode(60);


        assertEquals(root.getLeftLeaf(), resourcesStorage.getNode(50));
        assertEquals(root.getRightLeaf(), resourcesStorage.getNode(52));
        assertEquals(rightLeaf.getParentNode(), root);
        assertEquals(rightLeaf.getLeftLeaf(), resourcesStorage.getNode(56));
        assertEquals(rightLeaf.getRightLeaf(), resourcesStorage.getNode(65));

    }
}
