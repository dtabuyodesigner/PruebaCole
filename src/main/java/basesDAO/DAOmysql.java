package basesDAO;

import domain.Alumno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOmysql {

    private Connection connectionMysql;
    private final String url = "jdbc:mysql://localhost:3306/Colegio?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";


    public void conectar() throws Exception {

        Class.forName(driver);
        connectionMysql = DriverManager.getConnection(url, user, password);
    }

    public void desconectar() throws Exception {
        connectionMysql.close();
    }


    public void crearAlumno(Alumno alumno) throws Exception {
        // Creamos la cadena de la consulta que vamos a hacer
        String crea = "INSERT INTO Alumnos VALUES (null, ?, ?, ?,?,?)";

        // Creamos un objeto de la consulta con la BBDD
        PreparedStatement consulta = connectionMysql.prepareStatement(crea);

        // Configuramos los parametros del objeto consulta
        //  consulta.setInt(1, );
        consulta.setString(1, alumno.getNombre());
        consulta.setString(2, alumno.getApellido1());
        consulta.setString(3, alumno.getApellido2());
        consulta.setString(4, alumno.getCodCurso());
        consulta.setString(5, alumno.getObservaciones());

        // Ejecutamos la consulta y la cerramos
        consulta.executeUpdate();
        consulta.close();
    }

    public void eliminaAlumno(Alumno alumno) throws Exception {
        // Creamos la sentencia de eliminación
        String elimina = "DELETE FROM Alumnos WHERE codAlu = ?;";

        PreparedStatement sentencia = connectionMysql.prepareStatement(elimina);
        sentencia.setInt(1, alumno.getCodAlum());
        sentencia.executeUpdate();
        sentencia.close();
    }
    //Método para modificar alumnos. todo 20-12-20

    public void editaAlumno(Alumno alumnoAntiguo, Alumno alumnoNuevo) throws Exception {
        String edita = "UPDATE Alumnos SET nombre = ?, apellido1 = ?, apellido2 = ?, codCurso = ?, observaciones = ? WHERE id = ?";

        PreparedStatement sentencia = connectionMysql.prepareStatement(edita);
        sentencia.setString(1, alumnoNuevo.getNombre());
        sentencia.setString(2, alumnoNuevo.getApellido1());
        sentencia.setString(3, alumnoNuevo.getApellido2());
        sentencia.setString(4, alumnoNuevo.getCodCurso());
        sentencia.setString(5, alumnoNuevo.getObservaciones());
        sentencia.setInt(6, alumnoAntiguo.getCodAlum());
        sentencia.executeUpdate();
        sentencia.close();

    }

    public List<Alumno> listaAlumnos() throws Exception {
        String consulta = "SELECT * FROM Alumnos";
        ArrayList<Alumno> alumnos = new ArrayList();

        Statement statement = connectionMysql.createStatement();
        ResultSet valoresObtenidos = statement.executeQuery(consulta);

        while (valoresObtenidos.next()) {
            Alumno alumno = new Alumno(
                    valoresObtenidos.getInt("codAlu"),
                    valoresObtenidos.getString("nombre"),
                    valoresObtenidos.getString("apellido1"),
                    valoresObtenidos.getString("apellido2"),
                    valoresObtenidos.getString("codCurso"),
                    valoresObtenidos.getString("observaciones")
            );

            alumnos.add(alumno);
        }

        statement.close();
        valoresObtenidos.close();

        return alumnos;
    }

    //todo Método para ver si existe el alumno en la base de datos
    public List<Alumno> buscar(String ap1, String ap2) throws SQLException {
        ArrayList<Alumno> alumnosencon = new ArrayList();
        String sql = "SELECT * FROM Alumnos WHERE apellido1 = ? AND apellido2 = ?";
        PreparedStatement sentencia = connectionMysql.prepareStatement(sql);
        sentencia.setString(1, ap1);
        sentencia.setString(2, ap2);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Alumno alumno = new Alumno(
                    resultado.getInt("codAlu"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido1"),
                    resultado.getString("apellido2"),
                    resultado.getString("codCurso"),
                    resultado.getString("observaciones")
            );

            alumnosencon.add(alumno);
        }

        sentencia.close();
        resultado.close();

        return alumnosencon;
    }
}
