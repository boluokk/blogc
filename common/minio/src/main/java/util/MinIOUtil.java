package util;

import io.minio.*;
import io.minio.http.Method;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static util.MinIOConfig.*;

/**
 * MinIO 工具箱
 *
 * @author boluo
 * @date 2024/02/02
 */
@Slf4j
public class MinIOUtil {


    private static final MinioClient minioClient;

    static {
        minioClient = MinioClient.builder()
                .endpoint(URL)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 文件名称
     */
    public static String upload(MultipartFile multipartFile) {
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + multipartFile.getOriginalFilename();
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET)
                    .object(fileName)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());
            return fileName;
        } catch (Exception e) {
            log.warn("上传失败: {}", e);
        }
        return null;
    }

    /**
     * 覆盖原有文件
     *
     * @param fileName      文件名
     * @param multipartFile 文件
     * @return 文件名
     */
    public static String put(String fileName, MultipartFile multipartFile) {
        try {
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET)
                    .object(fileName)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());
            return fileName;
        } catch (Exception e) {
            log.warn("上传失败: {}", e);
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名字
     * @param res      响应
     */
    public static void download(String fileName, HttpServletResponse res) {
        InputStream in = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().build());
            IOUtils.copy(in, res.getOutputStream());
            res.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            res.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            res.flushBuffer();
        } catch (Exception e) {
            log.warn("下载文件失败: {}", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 预览文件
     *
     * @param fileName 文件名称
     * @return 文件链接
     */
    public static String preview(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(BUCKET)
                    .object(fileName)
                    .method(Method.GET)
                    .build());
        } catch (Exception e) {
            log.warn("文件链接获取失败: {}", e);
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @return 结果
     */
    public static boolean delete(String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .object(BUCKET)
                    .object(fileName)
                    .build());
            return true;
        } catch (Exception e) {
            log.warn("删除失败: {}", e);
        }
        return false;
    }
}
