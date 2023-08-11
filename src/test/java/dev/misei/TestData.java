package dev.misei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@TestComponent
public class TestData {
    @Value("classpath:organizationFirstTime.json")
    Resource organizationFirstTime;
    @Value("classpath:userJoin.json")
    Resource userJoin;
}
