//package io.sruby.github.test.unit.dbunit.orqcle;
//
//import lombok.SneakyThrows;
//import org.dbunit.database.DatabaseConfig;
//import org.dbunit.database.DatabaseConnection;
//import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
//import org.dbunit.ext.oracle.OracleDataTypeFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.Connection;
//
///**
// * @description: config
// * @author: sruby
// * @create: 2020-12-29 09:31
// */
//@Configuration
//@ConditionalOnProperty("spring.datasource.dbunit.schema")
//public class DbunitConfig {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Value("${spring.datasource.dbunit.schema}")
//    private String schema;
//    @Bean
//    public OracleDataTypeFactory oracleDataTypeFactory(){
//        return new Oracle10DataTypeFactory();
//    }
//    @SneakyThrows
//    @Bean
//    public DatabaseConnection unitdbDatabaseConnection(){
//        Connection connection = jdbcTemplate.getDataSource().getConnection();
//        /**
//         * set default schema for db
//         */
//        DatabaseConnection databaseConnection = new DatabaseConnection(connection, schema);
//        /**
//         * recognizes more oracle data type
//         */
//        DatabaseConfig databaseConnectionConfig = databaseConnection.getConfig();
//        databaseConnectionConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,oracleDataTypeFactory());
//        return databaseConnection;
//    }
//}
