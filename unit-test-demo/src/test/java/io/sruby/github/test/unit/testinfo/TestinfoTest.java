package io.sruby.github.test.unit.testinfo;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-23 17:01
 */
@DisplayName("test info test")
public class TestinfoTest {
    public TestinfoTest(TestInfo testInfo) {
        assertThat(testInfo.getDisplayName()).isEqualTo("test info test");
    }

    @BeforeEach
    public void init(TestInfo testInfo){
        String displayName = testInfo.getDisplayName();
        assertThat(displayName).isIn("test 1","test 2");
    }

    @Test
    @DisplayName("test 1")
    @Tag("my-tag")
    public void test(TestInfo testInfo){
        assertThat(testInfo.getDisplayName()).isEqualTo("test 1");
        assertThat(testInfo.getTags()).contains("my-tag");
    }
}
