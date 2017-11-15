package teymi15.kassistant.service;

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

import org.springframework.stereotype.Service;

/**
 * The class which sees to add the photo file into the data base
 */
@Service
public interface PhotoService {
    /**
     *
     * @param file
     * @return
     */
    String addPhoto(byte[] file);
}
