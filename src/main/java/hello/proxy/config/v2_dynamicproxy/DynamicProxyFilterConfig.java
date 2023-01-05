package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicFilterHandler;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        final OrderControllerV1 orderService = new OrderControllerV1Impl(orderService(logTrace));
        final LogTraceBasicFilterHandler handler = new LogTraceBasicFilterHandler(orderService, logTrace, PATTERNS);
        final OrderControllerV1 proxy = (OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class}, handler);
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        final OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepository(logTrace));
        final LogTraceBasicFilterHandler handler = new LogTraceBasicFilterHandler(orderService, logTrace, PATTERNS);
        final OrderServiceV1 proxy = (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[]{OrderServiceV1.class}, handler);
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        final OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
        final LogTraceBasicFilterHandler handler = new LogTraceBasicFilterHandler(orderRepository, logTrace, PATTERNS);
        final OrderRepositoryV1 proxy = (OrderRepositoryV1)Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, handler);
        return proxy;
    }
}
