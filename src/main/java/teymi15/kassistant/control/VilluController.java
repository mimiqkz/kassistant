/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teymi15.kassistant.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author Alexander Freyr Sveinsson
 */
@Controller
public class VilluController implements ErrorController {
    
    private static final String PATH = "/error";
    
    @Autowired
    private ErrorAttributes errorAttributes;
    
/**
 * handles the error form the web
 * @param request
 * @return
 */    
    @RequestMapping(value = PATH)
    public ModelAndView villa(HttpServletRequest request) {
     
        // sendir attribute til viðmótsins og birtir síðuna með villuskilaboðum  
        return new ModelAndView("errorpage", "attrs", getErrorAttributes(request, false));
    }
    
    /**
     * Returns  the path to the error
    */
    @Override
    public String getErrorPath() {
        return PATH;
    } 
    
    
    /**
     * gets the error-attribute that came from the Http request
     * @param request
     * @param includeStackTrace if true then return the trace
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
			boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return this.errorAttributes.getErrorAttributes(requestAttributes,
				includeStackTrace);
	}
    
}

