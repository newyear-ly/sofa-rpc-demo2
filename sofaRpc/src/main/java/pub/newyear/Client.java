package pub.newyear;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import pub.newyear.service.HelloService;

public class Client {
    public static void main(String[] args) {
        // 1. 注册中心配置
        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol("sofa") // 设置协议
                .setAddress("127.0.0.1:9603"); // 设置注册中心 session 地址

        // 2. consumer 综合配置
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()) // 指定接口
                .setRegistry(registryConfig)
                .setProtocol("bolt") // 指定协议
                .setConnectTimeout(10 * 1000);

        // 3. 构造服务引用
        HelloService helloService = consumerConfig.refer();

        Class test = helloService.getClass();
        // 4. 进行服务调用
        while (true){
            System.out.println(helloService.sayHello("555"));
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
            }
        }
    }
}

