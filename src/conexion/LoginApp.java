package conexion;
import java.util.Scanner;

public class LoginApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ingrese el usuario:");
        String usuario = sc.nextLine();
        
        System.out.println("Ingrese la contraseña:");
        String contraseña = sc.nextLine();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean loginExitoso = usuarioDAO.validarLogin(usuario, contraseña);
        
        if (loginExitoso) {
            System.out.println("✅ Login exitoso. Bienvenido " + usuario);
        } else {
            System.out.println("🚫 Usuario o contraseña incorrectos.");
        }
        
        sc.close();
    }
}