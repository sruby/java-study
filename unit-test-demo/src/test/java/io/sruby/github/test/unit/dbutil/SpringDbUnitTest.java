package io.sruby.github.test.unit.dbutil;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import io.sruby.github.test.unit.entity.Ipo;
import io.sruby.github.test.unit.service.IpoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-25 11:36
 */
@DatabaseSetup("/partial.xml")
//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class SpringDbUnitTest {
//    @MockBean
//    CompanyService companyService;
//    @Spy
//    private IpoMapper ipoMapper;
    @Autowired
    IpoService ipoService;

//    @DatabaseSetup("partial.xml")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGet() {
        Integer id = 4;
        Ipo ipo = ipoService.get(id);
        assertTrue(!ObjectUtils.isEmpty(ipo));
        assertThat(ipo.getId()).isEqualTo(id);
    }
    @Test
    void testGet2() {
        Integer id = 4;
        Ipo ipo = ipoService.get(id);
        assertTrue(!ObjectUtils.isEmpty(ipo));
        assertThat(ipo.getId()).isEqualTo(id);
    }

    @DatabaseSetup(value = "/partial2.xml",type = DatabaseOperation.INSERT)
    @Test
    void testGet_WithOutClearExistedData() {
        Integer id = 5;
        Ipo ipo = ipoService.get(id);
        assertTrue(!ObjectUtils.isEmpty(ipo));
        assertThat(ipo.getId()).isEqualTo(id);
    }

    @Test
    public void testInsert(){
        ipoService.insert(Ipo.builder().id(111).code("111").companyId("8888").build());
    }

    @Test
    public void test(){
//        ipoService
    }
}
