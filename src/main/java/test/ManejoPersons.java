package test;

import datos.PersonaJDBC;
import domain.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejoPersons {
    public static void main(String[] args) {
        
        // prueba SELECT
//        PersonaJDBC personaJDBC = new PersonaJDBC();
//        List<Persona> personas = personaJDBC.select();
//        
//        
//        for(Persona persona: personas){
//            System.out.println("persona: "+ persona);
//        }
        
        // prueba del insert COMPLETADA
        /*Persona persona = new Persona();
        persona.setNombre("Klay");
        persona.setApellido("Tomland");
        persona.setEmail("klay@prueba.com");
        persona.setTelefono("2456709");
        personaJDBC.insert(persona);
        */
        
        // prueba UPDATE COMPLETADA
//        Persona persona = new Persona();
//        persona.setId_persona(3);
//        persona.setNombre("Kyoto");
//        persona.setApellido("Tomland");
//        persona.setEmail("kyoto@prueba.com");
//        persona.setTelefono("2456709");
//        personaJDBC.update(persona);

        
        // prueba DELETE COMPLETADA
//        Persona persona = new Persona();
//        persona.setId_persona(3);
//        personaJDBC.delete(persona); 

        PersonaJDBC personaJDBC = new PersonaJDBC();
        List<Usuario> usuarios = personaJDBC.selectUser();
        for(Usuario usuario: usuarios){
            System.out.println("usuario: " + usuario);
        }
        
        // PRUEBA INSERT USUARIO COMPLETADA
//        Usuario usuario = new Usuario();
//        usuario.setUsuario("Kyoto");
//        usuario.setPassword("kyoto123");
//        personaJDBC.insertUser(usuario);

        //PRUEBA UPDATE USUARIO COMPLETADA
//         Usuario usuario = new Usuario("tokyo","tokyo123");
//         usuario.setId_usuario(3);
//        try {
//            personaJDBC.update(usuario);
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        }
        
        //PRUEBA DELETE USUARIO COMPLETADA
        Usuario usuario = new Usuario();
        usuario.setId_usuario(3);
        personaJDBC.deleteUSer(usuario);

    }
}
