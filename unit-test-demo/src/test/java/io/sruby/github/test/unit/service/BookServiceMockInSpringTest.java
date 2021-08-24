package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.BookDTO;
import io.sruby.github.test.unit.entity.Book;
import io.sruby.github.test.unit.entity.BookCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BookServiceMockInSpringTest {
    @Autowired
    BookCompanyService bookCompanyService;
    @Autowired
    BookService ipoService;

    @Test
    void testGet() {
        String code = "8888";
        String companyId = "00001";
        when(bookCompanyService.get(companyId)).thenReturn(new BookCompany(1,companyId, code+"_Company"));

        BookDTO result = ipoService.get(code,companyId);
        Assertions.assertEquals(new BookDTO(code, companyId, code+"_Company"), result);
    }

    @Test
    public void testInsert(){
        ipoService.insert(Book.builder().id(4).code("111").companyId("8888").build());
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme