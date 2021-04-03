package com.mybatis.spring.fastdfs.controller;

import com.mybatis.spring.fastdfs.client.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传接口
 */
@Controller
@PropertySource("classpath:config.properties")
public class PictureController {
    @Value("${file_server_addr}")
    private String IMAGE_SERVER_URL;
    private static FastDFSClient fastDFSClient = new FastDFSClient();

    /**
     * 文件上传
     * @param uploadFile
     * @param request
     * @return
     */
    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map fileUpload(MultipartFile uploadFile, HttpServletRequest request) {
        try {
            //1、取文件的扩展名
            String filepath = fastDFSClient.uploadFileWithMultipart(uploadFile);
            //4、拼接返回的url和ip地址，拼装成完整的url
            String url = IMAGE_SERVER_URL + filepath;
            //5、返回map
            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //5、返回map
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return result;
        }
    }


}
