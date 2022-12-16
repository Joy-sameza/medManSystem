package medmansystem;
import java.io.PrintStream;
import java.sql.*;

import org.w3c.dom.html.HTMLInputElement;

public class MySqlAccess {

    private static final PrintStream OUT = System.out;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception{
        try {
            connect();
            //PreparedStatement can use variables and are more efficient 
            preparedStatement = connect.prepareStatement("select * from medmansystem.users");
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

    /*
     * 
     Adding data to database
     *
     */
    //create a new user in database
    public void addNewUser(String name, String email, int contact, String password) throws Exception {
        try {
            String sqlString = "insert into users(iduser, user_name, email, contact, user_password) values (default, \'" + name + "\', \'"+ email + "\', " + contact + ", \'" + password + "\')";
            connect();
            CallableStatement callableStatement = connect.prepareCall(sqlString);
            callableStatement.executeUpdate();
            OUT.println("\nUser added!");
        } catch (Exception e) {
            throw e;
        }
    }

    public void addDoctor(String name, String email, String specialisation) throws Exception{
        try {
            String sqlString = "insert into doctor (iddoctor, doctor_name, email, specialisation) values (default, \'" + name + "\', \'"+ email + "\', \'" + specialisation + "\');";
            connect();
            CallableStatement callableStatement = connect.prepareCall(sqlString);
            callableStatement.executeUpdate();
            OUT.println("\nDoctor added!");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void addPatient(String name, String email) throws Exception{
        try {
            String sqlString = "insert into patient (idpatient, patient_name, email) values (default, \'" + name + "\', \'"+ email + "\');";
            connect();
            CallableStatement callableStatement = connect.prepareCall(sqlString);
            callableStatement.executeUpdate();
            OUT.println("\nPatient added!");
        } catch (Exception e) {
            throw e;
        }
    }    

    /*
     Delete data to database
     */
    public void deleteByName(String name, String tableName) throws Exception {
        try {
            String nameField = null;
            switch (tableName) {
                case "users":
                    nameField = "user_name";
                    break;
                case "patient":
                    nameField = "patient_name";
                case "doctor":
                    nameField = "doctor_name";
                    break;
                case "admins":
                    nameField = "admin_name";
                default:
                    break;
            }
            String sqlString = "delete from " + tableName +" where " + nameField + "= \'" + name + "\';";
            connect();
            CallableStatement callableStatement = connect.prepareCall(sqlString);
            callableStatement.executeUpdate();
            OUT.println("\nUser deleted!");
        } catch (Exception e) {
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

    public void updateUser(String set, String where) throws Exception{
        String sqlString = "update users set " + set + " where " + where + ";";
        connect();
        CallableStatement callableStatement = connect.prepareCall(sqlString);
        callableStatement.executeUpdate();
        OUT.println("updated!!");
    }

    void connect() throws Exception {
        try {
            // This will load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Setup connection with the db
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/medmansystem","root","12345");
            //Statement allow to issue SQL queries to database
            statement = connect.createStatement();
            //Result set get the result of the sql query
            resultSet = statement.executeQuery("select * from medmansystem.users");
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void display() throws Exception {

        connect();
        CallableStatement call = connect.prepareCall("select * from patient;");
        resultSet = call.executeQuery();
        OUT.println("id\t\tname\t\temail");
        if (!resultSet.next()) {
            OUT.println("Empty");
        } else {
            // Condition check
            while (resultSet.next()) {
                int id = resultSet.getInt("idpatient");
                String names = resultSet.getString("patient_name");
                String emails = resultSet.getString("email");
                // String specia = resultSet.getString("specialisation");
            OUT.println(id + "\t\t" + names + "\t\t" + emails );
            }
        }
    }

    
}