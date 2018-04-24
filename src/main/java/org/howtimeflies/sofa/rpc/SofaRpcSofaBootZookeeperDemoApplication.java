package org.howtimeflies.sofa.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"rpc-server.xml","rpc-client.xml"}) // 引入加载的 spring xml
public class SofaRpcSofaBootZookeeperDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = 
                SpringApplication.run(SofaRpcSofaBootZookeeperDemoApplication.class, args);

        // 等待ZooKeeper下发地址
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        
        // 拿到调用端 进行 调用
        HelloService helloService = (HelloService) context.getBean("helloServiceRef");
        while (true) {
            try {
                String hi = helloService.sayHello("world");
                System.out.println(hi);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            
            }
        }
    }
}