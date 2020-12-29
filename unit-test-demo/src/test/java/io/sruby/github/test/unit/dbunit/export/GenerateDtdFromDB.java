package io.sruby.github.test.unit.dbunit.export;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @description:
 * @author: sruby
 * @create: 2020-12-25 11:06
 */
public class GenerateDtdFromDB {
    public static void main(String[] args) throws Exception {
        // database connection
        Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:hsqldb:sample", "sa", "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // write DTD file
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("test.dtd"));
    }
}
