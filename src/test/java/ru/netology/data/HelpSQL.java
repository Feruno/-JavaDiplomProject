package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.data.DataGenerator;

import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelpSQL {
    private HelpSQL(){
    }

    @Value
    public static class Status{
        private String status;
    }
    @Value
    public static class TimePurchase {
        private String time;
    }

    private static final QueryRunner runner = new QueryRunner();

    private static Connection getConn() throws SQLException{
        return DriverManager.getConnection(System.getProperty("db.url"),  "app", "pass"); // "jdbc:mysql://localhost:3306/app"
    }

    @SneakyThrows
    public static Status getInfoPurStatus(){
        var sqlRes = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var con = getConn();
        var status = runner.query(con, sqlRes, new ScalarHandler<String>());
        return new Status(status);
    }

    @SneakyThrows
    public static Status getInfoCreditPurStatus(){
        var sqlRes = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var con = getConn();
        var status = runner.query(con, sqlRes, new ScalarHandler<String>());
        return new Status(status);
    }

    @SneakyThrows
    public static void cleanDatabase(){
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity ");
        runner.execute(connection, "DELETE FROM payment_entity ");
        runner.execute(connection, "DELETE FROM order_entity ");
    }


}
