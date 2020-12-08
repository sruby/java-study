package io.sruby.github.test.unit.inmock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ArticleManager {
    private ArticleCalculator articleCalculator;
    private ArticleDatabase articleDatabase;

    ArticleManager(ArticleCalculator calculator, ArticleDatabase database) {
        // parameterized constructor
        log.info("article manager");
    }

    public void initiateArticle() {
        log.info("initiateArticle");
    }
}