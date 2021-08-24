package io.sruby.github.test.unit.service;

import io.sruby.github.test.unit.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class BookServiceTest {
    @Autowired
    IBookService bookService;

    @Test
    public void testInsert(){
        ArrayList<Book> entityList = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            entityList.add(Book.builder()
                    .id(1111)
                    .code("88888")
                    .companyId("9999")
                    .build())    ;
        }

        bookService.saveBatch(entityList);
    }

    @Test
    public void testDeleteAllAndSaveBatch(){
        bookService.removeAll();

        ArrayList<Book> entityList = new ArrayList<>();

        for (int i = 0; i < 150000; i++){
                entityList.add(Book.builder()
                        .id(1111)
                        .code("88888")
                        .companyId("9999")
                        .build())    ;
        }

        bookService.saveBatch(entityList);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme