package io.github.sruby.basicjava.override;

import org.junit.Assert;
import org.junit.Test;

public class ParentImplTest2 {
    ParentImpl parentImpl = new ParentImpl();

    @Test
    public void testA() throws Exception {
        Integer result = parentImpl.a();
        Assert.assertEquals(Integer.valueOf(0), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme