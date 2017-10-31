package teymi15.kassistant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import teymi15.kassistant.control.IngredientController;
import teymi15.kassistant.service.IngredientService;
import teymi15.kassistant.service.IngredientServiceImp;
import teymi15.kassistant.service.PhotoServiceImp;
import teymi15.kassistant.service.UserServiceImp;

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
 * Prófunarklasi sem framkvæmir prófanir á weblayer og notar WebMvcTest
 */
@RunWith(SpringRunner.class)
/**
 *  only the specified class is run not the hole context
 *  we aske to only runn the IngredientController class
 */
@WebMvcTest(IngredientController.class)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IngredientServiceImp ingredientService;
    @MockBean
    PhotoServiceImp photoService;
    @MockBean
    UserServiceImp userService;
    /**
     * class that teks to run HttpRequest on /create-ingredient
     * and get back the CreateIngredient.html page
     */
    @Test
    public void newIngretientretReturnsName() throws Exception {
        this.mockMvc.perform(get("/create-ingredient"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("name")));
    }
}