import java.sql.*;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class Sql {

    public Sql(){}

    public Connection getConnection() {
        String dbURL = "jdbc:mysql://localhost:3306/javaDataBase";
        String userName = "root";
        String password = "";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void close(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() throws SQLException {

        List<User> users = new LinkedList<>();

        Statement statement = getConnection().createStatement();

        String query = "SELECT * FROM users";

        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String address = resultSet.getString("adress");
            String email = resultSet.getString("email");

            users.add(new User(id, firstName, lastName, address, email));
        }

        return users;
    }

    public List<String> getUserEmail() throws SQLException {

       List<String> email = new LinkedList<>();

        Statement statement = getConnection().createStatement();

        String query = "SELECT email, first_name FROM users";

        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            String name = resultSet.getString("first_name");
            String userEmail = resultSet.getString("email");

            email.add("\n" + name + ":" + " email(" + userEmail + ")" + "\n");
        }
        return email;
    }

    public void ChangeInformation(){}

    public void ChangeAddress() throws SQLException {
        Connection connection = getConnection();

        try{
            connection.setAutoCommit(false);
            Statement statement = getConnection().createStatement();

            String updateAddress1 = "UPDATE users SET adress = '606 Fir Place' WHERE id = 10";

            statement.executeUpdate(updateAddress1);

           connection.commit();

        } catch (SQLException e) {
          connection.rollback();
        }
    }

    public void changeFirstName() throws SQLException {
        Connection connection = getConnection();

        try {
            connection.setAutoCommit(false);
            Statement statement = getConnection().createStatement();

            String updateFirstName = "UPDATE users SET first_name = 'yvi' WHERE id = 9";

            statement.executeUpdate(updateFirstName);

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
        }
    }

}
