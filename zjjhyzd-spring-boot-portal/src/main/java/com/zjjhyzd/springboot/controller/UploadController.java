package com.zjjhyzd.springboot.controller;

import com.zjjhyzd.springboot.config.UploadConfig;
import com.zjjhyzd.springboot.factory.ResponseEntityFactory;
import com.zjjhyzd.springboot.model.DataModel;
import com.zjjhyzd.springboot.utils.UploadUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/upload")
public class UploadController {
    @Autowired
    UploadConfig uploadConfig;

    @PostMapping("/image")
    public ResponseEntity<DataModel> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam(required = false, defaultValue = "0") Integer compress) {
        String originalFilename = file.getOriginalFilename();
        String basePath = uploadConfig.getBasePath();
        String domain = uploadConfig.getDomain();

        file.getContentType();
        try {
            String savePath = UploadUtil.generatorSavePath(basePath, "image", originalFilename);
            File saveFile = new File(basePath + savePath);
            if (1 == compress) {
                Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.25f).toFile(saveFile);
            } else {
                file.transferTo(saveFile);
            }
            return ResponseEntityFactory.success(domain + savePath.replaceAll("\\\\", "/"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntityFactory.error("系统异常", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/image-batch")
    public ResponseEntity<DataModel> uploadImageBatch(@RequestParam("file") MultipartFile[] files, @RequestParam(required = false, defaultValue = "0") Integer compress) {
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String originalFilename = file.getOriginalFilename();
            String basePath = uploadConfig.getBasePath();
            String domain = uploadConfig.getDomain();
            try {
                String savePath = UploadUtil.generatorSavePath(basePath, "image", originalFilename);
                File saveFile = new File(basePath + savePath);
                if (1 == compress) {
                    Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.25f).toFile(saveFile);
                } else {
                    file.transferTo(saveFile);
                }
                result[i] = domain + savePath.replaceAll("\\\\", "/");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntityFactory.error("系统异常", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return ResponseEntityFactory.success(result);
    }

    @PostMapping("/video")
    public ResponseEntity<DataModel> uploadvideo(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String basePath = uploadConfig.getBasePath();
        String domain = uploadConfig.getDomain();
        try {
            String savePath = UploadUtil.generatorSavePath(basePath, "video", originalFilename);
            File saveFile = new File(basePath + savePath);
            file.transferTo(saveFile);
            return ResponseEntityFactory.success(domain + savePath.replaceAll("\\\\", "/"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntityFactory.error("系统异常", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/audio")
    public ResponseEntity<DataModel> uploadImage(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String basePath = uploadConfig.getBasePath();
        String domain = uploadConfig.getDomain();
        try {
            String savePath = UploadUtil.generatorSavePath(basePath, "audio", originalFilename);
            File saveFile = new File(basePath + savePath);
            file.transferTo(saveFile);
            return ResponseEntityFactory.success(domain + savePath.replaceAll("\\\\", "/"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntityFactory.error("系统异常", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/file")
    public ResponseEntity<DataModel> uploadFile(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String basePath = uploadConfig.getBasePath();
        String domain = uploadConfig.getDomain();
        try {
            String savePath = UploadUtil.generatorSavePath(basePath, "file", originalFilename);
            File saveFile = new File(basePath + savePath);
            file.transferTo(saveFile);
            return ResponseEntityFactory.success(domain + savePath.replaceAll("\\\\", "/"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntityFactory.error("系统异常", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
