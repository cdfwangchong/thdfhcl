package com.cdfg.thdfhcl.controller;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.ThdfhclNotFoundException;
import com.cdfg.thdfhcl.pojo.until.Result;
import com.cdfg.thdfhcl.pojo.until.VersionEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.cdfg.thdfhcl.pojo.until.Constant.*;

@CrossOrigin
@RestController
@RequestMapping("cdfg")
public class VerSionController {

    Logger logger = Logger.getLogger(VerSionController.class);

    @PostMapping("qryversion")
    @ResponseBody
    public Result<VersionEntity> qryVersion(){

        byte[] buffer= new byte[1000];
        VersionEntity orderInfoBatch = null;

        try {
            ApplicationHome applicationHome = new ApplicationHome(this.getClass());
            File file = applicationHome.getDir();
            String route = file.getAbsolutePath();

            String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            File txtfile = new File(route+"/version-control.txt");
            InputStream inputStream=new FileInputStream(txtfile);
            inputStream.read(buffer);
            ObjectMapper mapper = new ObjectMapper();
            orderInfoBatch = mapper.readValue(buffer, VersionEntity.class);

        } catch (IOException e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("获取version-control.txt文件内容异常");
            throw new ThdfhclNotFoundException(errCode_7,errMsg_7);
        }

        return new Result<VersionEntity>(sucCode,sucMsg,orderInfoBatch);
    }
}