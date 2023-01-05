package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class ServiceImpl implements ServiceInterface {

    @Override
    public void save() {
        log.info("Save call");
    }

    @Override
    public void find() {
        log.info("Find call");
    }
}
