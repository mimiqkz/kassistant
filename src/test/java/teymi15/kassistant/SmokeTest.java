package teymi15.kassistant;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teymi15.kassistant.control.MainController;
/**
 *
 * @author Alexander Freyr Sveinsson
 * @date október 2017
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 *
 * Test class that checks if mainController runs
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
    @Autowired
    MainController mainController;

    public SmokeTest(){}
    /**
     * Method to check if mainController exists
     */
    @Test
    public void contextLoads(){
        Assertions.assertThat(this.mainController).isNotNull();
    }
}
