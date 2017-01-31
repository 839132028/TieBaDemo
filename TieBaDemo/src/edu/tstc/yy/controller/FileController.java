package edu.tstc.yy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.tstc.yy.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by w_2 on 2016-11-13.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/imageUpload.do")
    @ResponseBody
    public String imageUpload(@RequestParam("image")MultipartFile[] files){
        JSONObject jsonObject=new JSONObject();
        List<String> arrayList=new ArrayList<>();
        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                arrayList.add(saveFile(file));
            }
        }
        return JSON.toJSONString(arrayList);
    }

    private String saveFile(MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String fileName=System.currentTimeMillis()+file.getOriginalFilename();
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/") + "fileUpload/"
                        + fileName;
                // 转存文件
                file.transferTo(new File(filePath));
                String path="fileUpload/" + fileName;
                return path;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ReturnCode.IMAGE_UPLOAD_ERROR;
    }
}
