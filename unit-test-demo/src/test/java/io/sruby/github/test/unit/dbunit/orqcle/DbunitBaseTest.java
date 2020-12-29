package io.sruby.github.test.unit.dbunit.orqcle;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-29 09:38
 */
@DbUnitConfiguration(databaseConnection = "unitdbDatabaseConnection")
@DatabaseSetup("/dbunit/tablename1.xml")
@DatabaseSetup("/dbunit/tablename2.xml")
@SpringBootTest
//@ActiveProfiles("test")
@Transactional
@TestExecutionListeners({ TransactionDbUnitTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@Slf4j
public class DbunitBaseTest {
}
