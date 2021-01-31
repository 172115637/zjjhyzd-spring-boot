package com.zjjhyzd.springboot.utils;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

import java.io.File;

public class UploadUtil {
    /**
     * 将文件名重命名为唯一名
     *
     * @param filename
     * @return
     */
    public static String covertFilename(String filename) {
        return IdUtil.objectId() + filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    public static String generatorSavePath(String basePath, String type, String filename) {
        if (!FileUtil.exist(basePath + File.separator + type)) {
            FileUtil.mkdir(basePath + File.separator + type);
        }
        return File.separator + type +
                File.separator + covertFilename(filename);
    }
}
