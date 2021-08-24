package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.BookDTO;
import io.sruby.github.test.unit.entity.Book;
import io.sruby.github.test.unit.entity.BookCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

//@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
class BookServiceMockInSpringTest2 {
    @MockBean
    BookCompanyService bookCompanyService;
    @SpyBean
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

    @Test
    public void test(){
        int id = 1;
        when(ipoService.get(id)).thenReturn(Book.builder().id(1).code("111").companyId("8888").build());
        Book book = ipoService.getIpoWithBusinuss(id);
        assertThat(ObjectUtils.isEmpty(book)).isFalse();
        assertThat(book.getId()).isEqualTo(id);
        assertThat(book.getCode()).startsWith("Businuss");
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme