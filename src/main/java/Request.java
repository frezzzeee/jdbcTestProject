import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Request {

    // Параметры подключения к БД
    private String url = "jdbc:mysql://db4free.net:3306/dexautomation?useSSL=false";
    private String username = "dexautomation";
    private String password = "dexautomation";

    // Создание подключения
    private Connection connection = DriverManager.getConnection(url, username, password);

    // Конструктор запроса
    public Request() throws SQLException {

    }

    // Фукнция сброса таблицы
    public void dropTable() throws SQLException {
        String dropTable = "DROP TABLE dexautomation.Mystudents;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(dropTable);
        statement.close();
    }

    // Создание таблицы
    public void createTableQuery() throws SQLException {
        String createTable = "  CREATE TABLE dexautomation.Mystudents (" +
                            "  id INT NOT NULL AUTO_INCREMENT," +
                            "  firstname VARCHAR(45) NULL," +
                            "  lastname VARCHAR(45) NULL," +
                            "  age INT NULL," +
                            "  phone VARCHAR(45) NULL," +
                            "  PRIMARY KEY (id));";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTable);
        statement.close();
    }

    // Вставка записей в таблицу
    public void insertQuery () throws SQLException {

        Statement statement = connection.createStatement();

        List<String> studentsList = new ArrayList<String>();
        studentsList.add("INSERT INTO dexautomation.Mystudents (firstname, lastname, age, phone) VALUES ('Ivan', 'Ivanov', '32', '892323');");
        studentsList.add("INSERT INTO dexautomation.Mystudents (firstname, lastname, age, phone) VALUES ('Dima', 'Lukin', '27', '121233');");
        studentsList.add("INSERT INTO dexautomation.Mystudents (firstname, lastname, age, phone) VALUES ('Olga', 'Otinova', '20', '831233');");

        for (int i = 0; i < studentsList.size(); i++) {
            statement.executeUpdate(studentsList.get(i));
        }
        statement.close();
    }

    // Извлечение записей из таблицы
    public String executeQuery() throws SQLException {

        String sqlQuery = "select * from Mystudents";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        String columnName2 = resultSet.getMetaData().getColumnName(2);
        String columnName3 = resultSet.getMetaData().getColumnName(3);
        String columnName4 = resultSet.getMetaData().getColumnName(4);
        String columnName5 = resultSet.getMetaData().getColumnName(5);
        String result = "Students column names: " + columnName2 + ", " + columnName3 + ", " + columnName4 + ", " + columnName5 + "\n";
        while (resultSet.next()) {
            String firstname = resultSet.getString(2);
            String lastname = resultSet.getString(3);
            String age = resultSet.getString(4);
            String phone = resultSet.getString(5);
            result += "Firstname: " + firstname + ", Lastname: " + lastname +
                    ", Age: " + age + ", Phone: " + phone + "\n";
        }
        resultSet.close();
        statement.close();
        return result;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
