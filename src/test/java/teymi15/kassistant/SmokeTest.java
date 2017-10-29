package teymi15.kassistant;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teymi15.kassistant.control.MainController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
    @Autowired
    MainController mainController;

    public SmokeTest(){}

    @Test
    public void contextLoads(){
        Assertions.assertThat(this.mainController).isNotNull();
    }
}
