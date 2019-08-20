package support.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlClient {
    private static int MAX_ROWS = 100;
    private Logger log = LogManager.getLogger(SqlClient.class);
    private String url;
    private String user;
    private String pwd;
    private String driverClassName;

    private Connection conn;
    private PreparedStatement pst;
    private String sql;

    public SqlClient(String url, String user, String pwd, String driverClassName) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.driverClassName = driverClassName;
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect() throws SQLException {
        conn = DriverManager.getConnection(url, user, pwd);
        log.debug("---- DB SETUP ----");
        log.debug(String.format("Driver: %s", driverClassName));
        log.debug(String.format("Database url: %s", url));
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (conn == null) {
            throw new RuntimeException("Connection not initialised");
        }
        if (pst != null) {
            pst.close();
        }
        this.sql = sql;
        pst = conn.prepareStatement(sql);
        pst.setMaxRows(MAX_ROWS);
        return pst;
    }

    public void close() throws SQLException {
        if (pst != null) {
            pst.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<Map<String, String>> executeQueryAndGetRsAsList() {
        log.debug("---- SQL QUERY REQUEST ----");
        log.debug(String.format("SQL query: %s", sql));
        List<Map<String, String>> tableData = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()) {
                Map<String, String> rowData = new HashMap<>();
                for (int i = 1; i <= columns; i++) {
                    Object value = rs.getObject(i);
                    rowData.put(md.getColumnLabel(i), value != null ? value.toString() : null);
                }
                tableData.add(rowData);
            }
            return tableData;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                log.debug(String.format("SQL result: %s", tableData));
                log.debug("-----------------------");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ResultSet executeQuery() throws SQLException {
        log.debug("---- SQL QUERY REQUEST ----");
        log.debug(String.format("SQL query: %s", sql));
        return pst.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        log.debug("---- SQL UPDATE REQUEST ----");
        log.debug(String.format("SQL update: %s", sql));
        int affected = 0;
        try {
            affected = pst.executeUpdate();
            return affected;
        } finally {
            log.debug(String.format("SQL affected rows: %s", affected));
            log.debug("-----------------------");
        }
    }
}