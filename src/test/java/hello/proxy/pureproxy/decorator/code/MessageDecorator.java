package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator {

    public MessageDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("Message Decorator 실행");
        String result = super.operation();
        String decoResult = "***" + result + "***";
        log.info("Message Decorator 꾸미기 before {} after {}", result, decoResult);
        return decoResult;
    }
}
