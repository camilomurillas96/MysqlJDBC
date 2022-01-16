package datos;

import domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PersonaJDBC {
    //PERSONA:
    private static final String SQL_SELECT = "SELECT id_persona, nombre,"
            + " apellido, email, telefono FROM test.persona";
    private static final String SQL_INSERT = "INSERT INTO test.persona"
            + "(nombre,apellido,email,telefono) VALUE(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE test.persona"
            + " SET nombre=?, apellido=?, email=?, telefono=?"
            + " WHERE id_persona=?";
    private static final String SQL_DELETE = "DELETE FROM test.persona"
            + " WHERE id_persona=?";
    
    //USUARIO:
    
    private static final String SQL_SELECT_U = "SELECT id_usuario, usuario, password FROM test.usuario";
    private static final String SQL_INSERT_U = "INSERT INTO test.usuario(usuario,password) VALUE (?,?)";
    private static final String SQL_UPDATE_U = "UPDATE test.usuario SET usuario=?, password=? WHERE id_usuario=?";
    private static final String SQL_DELETE_U = "DELETE FROM test.usuario WHERE id_usuario=?";
    
    //SELECT PERSONA
    public List<Persona> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<Persona>();        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");                
                persona = new Persona();
                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setEmail(email);
                persona.setTelefono(telefono);                
                personas.add(persona);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
        return personas;
    }
    
    //SELECT USUARIO
    public List<Usuario> selectUser(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_U);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_usuario = rs.getInt("id_usuario");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");                
                user = new Usuario(usuario,password);               
                user.setId_usuario(id_usuario);
//                user.setUsuario(usuario);
//                user.setPassword(password);                
                usuarios.add(user);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
         Conexion.close(rs);
         Conexion.close(stmt);
         Conexion.close(conn);
        }        
        return usuarios;
    }
    // INSERT PERSONA
    public int insert(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setString(3,persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            System.out.println("Ejecutando el query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    //INSERT USUARIO
    public int insertUser(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_U);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            System.out.println("Ejecutando query: " + SQL_INSERT_U);
            rows = stmt.executeUpdate();
            System.out.println("Registros insertados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    //UPDATE PERSONA
    public int update(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query Update: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setString(3,persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getId_persona());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows + ", fila: " + persona.getId_persona());
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
    // UPDATE USUARIO
    public int update(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;      
        
        try{
        conn = Conexion.getConnection(); 
        System.out.println("Ejecutando Query: " + SQL_UPDATE_U);
        stmt = conn.prepareStatement(SQL_UPDATE_U);
        stmt.setString(1, usuario.getUsuario());
        stmt.setString(2, usuario.getPassword());
        stmt.setInt(3, usuario.getId_usuario());
        rows = stmt.executeUpdate();
            System.out.println("Registros modificados: " + rows);        
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
        Conexion.close(stmt);
        Conexion.close(conn);
        }
        return rows;
    }
    
    // DELETE PERSONA
    public int delete(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query Delete: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getId_persona());
            rows = stmt.executeUpdate();
            System.out.println("Registro eliminado: " + rows );
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
        Conexion.close(stmt);
        Conexion.close(conn);
        }
        return rows;
    }
    
    //DELETE USUARIO
    public int deleteUSer(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_U);
            System.out.println("Ejecutando query: " + SQL_DELETE_U);
            stmt.setInt(1, usuario.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Registros elimidados: " + rows);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }      
        
        return rows;
    }
}
