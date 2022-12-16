package medmansystem;
import java.io.PrintStream;
import java.sql.*;

public class MySqlAccess {

    private static final PrintStream OUT = System.out;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception{
        try {
            // This will load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Setup connection with the db
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","12345");
            //Statement allow to issue SQL queries to database
            statement = connect.createStatement();
            //Result set get the result of the sql query
            resultSet = statement.executeQuery("select * from medmansystem.users");
            //PreparedStatement can use variables and are more efficient 
            preparedStatement = connect.prepareStatement("insert into medmasystem.users values (default, ?, ?, ?, ?)");  //id, user_name, email, contact, user_password


            preparedStatement = connect.prepareStatement("select user_name, email, contact, user_password from medmansystem.users");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

            // Remove again the insert comment
            preparedStatement = connect.prepareStatement("delete from medmansystem.users where user_name = ?;");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            resultSet = statement.executeQuery("select * from medmansystem.users");
            writeMetaData(resultSet);

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    //create a new user in database
    void addNewUser(String name, String password, int contact, String email) throws Exception {
        try {
            String sqlString = "insert into users (user_name, user_password, contact, email) values (default, " + name + ", "+ password + ", " + contact + ", " + email + ")";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/medmansystem?" + "user=root&password=12345");
            CallableStatement callableStatement = connect.prepareCall(sqlString);
            callableStatement.execute();
        } catch (Exception e) {
            throw e;
        }
    }

    private void close() throws Exception {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        OUT.println("The columns in the table are: ");

        OUT.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            OUT.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException{
        while (resultSet.next()) {
            OUT.println(resultSet);
        }
    }

    private void updateUser() throws Exception{

    }

    
}
