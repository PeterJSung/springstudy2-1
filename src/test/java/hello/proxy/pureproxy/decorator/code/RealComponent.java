package hello.proxy.pureproxy.decorator.code;

import hello.proxy.pureproxy.proxy.code.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {

    @Override
    public String operation() {
        log.info("Real Component 실행");
        return "Data";
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
