package itstep.learning.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbService implements DbService {
    private Connection connection;
    @Override
    public Connection getConnection() throws SQLException {
        if(connection == null) {
            // Процес підключення: реєструємо драйвер СУБД (MySql)
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            // Формуємо рядок підключення
            String connectionUrl = "jdbc:mysql://localhost:3308/java_kn_p_213" +
                    "?useUnicode=true&characterEncoding=utf8&useSSL=false";
            String username = "user213";
            String password = "pass213";
            //Одержуємо підключення
            connection = DriverManager.getConnection(connectionUrl, username, password);
        }
        return connection;
    }
}


/*
JBDC - Java DataBase Connectivity - технологія доступу до даних, аналогічних
до ADO.NET
Надає узагальнений інтерйфейс дял работи з різними джерелами даних (СУБД)
Для роботи з контретною СУБД необхідно встановити конектор (драйвер) відповідної
СУБД

 */
