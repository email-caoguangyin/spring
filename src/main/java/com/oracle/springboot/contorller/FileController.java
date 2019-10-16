package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.FileModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileModel upload(){

        FileModel fileModel = new FileModel();
        fileModel.setSuccess(1);
        fileModel.setUrl("/avatar_url/timg.jpg");

        return fileModel;

    }
}
