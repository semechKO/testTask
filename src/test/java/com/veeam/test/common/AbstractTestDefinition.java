package com.veeam.test.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TestContextConfiguration.class})
@SpringBootTest
@Slf4j
abstract public class AbstractTestDefinition {
    protected static Logger logger = LoggerFactory.getLogger(AbstractTestDefinition.class);
}