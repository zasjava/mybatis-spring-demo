package com.mybatis.spring.fastdfs.controller;

import com.mybatis.spring.fastdfs.client.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@Controller
@PropertySource("classpath:config.properties")
public class PictureController {
    @Value("${file_server_addr}")
    private String IMAGE_SERVER_URL;
    private static FastDFSClient fastDFSClient = new FastDFSClient();

    //1.获取到上传的文件
    //2.调用fastDFS的工具类,实现上传
    //3.上传成功后,我们获取到返回的字符串路径
    //4.我们把字符串进行拼接    http://192.168.177.139/+返回的字符串
    //5.返回json数据    如果成功的话  返回 {error:"0",url:"http://192.168.177.139/+返回的字符串"}
    //6.如果失败的话:   返回{error:"1",message:"错误信息"}
    @RequestMapping("/pic/upload")
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
