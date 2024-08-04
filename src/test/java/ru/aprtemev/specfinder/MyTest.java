package ru.aprtemev.specfinder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
public class MyTest {

    //todo remove

    @Test
    public void init() {
        BigDecimal i = new BigDecimal("5.5");
        Object o = (Object) i;
        log.info(o.toString());
    }
}
