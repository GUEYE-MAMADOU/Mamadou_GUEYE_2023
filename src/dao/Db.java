package dao;

import java.sql.*;

public class Db {
    //Pour la connexion
    private Connection conn;

    //Pour les resultats de types SELECT
    private ResultSet rs;

    //Pour les requetes preparees
    private PreparedStatement pstm ;

    //Pour les requetes de types MAJ
    private int ok;

    private static final String URL = "jdbc:mysql://localhost:3306/javadb";
    private static final String USER = "root";
    private static final String PASSWORD = "";



    Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        /*
          if (conn != null) {
          System.out.println("Connexion réussie.");
            } else {
            System.out.println("Échec de la connexion.");
                }
        */
        return conn;
    }

    public void initPrepar(String sql){
        try {
            getConnection();
            pstm = conn.prepareStatement(sql);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executSelect(){
        rs = null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public int executeMaj(){
        try {
            ok = pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    public void closeCOnncetion (){
        try {
            if (conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public ResultSet executeSelect() {
        rs = null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}


