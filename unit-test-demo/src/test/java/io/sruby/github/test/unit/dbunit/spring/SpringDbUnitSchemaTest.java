package io.sruby.github.test.unit.dbunit.spring;

/**
 * @description: test
 * https://stackoverflow.com/questions/22796147/spring-test-dbunit-and-table-schema-name
 * 方式2：
 * spring.datasource.schema中的脚本：
 * spring.datasource.schema = schema.sql
 * 然后是带有以下内容的文件squema.sql：
 * ALTER SESSION SET CURRENT_SCHEMA=mySchema
 * https://www.coder.work/article/6646875
 * @author: sruby
 * @create: 2020-12-28 20:10
 */
public class SpringDbUnitSchemaTest {
}
