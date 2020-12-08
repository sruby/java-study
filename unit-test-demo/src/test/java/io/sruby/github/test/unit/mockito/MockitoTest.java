package io.sruby.github.test.unit.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-08 08:58
 */

public class MockitoTest {
    @Test
    public void testVerify(){
        List mockList = mock(List.class);
        mockList.add("one");
        mockList.clear();
        verify(mockList).add("one");
        verify(mockList).clear();
    }
}
