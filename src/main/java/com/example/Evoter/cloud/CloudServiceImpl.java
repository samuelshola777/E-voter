//package com.example.Evoter.cloud;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.example.Evoter.voter.exception.ImageUploadException;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//
//import java.util.Map;
//
//@Service
//@Slf4j
//public class CloudServiceImpl implements CloudInterface{
//    @Autowired
//    private Cloudinary cloudinary;
//    @Override
//    public String upload(MultipartFile image) {
//        try{
//            Map<?,?> response =
//                    cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
//            log.info("response::{}",response);
//            return response.get("url").toString();
//        } catch (IOException e) {
//            throw new ImageUploadException(e.getMessage() );
//        }
//    }
//    }
