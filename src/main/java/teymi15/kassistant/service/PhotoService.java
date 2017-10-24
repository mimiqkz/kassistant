package teymi15.kassistant.service;

import org.springframework.stereotype.Service;

@Service
public interface PhotoService {
    String addPhoto(byte[] file);
}
