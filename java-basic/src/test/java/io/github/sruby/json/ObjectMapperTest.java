package io.github.sruby.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.sruby.annotation.demo.Person;
import io.github.sruby.bean.Employee;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

/**
 * Test
 */
@Slf4j
public class ObjectMapperTest {
    private ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Test
    void readValue() {
        Person person = objectMapper.readValue("{\"name\":\"test\"}", Person.class);
        log.debug("person:{}",person);
    }

    @Test
    public void testWriteValue() throws IOException {
        //convert json string to object
        Employee employee = Employee.builder().age(11).hireDate(new Date()).name("张三").build();

        //writing to console, can write to any output stream such as file
        StringWriter stringEmp = new StringWriter();
        objectMapper.writeValue(stringEmp, employee);
        System.out.println("Employee JSON is\n"+stringEmp);

        String valueAsString = objectMapper.writeValueAsString(employee);
        log.debug("value:{}",valueAsString);

        String valueAsString1 = objectMapper.writeValueAsString("&#^$#&#");
        log.debug("value:{}",valueAsString1);

    }

    @Test
    public void testWriteValueAsString(){

    }


}
