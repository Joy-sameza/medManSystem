/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medmansystem;
import java.io.PrintStream;
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

    public static void main(String[] args) throws Exception{
        Users user = new Users();
        user.name = "Something";
        user.setPassword("password");
        user.contact = 679862916;
        user.specialisation = "sickness";
        
        MySqlAccess data = new MySqlAccess();
        data.readDataBase();
        data.addNewUser(user.name, user.getPassword(), user.contact, user.getEmail());
    }
    
}
