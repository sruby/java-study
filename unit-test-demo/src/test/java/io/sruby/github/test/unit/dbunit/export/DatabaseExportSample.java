package io.sruby.github.test.unit.dbunit.export;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.util.search.SearchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class DatabaseExportSample
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private IDatabaseConnection connection;

    @BeforeEach
    public void before() throws DatabaseUnitException, SQLException {
        Connection jdbcConnection = jdbcTemplate.getDataSource().getConnection();
        connection = new DatabaseConnection(jdbcConnection);
    }

    @Test
    public void testPartialDBExport() throws Exception
    {

        // database connection
//        Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
//        Connection jdbcConnection = DriverManager.getConnection(
//                "jdbc:hsqldb:sample", "sa", "");


        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("ipo", "SELECT * FROM ipo WHERE company_id='888'");
//        partialDataSet.addTable("company");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));
    }

    @Test
    public void testFullDBExport() throws IOException, SQLException, DataSetException {
        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
    }

    @Test
    public void testDependentTablesDatabaseExport() throws SQLException, DataSetException, SearchException, IOException {
        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
        String[] depTableNames = TablesDependencyHelper.getAllDependentTables( connection, "ipo" );
        IDataSet depDataset = connection.createDataSet( depTableNames );
        FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));
    }


}