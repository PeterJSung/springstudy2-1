package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        final OrderControllerV1 orderService = new OrderControllerV1Impl(orderService(logTrace));
        final LogTraceBasicHandler handler = new LogTraceBasicHandler(orderService, logTrace);
        final OrderControllerV1 proxy = (OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class}, handler);
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        final OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepository(logTrace));
        final LogTraceBasicHandler handler = new LogTraceBasicHandler(orderService, logTrace);
        final OrderServiceV1 proxy = (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[]{OrderServiceV1.class}, handler);
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        final OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
        final LogTraceBasicHandler handler = new LogTraceBasicHandler(orderRepository, logTrace);
        final OrderRepositoryV1 proxy = (OrderRepositoryV1)Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, handler);
        return proxy;
    }
}
