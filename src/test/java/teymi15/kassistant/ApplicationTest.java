package teymi15.kassistant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 *
 * @author Alexander Freyr Sveinsson
 * @date október 2017
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 *
 * a test class that performs tests without calling the server
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
    // Server (Tomcat) not made to run
    @Autowired
    private MockMvc mockMvc;
    /**
     * Class to test if it works to send a HttpRequest to /create-ingredient
     * and to see if we get back createIngredient.html page that has the String name
     */
    @Test
    public void newIngretientretReturnsRecipe() throws Exception {
        this.mockMvc.perform(get("/create-ingredient"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("name")));

    }

}
