/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jvrui
 */

import static java.lang.Integer.getInteger;
import java.sql.*;

public class Metodos_sql {
    
    
    public static ConexionBaseDatos conexion = new ConexionBaseDatos();
    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero;
    
    //Guardar usuario
    public int guardar(String nombre,String apellido,String correo, String usuario,String contraseña, String repetirContraseña){
        int resultado = 0;
        Connection conexion = null;
        
        String sentencia_guardar = ("INSERT INTO usuarios(nombre,apellido,usuario,contraseña) VALUES (?,?,?,?)");
        
        try {
            conexion = ConexionBaseDatos.conectar();
            sentencia_preparada = conexion.prepareStatement(sentencia_guardar);
            
            sentencia_preparada.setString(1, nombre);
            sentencia_preparada.setString(2, apellido);
            sentencia_preparada.setString(3, correo);
            sentencia_preparada.setString(4, usuario);
            sentencia_preparada.setString(5, contraseña);
            sentencia_preparada.setString(6, repetirContraseña);             
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            conexion.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }
    
    //TRAER EL USUARIO(nombre y apellido)
    public static String buscarUsuario(String usuario){
        String busqueda_usuario = null;
        
        Connection conexion = null;
        try {
            conexion = ConexionBaseDatos.conectar();
            String sentencia_buscar = ("SELECT nombre, apellido FROM usuarios WHERE usuario ='"+usuario+"'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery();
            //validadmos y traemos el resultado
            if(resultado.next()){
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                
                busqueda_usuario = (nombre+" "+apellido);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return busqueda_usuario;
    }
    
    //Retorna el id del usuario que se logeo
    public static String buscarId(String usuario){
        String busqueda_id = null;//para el id
        
        Connection conexion = null;
        try {
            conexion = ConexionBaseDatos.conectar();
            //String sentencia_buscar = ("SELECT nombre, apellido FROM usuarios WHERE usuario ='"+usuario+"'");
            String sentencia_buscar = ("SELECT id, nombre, apellido FROM usuarios WHERE usuario ='"+usuario+"'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery();
            //validadmos y traemos el resultado
            if(resultado.next()){
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String id = resultado.getString("id");//IDDDDDDDD
                
                busqueda_id = (id);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return busqueda_id;
    }
    
    //VERIFICAR SI EL USUARIO esta registrado o no
    public static String BuscarUsuarioRegistrado(String usuario, String contraseña){
        String busqueda_uRegistrado =  null;
        Connection conexion = null;
        
        try {
            conexion = ConexionBaseDatos.conectar();
            String sentencia_busqueda_uRegistrado = ("SELECT nombre,usuario,contraseña FROM usuarios WHERE usuario = '"+usuario+"' && contraseña = '"+contraseña+"'");
            sentencia_preparada = conexion.prepareStatement(sentencia_busqueda_uRegistrado);
            resultado = sentencia_preparada.executeQuery();
            if(resultado.next()){
                busqueda_uRegistrado = "Usuario registrado";
            }else{
                busqueda_uRegistrado = "Usuario NO registrado";
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return busqueda_uRegistrado;
    }
    
}
