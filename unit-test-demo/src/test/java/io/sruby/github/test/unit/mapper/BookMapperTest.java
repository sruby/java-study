package io.sruby.github.test.unit.mapper;

import io.sruby.github.test.unit.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;
    @Test
    public void testInsert(){
//        ipoMapper.insert(Ipo.builder().id(1).code("11").companyId("888").build());
    }

    @Test
    @Transactional
    public void test_Transactional_Callback(){
        bookMapper.insert(Book.builder().id(20).code("22").companyId("8881").build());
    }


}