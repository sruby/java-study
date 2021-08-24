package io.sruby.github.test.unit.dbunit.spring;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import io.sruby.github.test.unit.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-25 11:36
 */
//@DatabaseSetup("/partial.xml")
//@DatabaseSetup("/partial2.xml")
@DatabaseSetups({
    @DatabaseSetup("/partial.xml"),
    @DatabaseSetup("/partial2.xml")
})
//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({
        TransactionDbUnitTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class SpringDbUnitTest {
//    @MockBean
//    CompanyService companyService;
//    @Spy
//    private IpoMapper ipoMapper;
    @Autowired
BookService ipoService;

//    @DatabaseSetup("/partial.xml")
    @BeforeAll
    static void setUp() {
    }


//    @Test
//    void testGet() {
//        Integer id = 4;
//        Ipo ipo = ipoService.get(id);
//        assertTrue(!ObjectUtils.isEmpty(ipo));
//        assertThat(ipo.getId()).isEqualTo(id);
//    }
//    @Test
//    void testGet2() {
//        Integer id = 4;
//        Ipo ipo = ipoService.get(id);
//        assertTrue(!ObjectUtils.isEmpty(ipo));
//        assertThat(ipo.getId()).isEqualTo(id);
//    }
//
//    @DatabaseSetup(value = "/partial2.xml",type = DatabaseOperation.INSERT)
//    @Test
//    void testGet_WithOutClearExistedData() {
//        Integer id = 5;
//        Ipo ipo = ipoService.get(id);
//        assertTrue(!ObjectUtils.isEmpty(ipo));
//        assertThat(ipo.getId()).isEqualTo(id);
//    }
//
//    @Test
//    public void testInsert(){
//        ipoService.insert(Ipo.builder().id(111).code("111").companyId("8888").build());
//    }

    @Test
    public void test(){
//        ipoService
    }
}
