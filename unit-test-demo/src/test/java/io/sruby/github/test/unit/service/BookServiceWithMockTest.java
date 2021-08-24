package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.dto.BookDTO;
import io.sruby.github.test.unit.entity.Book;
import io.sruby.github.test.unit.entity.BookCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
//@Transactional
class BookServiceWithMockTest {
    @Mock
    BookCompanyService bookCompanyService;
    @InjectMocks
    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGet() {
        String code = "8888";
        String companyId = "00001";
        when(bookCompanyService.get(companyId)).thenReturn(new BookCompany(1,companyId, code+"_Company"));

        BookDTO result = bookService.get(code,companyId);
        Assertions.assertEquals(new BookDTO(code, companyId, code+"_Company"), result);
    }

    @Test
    public void testInsert(){
        bookService.insert(Book.builder().id(111).code("111").companyId("8888").build());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme