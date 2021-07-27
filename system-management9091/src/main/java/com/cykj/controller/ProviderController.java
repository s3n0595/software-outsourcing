package com.cykj.controller;

import com.cykj.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/7/27 10:04
 * @desc:
 */
@Controller
@RequestMapping("/provider")
public class ProviderController {
	@Autowired
    private ProviderService providerService;

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> providerList(int page, String info){
        System.out.println(page);
        System.out.println("info : " + info);
        System.out.println(providerService.queryAll());
        return providerService.queryAll();
    }
}
