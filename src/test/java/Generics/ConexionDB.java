package Generics;

import oracle.ucp.jdbc.oracle.OracleDriverConnectionFactoryAdapter;

import java.sql.*;

public class ConexionDB {


    private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    private static final String DRIVER_URL_DEFAULT="jdbc:oracle:thin:@200.14.169.238:1521/ORION";
    private static final String USUARIO = "VISTA_360_CN";
    private static final String PASSWORD = "VISTA_360_CN_ORION2K16";

    private String driverClassName;
    private String driverUrl;
    private String usuario;
    private String password;
    private Statement stmt;
    private ResultSet rset;

    public ConexionDB (){
        this.driverClassName=DRIVER_CLASS_NAME;
        this.driverUrl=DRIVER_URL_DEFAULT;
        this.usuario= USUARIO;
        this.password = PASSWORD;
    }

    public Connection getConexion() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(driverUrl, usuario, password);
        }catch(SQLException e1){
            System.out.println("Query Execution Error" );
            e1.printStackTrace();
        }catch(Exception e){
            System.out.println("Error Exception");
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ResultSet RunQuery(Connection connection , String Query){
        try{
            stmt = connection.createStatement();
            rset = stmt.executeQuery(Query);
        } catch(SQLException e1){
            System.out.println("Query Execution Error" );
            e1.printStackTrace();
        }

        return rset;
    }

    //Cierre del ResultSet
    public void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre del PrepareStatement
    public void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre de la conexion
    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
