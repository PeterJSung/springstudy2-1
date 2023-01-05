package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello hello = new Hello();
        //1
        log.info("Start");
        String result1 = hello.callA();
        log.info("result={}", result1);

        //2
        log.info("Start");
        String result2 = hello.callB();
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        Method methodCallB = classHello.getMethod("callB");

        dynamicCall(methodCallA, target);
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("Start");
        Object result1 = method.invoke(target);
        log.info("result={}", result1);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("Called A");
            return "A";
        }

        public String callB() {
            log.info("Called B");
            return "B";
        }
    }

}
