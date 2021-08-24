package io.sruby.github.test.unit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sruby.github.test.unit.dto.BookDTO;
import io.sruby.github.test.unit.entity.Book;
import io.sruby.github.test.unit.entity.BookCompany;
import io.sruby.github.test.unit.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: sruby
 * @create: 2020-12-07 11:53
 */
@Service
public class BookService extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Autowired
    private BookCompanyService bookCompanyService;
    @Autowired
    private BookMapper bookMapper;

    public BookDTO get(String code, String companyId){
        Book book = Book.builder().code(code).companyId(companyId).build();
        BookCompany company = bookCompanyService.get(companyId);
        BookDTO bookDTO = BookDTO.builder().code(code).companyId(companyId).companyName(company.getCompanyName()).build();
        return bookDTO;
    }

    public Book getIpoWithBusinuss(Integer id){
        Book book = get(id);
        book.setCode("Businuss"+ book.getCode());
        return book;
    }

    public Book get(Integer id){
        return bookMapper.selectById(id);
    }


    public int insert(Book book){
//        return ipoMapper.insert(ipo);
        return 0;
    }

    @Override
    @Transactional
    public void removeAll(){
        remove(new QueryWrapper<Book>().lambda().gt(Book::getId,0));
    }
}
