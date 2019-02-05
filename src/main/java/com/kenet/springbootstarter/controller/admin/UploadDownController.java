package com.kenet.springbootstarter.controller.admin;

import com.kenet.springbootstarter.entity.Result;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UploadDownController {

    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {

        //相对于本机Tomcat服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload");
        // 图片存放的目录
        System.out.println("Tomact服务器文件位置path--->" + path);

//        项目工程下的路径
        path = "F:\\JavaDeveloper\\Spring Learning\\spring-boot-starter\\upload";
        File filePath = new File(path);

        System.out.println("文件保存的路径"+path);

        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录："+filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);

        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("新文件名称：" + fileName);


        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            return new Result(true,"/upload/" + fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new Result(false, "上传失败");
        }

    }

    /*
    * 指定文件名下载图片
    * */
    @RequestMapping(value = "/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable(value="fileName",required = false) String fileName,
                                           HttpServletRequest request) {
        try{
            //下载路径

            // 相对于本地Tomcat服务器
            String path = request.getServletContext().getRealPath("/resources/upload/");

            // 相对于项目工程根目录
            path = "F:\\JavaDeveloper\\Spring Learning\\spring-boot-starter\\upload";

            File file = new File(path + File.separator + fileName);
            HttpHeaders headers = new HttpHeaders();
            //解决文件名中文乱码问题
            String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
            //告诉浏览器以"attachment"方式打开文件
            headers.setContentDispositionFormData("attachment",downloadFileName);
            //设置请求头的媒体格式类型为 application/octet-stream(二进制流数据)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("文件下载出错...");
            return null;
        }
    }
}
