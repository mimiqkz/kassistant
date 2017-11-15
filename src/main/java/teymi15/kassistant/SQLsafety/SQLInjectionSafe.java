package teymi15.kassistant.SQLsafety;
/**
 * The program allows user to search for recipe with the matching name.
 * For example, "Apple pie", "Brocoli soup", "Chocolate Cake" , etc.
 *
 * @author Nu Phan Quynh Do
 * @author  Alexander Freyr Sveinsson
 * @author Alexandra Mjöll Young
 * @version 1.3
 * @since   2017-11-01
 */
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Make sure that everything which is added into the database is safe
 */
@Documented
@Constraint(validatedBy = SQLInjectionSafeConstraintValidator.class)
@Target( {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInjectionSafe {
    String message() default "{SQLInjectionSafe}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
