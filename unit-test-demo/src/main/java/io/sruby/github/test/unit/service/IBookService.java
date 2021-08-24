package io.sruby.github.test.unit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sruby.github.test.unit.entity.Book;

public interface IBookService extends IService<Book> {
    public void removeAll();
}
