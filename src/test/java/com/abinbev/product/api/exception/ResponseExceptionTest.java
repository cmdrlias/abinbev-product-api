package com.abinbev.product.api.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class ResponseExceptionTest {

    @Test(expected = ResponseException.class)
    public void ResponseExceptionWithMessage() {
        ResponseException ex = new ResponseException("Test Exception");
        throw ex;
    }

    @Test(expected = ResponseException.class)
    public void ResponseExceptionWithMessageAndStatus() {
        ResponseException ex = new ResponseException("Test Exception", HttpStatus.BAD_GATEWAY);
        throw ex;
    }

    @Test(expected = ResponseException.class)
    public void ResponseExceptionWithMessageThrowableAndStatus() {
        ResponseException ex = new ResponseException("Test Exception", new Throwable(), HttpStatus.BAD_GATEWAY);
        throw ex;
    }
}