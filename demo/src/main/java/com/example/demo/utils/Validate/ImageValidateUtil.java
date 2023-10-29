package com.example.demo.utils.Validate;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片校验工具类
 */
public class ImageValidateUtil {
    /**
     * 验证上传的图片是否合法。
     *
     * @param image 上传的图片文件
     *
     * @return true 如果图片合法，否则 false
     */
    public static boolean validateImage(MultipartFile image) {
        // 调用两个验证方法，分别验证图片大小和图片类型
        return isSizeValid(image) && isTypeValid(image);
    }

    /**
     * 验证文件大小是否在合法范围内。
     *
     * @param image 上传的图片文件
     *
     * @return true 如果图片大小合法，否则 false
     */
    public static boolean isSizeValid(MultipartFile image) {
        // 验证文件大小是否超出限制
        long maxSizeBytes = 5 * 1024 * 1024; // 5MB
        return image.getSize() <= maxSizeBytes;
    }

    /**
     * 验证文件内容类型是否为合法的图片类型。
     *
     * @param image 上传的图片文件
     *
     * @return true 如果是合法的图片类型，否则 false
     */
    public static boolean isTypeValid(MultipartFile image) {
        String contentType = image.getContentType();
        // 定义合法的图片类型
        String[] validTypes = {"image/jpeg", "image/png", "image/gif"};

        if (contentType != null) {
            // 检查内容类型是否在合法的图片类型列表中
            for (String validType : validTypes) {
                if (contentType.equals(validType)) {
                    return true;
                }
            }
        }
        return false;
    }
}
