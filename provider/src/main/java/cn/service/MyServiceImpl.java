package cn.service;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author 喻浩
 * @create 2020-05-26-6:45
 */
@Service
public class MyServiceImpl implements MyServiceInf {
    public String echo(String msg){
        System.out.println("ok asdasdsaas ");
        return "receive msg is "+ msg;
    }
}
