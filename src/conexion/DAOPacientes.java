package conexion;

// DAO de pacientes (DAO: Objeto de Acceso a Datos)

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPacientes {
    private Connection cn;
    
    public DAOPacientes(Connection cn) {
        this.cn = cn;
    }

    // Insertar informacion del paciente
    public boolean agregarPaciente(Pacientes paciente) {
        String sql = "INSERT INTO paciente(cedula, nombre, apellido, sexo, `grupo sanguineo`, telefono, direccion, email, `fecha de nacimiento`) VALUES (?,?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, paciente.getCedula());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setString(4, paciente.getSexo());
            ps.setString(5, paciente.getGrupoS());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getDireccion());
            ps.setString(8, paciente.getEmail());
            ps.setDate(9, paciente.getFecha());
            ps.executeUpdate();
            return true; // Éxito
        } catch (SQLException e) {
            System.out.println("Error al agregar paciente: " + e.getMessage());
            return false; // Fallo
        }
    }
    
    // Actualizacion de los datos del paciente
    public boolean actualizarPaciente(Pacientes paciente, String cedulaAnterior) {
        String sql = "UPDATE paciente SET nombre=?, apellido=?, sexo=?, `grupo sanguineo`=?, telefono=?, direccion=?, email=?, `fecha de nacimiento`=?, cedula=? WHERE cedula=?";

        if (cn == null) {
            System.out.println("Error: La conexion con la base de datos no se establecio");
            return false;
        }
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getSexo());
            ps.setString(4, paciente.getGrupoS());
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getDireccion());
            ps.setString(7, paciente.getEmail());
            ps.setDate(8, paciente.getFecha());
            ps.setString(9, paciente.getCedula());
            ps.setString(10, cedulaAnterior);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Retorna true si al menos una fila fue modificada
        } catch (SQLException e) {
            System.out.println("Error al actualizar paciente: " + e.getMessage());
            return false;
        }
    }
    
    // Obtener los datos del paciente para la lista de pacientes
    public List<Pacientes> obtenerTodosLosPacientes() {
        List<Pacientes> listaPacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listaPacientes.add(new Pacientes(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("sexo"),
                    rs.getString("grupo sanguineo"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getDate("fecha de nacimiento")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pacientes: " + e.getMessage());
        }

        return listaPacientes;
    }
    
    // Obtencion de los pacientes para ver la información
    public Pacientes obtenerPaciente(String cedula) {
        String sql = "SELECT * FROM paciente WHERE cedula = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Pacientes(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("sexo"),
                    rs.getString("grupo sanguineo"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getDate("fecha de nacimiento")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener paciente: " + e.getMessage());
        }

            return null; // Si no se encontró el paciente
        }
    
}
