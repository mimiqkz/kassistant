package teymi15.kassistant;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import teymi15.kassistant.control.RecipeController;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.service.RecipeService;

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
    RecipeService recipeService;

    @Test
    public void testSelectNonExistentRecipe() throws Exception {
        Recipe t = null;
        when(recipeService.getRecipeById(0)).thenReturn(t);
        this.mockMvc.perform(get("recipe/0"))
                .andDo(print())
                .andExpect(status()
                .isOk()).andExpect(content().string(containsString("Non existent recipe")));
    }
}
