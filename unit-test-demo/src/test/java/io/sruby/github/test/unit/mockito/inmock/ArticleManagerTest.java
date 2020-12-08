package io.sruby.github.test.unit.mockito.inmock;

import io.sruby.github.test.unit.inmock.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ArticleManagerTest extends SampleBaseTestCase {

       @Mock
       private ArticleCalculator calculator;
       @Mock(name = "database")
       private ArticleDatabase dbMock; // note the mock name attribute
       @Spy
       private UserProvider userProvider = new ConsumerUserProvider();

       @InjectMocks
       private ArticleManager manager;

       @BeforeEach
       public void setUp(){
              MockitoAnnotations.initMocks(this);
       }

       @Test
       public void shouldDoSomething() {
           manager.initiateArticle();
       }
   }