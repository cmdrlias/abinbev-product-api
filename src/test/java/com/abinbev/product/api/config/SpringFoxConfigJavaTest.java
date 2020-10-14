package com.abinbev.product.api.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SpringFoxConfigJavaTest {

    @Spy
    SpringFoxConfig springFoxConfig;

    @Test
    public void api() {
        Docket response = springFoxConfig.api();
        assertNotNull(response);
    }
}
