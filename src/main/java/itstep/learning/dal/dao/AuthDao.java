package itstep.learning.dal.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.services.db.DbService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

@Singleton
public class AuthDao {
    private final DbService dbService;
    private  final Logger logger;

    @Inject
    public AuthDao(DbService dbService, Logger logger) {
        this.dbService = dbService;
        this.logger = logger;
    }

    public boolean install() {
        String sql = "CREATE TABLE IF NOT EXISTS `users` (" +
                "`user_id` CHAR(32) PRIMARY KEY DEFAULT(UUID())," +
                "`user_name` VARCHAR(64) NOT NULL," +
                "`email` VARCHAR(128) NOT NULL," +
                "`phone` VARCHAR(16) NULL," +
                "`avatar_url` VARCHAR(64) NULL," +
                "`delete_dt` DATETIME NULL," +
                "`birthdate` DATETIME NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";

        try {
            if (dbService == null || dbService.getConnection() == null) {
                logger.severe("Проблема з підключенням до бази даних.");
                return false;
            }

            try (Statement stmt = dbService.getConnection().createStatement()) {
                stmt.executeUpdate(sql);
            }
        }
        catch (SQLException ex) {
            logger.warning(ex.getMessage() + "--" + sql);
            return false;
        }
        return true;
    }


}

/*
DAO - Data Access Object  - набір інструменті (бізнес-логіка) для
роботи з DTO - Data Transfer Object (entities) - моделями передачі даних.
Задачі авторизації/ автентифікації
![users]!      ![users_access]!     ![users_roles]!        ![tokens]!
user_id       [access_id]           |role_id               |token_id
user_name     [user_id]             |role_name             |user_id
email         [login]               |can_create            |iat //issued at
phone         [salt]                |can_read              |exp //expiration
avatar        [dk]                  |can_update
delete_dt     [role_id]             |can_delete
birthdate     [is_active]

![users_details]!
user_id
tg_url
fb_url
work_email
work_phone








Д.З Реалізувати сервіс для генерування випадкових імен файлів. (без розширення)
Випадковий набір символів одного реєстру (або маленькі або великі)
у якому немає активних символів файлової системи (./*,?...)
Сервіс може приймати параметр - довжина імені (у символа).
Якщо не передається, то вживає дані за замовчанням. (з ентропією 64 біти)
Інжектувати до домашньої сторінки, вивести пробні результати різної довжини.
 */
