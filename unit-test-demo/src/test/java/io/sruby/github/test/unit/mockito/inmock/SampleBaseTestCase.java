package io.sruby.github.test.unit.mockito.inmock;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class SampleBaseTestCase {

       @Before
       public void initMocks() {
           MockitoAnnotations.initMocks(this);
       }
   }
 