package io.sruby.github.test.unit.mapper;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.spring.api.DBRider;
import io.sruby.github.test.unit.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SpringBootTest
@DBRider
@DBUnit(schema = "unit_test", cacheConnection = false, leakHunter = true)
@Slf4j
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

    @DataSet(value = "datasets/book_id_5.xml",strategy = SeedStrategy.UPDATE,
            disableConstraints = true,cleanAfter = true,transactional = true)
    @Test
    public void test_(){
        Book book = bookMapper.selectById("5");
        log.info("book:{}",book);
        Assert.notNull(book, "booke is not null");
    }


}