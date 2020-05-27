package cn.controller;

import cn.service.MyServiceInf;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 喻浩
 * @create 2020-05-26-6:52
 */
@RestController
public class MyController {
    @Reference
    private MyServiceInf myServiceInf;

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable String msg){
        return myServiceInf.echo(msg);
    }
}
