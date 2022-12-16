/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medmansystem;
import java.util.Scanner;
/**
 *
 * @author SAM
 */
public class MedManSystem {

    /**
     * @param args the command line arguments
     */

    static Scanner sc = new Scanner(System.in);
    
    
    public class Admin extends Users {
        private int id;
    }
    public class Patient extends Users {
        protected int id;
    }
    public class Doctor extends Users {
        protected int id;
        public String specialisation;
    }

    public static void main(String[] args) throws Exception{
        Users user = new Users();
        user.name = "countries";
        user.setPassword("passwords");
        user.setEmail("countries@gmail.com");
        user.contact = 679862916;
        
        MySqlAccess data = new MySqlAccess();
        data.updateUser("user_name = 'sam'", "user_name = 'country'");
    }
    
}
