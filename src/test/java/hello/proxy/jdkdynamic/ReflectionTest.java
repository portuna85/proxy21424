package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        Hello target = new Hello();

        log.info("START");

        String resultA = target.callA();
        log.info("resultA = {}", resultA);

        String resultB = target.callB();
        log.info("resultB = {}", resultB);
    }

    @Test
    void reflection1() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        Object resultA = methodCallA.invoke(target);
        log.info("resultA = {}", resultA);

        Method methodCallB = classHello.getMethod("callB");
        Object resultB = methodCallB.invoke(target);
        log.info("resultB = {}", resultB);
    }

    @Test
    void reflection2() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);
        Object resultA = methodCallA.invoke(target);
        log.info("resultA = {}", resultA);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
        Object resultB = methodCallB.invoke(target);
        log.info("resultB = {}", resultB);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("START");

        Object result = method.invoke(target);
        log.info("result = {}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("CallA");
            return "A";
        }

        public String callB() {
            log.info("CallB");
            return "B";
        }
    }
}