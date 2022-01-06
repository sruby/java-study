package io.sruby.github.test.unit.dbunit.orqcle;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.spring.api.DBRider;
import io.sruby.github.test.unit.UnitTestApplication;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: db unit test
 * @author: sruby
 * @create: 2021-12-20 17:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnitTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DBRider
@DBUnit(schema = "unit_test",cacheConnection = false,leakHunter = true,allowEmptyFields = true)
@Tag("dbunit")
@ActiveProfiles("dev")
public abstract class DBUnitTest {

}
