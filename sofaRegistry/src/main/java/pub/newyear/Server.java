package pub.newyear;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import pub.newyear.service.HelloService;
import pub.newyear.service.HelloServiceImpl;

public class Server {
    public static void main(String[] args) {
        // 1. 注册中心配置
        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol("sofa") // 设置协议
                .setAddress("127.0.0.1:9603"); // 设置注册中心 session 地址

        // 2. 通信服务配置
        ServerConfig serverConfig = new ServerConfig()
                .setProtocol("bolt") // 设置一个协议，默认bolt
                .setPort(12200) // 设置一个端口，默认12200
                .setDaemon(false); // 非守护线程

        // 3. provider 综合配置
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()) // 指定接口
                .setRef(new HelloServiceImpl()) // 指定实现
                .setRegistry(registryConfig) // 指定注册中心
                .setServer(serverConfig); // 指定服务端

        // 4. 进行服务暴露
        providerConfig.export(); // 发布服务
    }
}

