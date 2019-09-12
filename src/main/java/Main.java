import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.sql.SQLException;

public class Main {

    @Test
    public void test() throws SQLException {
    Request table = new Request();
    //table.dropTable();
    //table.createTableQuery();
    String man  = table.insertQuery();
    Assert.assertEquals(man, table.student);
    System.out.println(man);
    //Извлечение всех записей из таблицы
    /*String queryResult = table.executeQuery();
    table.closeConnection();
    System.out.println(queryResult);*/
    }
}


