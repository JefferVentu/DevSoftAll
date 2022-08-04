package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class Metodos_sql {
    
    public static ConexionBaseDatos conexion = new ConexionBaseDatos();
    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero = 0;
    
    //Guardar usuario
    public int guardar(String nombres,String apellidos,String correo, String usuario,String contraseña){
        int resultado = 0;
        Connection conexion = null;
        
        String sentencia_guardar = ("INSERT INTO usuarios(nombres,apellidos,correo,usuario,contraseña) VALUES (?,?,?,?,?)");
        
        try {
            conexion = ConexionBaseDatos.conectar();
            sentencia_preparada = conexion.prepareStatement(sentencia_guardar);
            
            sentencia_preparada.setString(1, nombres);
            sentencia_preparada.setString(2, apellidos);
            sentencia_preparada.setString(3, correo);
            sentencia_preparada.setString(4, usuario);
            sentencia_preparada.setString(5, contraseña);            
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            
            
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
            String sentencia_buscar = ("SELECT nombres, apellidos FROM usuarios WHERE usuario ='"+usuario+"'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery();
            //validamos y traemos el resultado
            if(resultado.next()){
                String nombres = resultado.getString("nombres");
                String apellidos = resultado.getString("apellidos");
                
                busqueda_usuario = (nombres+" "+apellidos);
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
            String sentencia_buscar = ("SELECT id, nombres, apellidos FROM usuarios WHERE usuario ='"+usuario+"'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery();
            //validadmos y traemos el resultado
            if(resultado.next()){
                String nombres = resultado.getString("nombres");
                String apellidos = resultado.getString("apellidos");
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
            String sentencia_busqueda_uRegistrado = ("SELECT nombres,usuario,contraseña FROM usuarios WHERE usuario = '"+usuario+"' && contraseña = '"+contraseña+"'");
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
