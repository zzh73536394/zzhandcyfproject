package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("queryservice")
public interface CreateHtml {
    @RequestMapping("createHtml")
    @ResponseBody
    Boolean saveProduct(@RequestParam("videoid") Integer videoid);
}
