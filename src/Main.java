import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Sql sql = new Sql();
        sql.getConnection();
        sql.ChangeAddress();
        sql.changeFirstName();
        System.out.println(sql.getUsers());
        System.out.println(sql.getUserEmail());
        sql.close(sql.getConnection());
    }
}