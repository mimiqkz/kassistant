package teymi15.kassistant.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PhotoServiceImp implements PhotoService{
    @Override
    public String addPhoto(byte[] file) {
        Cloudinary cloudinary = new Cloudinary("cloudinary://831862946291534:rQ-uBNkMt75GfFrslr29elXgroA@kassistant");
        Map imageResultMap = null;
        String s = "";
        try{
            imageResultMap =cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            s =imageResultMap.get("secure_url").toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }
}
