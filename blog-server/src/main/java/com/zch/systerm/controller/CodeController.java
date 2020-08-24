package com.zch.systerm.controller;


import com.zch.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class CodeController {
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/Code")
    public MyResponse getCode(HttpServletRequest request) {
        VerificationCodeUtil codeUtil = new VerificationCodeUtil(140, 40, 4, 30);
        BufferedImage bufferedImage = codeUtil.getBufferedImage();
        String code = codeUtil.getCode();
        String uuid = UUID.randomUUID().toString();
        String yanzhengmaKey = Constant.VERFEY_CODE_KEY + uuid;
        redisCache.setCacheObject(yanzhengmaKey, code, 2, TimeUnit.MINUTES);
        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();
        MyResponse myResponse = MyResponse.success();
        try {
            ImageIO.write(bufferedImage, "jpg", fastByteArrayOutputStream);
        } catch (Exception e) {
            return MyResponse.error(e.getMessage());
        }
        myResponse.put("uuid", uuid);
        myResponse.put("img", Base64.encode(fastByteArrayOutputStream.toByteArray()));
        return myResponse;
    }

}
