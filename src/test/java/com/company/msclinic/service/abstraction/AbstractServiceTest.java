package com.company.msclinic.service.abstraction;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
public abstract class AbstractServiceTest {
}
