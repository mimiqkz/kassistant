package teymi15.kassistant.service;

import teymi15.kassistant.model.Recipe;

import java.util.List;

/**
 *
 * @author Ebba Þóra Hvannberg
 * @date september 2017 
 * HBV501G Hugbúnaðarverkefni 1 Háskóli Íslands
 *
 * Þjónusta sem athugar hvort nafn er á réttu formi
 *
 */
public interface RecipeService {

    /**
     * Athugar hvort nafn er á réttu formi, þ.e. verða að vera a.m.k. tvö orð.
     *
     * @param nafn
     * @return skilar satt ef nafn er á réttu formi annars ósatt
     */
    public boolean isNameCorrect(String nafn);

    /**
     * Bætir við kennara í kennariRep
     *
     * @param k Kennari
     */
    public void addRecipe(Recipe k);

    /**
     * Skilar öllum kennurum í kennariRep
     *
     * @return listi af kennurum
     */
    public List<Recipe> allirKennarar();
}
