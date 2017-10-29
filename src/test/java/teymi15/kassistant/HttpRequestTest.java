package teymi15.kassistant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Alexander Freyr Sveinsson
 * @date 29 október 2017
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 *
 * Test class that sends URL on a test web client
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void heimaSkilarName(){
                assertThat(this.testRestTemplate.getForObject("http://localhost:" +port +"/",
                        String.class)).contains("search");
    }
}
