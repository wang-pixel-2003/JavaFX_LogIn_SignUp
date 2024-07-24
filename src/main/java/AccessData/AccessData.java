package AccessData;

import java.sql.*;

public class AccessData {

    private static String URLconection = "jdbc:mysql://localhost:3306/mydb";
    private static String userDataBase = "root";
    private static String passwordDataBase = "107991";

    /**
     * Agrega la informacion a la tabla de usuarios.
     * @param userName Nombre de usuario
     * @param ide Identificacion
     * @param name Nombre Completo
     * @param password Contraseña
     */
    public static void insertUser(String userName, String ide, String name, String password){
        try {
            Connection connection = DriverManager.getConnection(URLconection, userDataBase, passwordDataBase);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO user (userName, ide, name, password) VALUES (?,?,?,?)");
            ps.setString(1,userName);
            ps.setString(2,ide);
            ps.setString(3,name);
            ps.setString(4,password);

            ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Devuelve todos los registros de la tabla usuarios
     * @return Datos de la tabla usuarios.
     */
    public static ResultSet getAllUsers(){
        try {
            Connection connection = DriverManager.getConnection(URLconection,userDataBase,passwordDataBase);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from user");

            return resultSet;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
