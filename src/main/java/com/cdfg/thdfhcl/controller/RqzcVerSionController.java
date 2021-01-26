package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.RqzcEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;
import static com.cdfg.thdfhcl.pojo.until.Constant.sucMsg;

@CrossOrigin
@RestController
@RequestMapping("cdfg")
public class RqzcVerSionController {
    Logger logger = Logger.getLogger(RqzcVerSionController.class);

    @PostMapping("qryRqzcVersion")
    @ResponseBody
    public Result<RqzcEntity> qryVersion(){

//        byte[] buffer= new byte[8000];
        RqzcEntity orderInfoBatch;
        InputStream inputStream = null;
        try {
            ApplicationHome applicationHome = new ApplicationHome(this.getClass());
            File file = applicationHome.getDir();
            String route = file.getAbsolutePath();//文件相对路径

            //文件绝对路径
//            String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//            System.out.println(path);
            File txtfile = new File(route+ "/SysteminitialConfig.json");
            inputStream=new FileInputStream(txtfile);
//            inputStream.read(buffer);
            ObjectMapper mapper = new ObjectMapper();
//             mapper.readValue(buffer, RqzcEntity.class);
            orderInfoBatch = mapper.readValue(inputStream,RqzcEntity.class);
            inputStream.close();
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("获取参数字典文件内容异常");
            throw new ThdfhclNotFoundException(errCode_34,errMsg_34);
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("关闭输出流错误！", e);
                }
            }
        }
        return new Result<RqzcEntity>(sucCode,sucMsg,orderInfoBatch);
    }
}
