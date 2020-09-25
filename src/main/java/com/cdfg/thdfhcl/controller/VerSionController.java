package com.cdfg.thdfhcl.controller;

import com.cdfg.thdfhcl.pojo.until.Result;
import jdk.internal.util.xml.impl.Input;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@CrossOrigin
@RestController
@RequestMapping("cdfg")
public class VerSionController {
    @RequestMapping(value = "qryversion",produces="json/html; charset=UTF-8")
    @ResponseBody
    public String qryVersion(){
        byte[] buffer= new byte[1000];
        String srt = "";
        try {
            ClassPathResource classPathResource = new ClassPathResource("version-control.txt");
            InputStream inputstream = classPathResource.getInputStream();
            int c = inputstream.read(buffer);
            srt += new String(buffer,0,c);

            System.out.println(srt);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return srt;
    }
}
