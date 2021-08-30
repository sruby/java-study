package io.sruby.github.test.unit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sruby.github.test.unit.entity.Book;

import java.util.ArrayList;

public interface IBookService extends IService<Book> {
    public void removeAll();

    void deleteAllAndSaveBatch(ArrayList<Book> entityList);
}
