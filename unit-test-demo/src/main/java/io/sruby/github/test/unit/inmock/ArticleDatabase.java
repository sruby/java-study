package io.sruby.github.test.unit.inmock;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: sruby
 * @create: 2020-12-08 10:06
 */
@Slf4j
public class ArticleDatabase {
    public void addListener(ArticleListener articleListener){
        log.info("add Listener");
    }
}
