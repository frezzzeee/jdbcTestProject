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
        String dropTable = "DROP TABLE dexautomation.Students;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(dropTable);
        statement.close();
    }

    // Создание таблицы
    public void createTableQuery() throws SQLException {
        String createTable = "  CREATE TABLE dexautomation.Students (" +
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
    private String name = "Clon";
    private String lastname = "213";
    private String age = "1221";
    private String phone = "23123123";
    public String student = name + lastname + age + phone;

    // Проверка на наличие записи, запись и вывод добавленного студента
    public String insertQuery () throws SQLException {
        String  insertStudent = "INSERT INTO dexautomation.Students (firstname, lastname, age, phone) VALUES (\'"+ name +"\' ,\'"+ lastname +"\' ,\'"+ age +"\' ,\'"+ phone +"\');";
        String sqlQuery = "select * from Students";
        String man = "";

        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String result = resultSet.getString(2)+resultSet.getString(3)+resultSet.getString(4)+resultSet.getString(5);
            if (result.equals(student)) {
                System.out.println("Студент уже добавлен");
                man = result;
                break;
            }
            else if(resultSet.isLast()) {
                statement1.executeUpdate(insertStudent);
                System.out.println("Студент добавлен");
                Statement statement2 = connection.createStatement();
                ResultSet resultSet1 = statement2.executeQuery(sqlQuery);
                resultSet1.last();
                man = resultSet1.getString(2)+resultSet1.getString(3)+resultSet1.getString(4)+resultSet1.getString(5);
            }
        }
        return man;
    }

    // Извлечение записей из таблицы
    public String executeQuery() throws SQLException {

        String sqlQuery = "select * from Students";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        String result = "";

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
