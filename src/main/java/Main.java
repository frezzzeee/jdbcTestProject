import java.sql.*;
import java.sql.SQLException;

public class Main {
        public static void main(String[] args) throws SQLException {
            Request table = new Request();
            try {
                table.dropTable();
                table.createTableQuery();
                table.insertQuery();
                String queryResult = table.executeQuery();
                table.closeConnection();
                System.out.println(queryResult);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

