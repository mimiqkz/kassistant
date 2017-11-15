package teymi15.kassistant.service;

import org.springframework.stereotype.Service;

@Service
public interface PhotoService {
    /**
     *
     * @param file
     * @return
     */
    String addPhoto(byte[] file);
}
