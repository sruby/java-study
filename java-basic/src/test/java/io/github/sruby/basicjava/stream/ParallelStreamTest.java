package io.github.sruby.basicjava.stream;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Parallel Stream test
 *
 * @author Sruby
 * @date 5/7/2023 4:20 PM
 */
public class ParallelStreamTest {
    long usingStreamParallel(Collection<Book> listOfBooks, int year) {
        AtomicLong countOfBooks = new AtomicLong();
        listOfBooks.stream().parallel()
                .forEach(book -> {
                    if (book.getYearPublished() == year) {
                        countOfBooks.getAndIncrement();
                    }
                });
        return countOfBooks.get();
    }

    long usingCollectionsParallel(Collection<Book> listOfbooks, int year) {
        AtomicLong countOfBooks = new AtomicLong();
        listOfbooks.parallelStream()
                .forEach(book -> {
                    if (book.getYearPublished() == year) {
                        countOfBooks.getAndIncrement();
                    }
                });
        return countOfBooks.get();
    }
}
