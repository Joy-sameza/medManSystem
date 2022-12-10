/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medmansystem;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author SAM
 */
public class MedManSystem {

    /**
     * @param args the command line arguments
     */

    private static final PrintStream OUT = System.out;
    static Scanner sc = new Scanner(System.in);
    
    
    public class Admin {
        private String name;
        public long contact;
        public String email; 
    }
    public class Patient {
        protected long id;
        public String name;
        private String email;
    }
    public class Doctor {
        protected long id;
        public String name;
        public String email;
        public String specialisation;
    }
    
    //Function to connect to DataBase
//    static void connectDb(String url, String user, String password) {
//            try {
//            Connection con = DriverManager.getConnection(url , user, password);
//            if (con != null) {
//                OUT.println("Sucessfully connected"); 
//            }
//        } catch (SQLException e) {
//            OUT.println(e + "\nNot connected");
//        }
//        }

    public static void main(String[] args) throws Exception{
        // Users user = new Users();
        
//        String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL [root on Default schema]";
//        String dbUser = "root";
//        String password = "12345";
        
//        connectDb(url , dbUser, password);
        
        MySqlAccess data = new MySqlAccess();
        data.readDataBase();
    }
    
}
