package com.ipn.mx.ejercicio5.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ipn.mx.ejercicio5.modelo.dto.UsuariosDTO;

public class UsuariosDAO {
    private static final String SQL_SELECT_ALL = "{call spMostrarUsuarios}";
    private Connection con;

    // public Connection getConnection() {
    // String user = "postgres";
    // String pwd = "n0m3l0s3";
    // String url = "jdbc:postgresql://localhost:5432/dbejercicio5";
    // String driver = "org.postgresql.Driver";
    // try {
    // Class.forName(driver);
    // con = DriverManager.getConnection(url, user, pwd);
    // } catch (ClassNotFoundException | SQLException ex) {
    // Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
    // }
    // return con;
    // }

    public Connection getConnection() {
        String user = "root";
        String pwd = "n0m3l0s3";
        String url = "jdbc:mysql://localhost:3306/ejercicio_5?serverTimezone=America/Mexico_City&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public List readAll() throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_SELECT_ALL);
            rs = cs.executeQuery();
            List results = getResults(rs);

            if (results.size() > 0)
                return results;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    private List getResults(ResultSet rs) throws SQLException {
        List results = new ArrayList();
        while (rs.next()) {
            UsuariosDTO dto = new UsuariosDTO();
            dto.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setPaterno(rs.getString("paterno"));
            dto.getEntidad().setMaterno(rs.getString("materno"));
            dto.getEntidad().setEmail(rs.getString("email"));
            dto.getEntidad().setIdTipoUsuario(rs.getInt("idTipoUsuario"));
            results.add(dto);
        }
        return results;
    }
}
