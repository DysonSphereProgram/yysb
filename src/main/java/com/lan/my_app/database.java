package com.lan.my_app;
import java.sql.*;
import java.util.ArrayList;
public class database {
    static Connection con = null;
    static PreparedStatement ps;
    public static void getcon(){
        try {
            Class.forName("dm.jdbc.driver.DmDriver");
            con = DriverManager.getConnection("jdbc:dm://192.168.0.192:5236", "SYSDBA", "SYSDBA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkUser(String username,String password){
        try {
            ps=con.prepareStatement("select count(*) from TEST.USER1 where USERNAME=? and ADDRESS=?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            rs.next();
            if(rs.getInt(1)!=0){
                ps.close();
                con.close();
                return true;
            }
            ps.close();
            con.close();
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
