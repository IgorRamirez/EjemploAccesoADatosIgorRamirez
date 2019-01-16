package ejemploaccesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xp
 */
public class GestorConexion {

    Connection conn1;

    public GestorConexion() {
        conn1 = null;
        try {
            String url1 = "jdbc:mysql://localhost:3306/discografica?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "root";

            conn1 = DriverManager.getConnection(url1, user, password);

            if (conn1 != null) {
                System.out.println("Conectando a discografica...");

            }

        } catch (SQLException ex) {
            System.out.println("ERROR: dirección o usuario/clave no válida");
            ex.printStackTrace();

        }
    }

    public void cerrar_conexion() {
        try {
            conn1.close();
            System.out.println("conexion cerrada");
        } catch (SQLException ex) {
            System.out.println("ERROR:al cerar la conexion");
            ex.printStackTrace();

        }

    }

    public void annadir_columna() {
        Statement sta;
        try {
            sta = conn1.createStatement();
            sta.executeUpdate("ALTER TABLE album ADD annno_pblicacion YEAR");
            sta.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrar_tabla()
            throws SQLException {
        Statement sta = conn1.createStatement();

        sta.executeUpdate("DROP TABLE album");
        sta.close();

    }

    public void insertar() {
        try {
// Crea un statement
            Statement sta = conn1.createStatement();
// Ejecuta la inserción
            sta.executeUpdate("INSERT INTO album VALUES (2,'Greatest Hits','Queen')");
// Cierra el statement
            sta.close();
        } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
    }
    
    
}
