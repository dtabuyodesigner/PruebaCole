package basesDAO;

import java.sql.*;

public class LoginDAO {

    private final String url = "jdbc:mysql://localhost:3306/Colegio?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    private Connection connectionMysql;
    public boolean dato;

    public LoginDAO() {

    }

    public void conectar() throws Exception {
        Class.forName(driver);
        connectionMysql = DriverManager.getConnection(url, user, password);
    }

    public void desconectar() throws Exception {
        connectionMysql.close();
    }


    public boolean registrar(String usuario, String pass) throws Exception {

        String query = "INSERT INTO users (usuario, pass) VALUES (?, ?)";
        conectar();
        PreparedStatement sentencia = connectionMysql.prepareStatement(query);

        sentencia.setString(1, usuario);
        sentencia.setString(2, pass);
        sentencia.executeUpdate();

        boolean aviso = false;

        if (sentencia != null) {
            aviso = true;
        }

        System.out.println(aviso);
        return aviso;
    }


    public boolean verificarLogin(String usuario, String pass) throws Exception {

        String query = "SELECT usuario, pass FROM users WHERE usuario = ? AND pass = ?";
        ResultSet rs = null;
        conectar();
        PreparedStatement sentencia = connectionMysql.prepareStatement(query);
        sentencia.setString(1, usuario);
        sentencia.setString(2, pass);
        rs = sentencia.executeQuery();

        boolean datos = false;
        while (rs.next()){
            datos= true;
        }

        return datos;
    }

}

