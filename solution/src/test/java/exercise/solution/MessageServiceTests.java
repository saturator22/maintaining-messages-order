package exercise.solution;

import exercise.solution.Service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTests {

    @Autowired
    MessageService messageService;

    @Test
    public void storeInadequateObjectTest() {

    }
}
