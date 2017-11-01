package teymi15.kassistant;


import teymi15.kassistant.control.RecipeController;
import teymi15.kassistant.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

// Athugið vel að þessi import séu rétt
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


/**
 *
 * @author Nu Phan Quynh Do
 * @date október 2017 HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 *
 * Prófunarklasi sem framkvæmir prófanir á webmocktest og notar WebMvcTest og
 * Mockito til að prófa service klasa
 */


@RunWith(SpringRunner.class)

@WebMvcTest(RecipeController.class)
public class WebMockTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RecipeServiceImp recipeService;
    @MockBean
    IngredientServiceImp ingredientService;
    @MockBean
    PhotoServiceImp photoService;
    @MockBean
    UserServiceImp userService;

    /**
     * To test whether the service is alive or not.
     * If the service is alive, then the page should have something
     * displayed as "Kitchen Assistant" as it should direct to the
     * homepage.
     * @throws Exception e
     */
    @Test
    public void testAlive() throws Exception {
        when(recipeService.isAlive()).thenReturn(true);
        this.mockMvc.perform(get("/alive"))
                .andDo(print())
                .andExpect(status()
                .isOk())
                .andExpect(content()
                        .string(containsString("Kitchen Assistant")));
    }
}
