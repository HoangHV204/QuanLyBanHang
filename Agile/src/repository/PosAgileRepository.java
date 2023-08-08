/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author LE MINH
 */
public class PosAgileRepository {

    public boolean checkLogin(String account, String password) {
        String query = """
                       SELECT * FROM dbo.USERS
                       WHERE username = ? AND password = ?
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, account);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public String getRole(String maNhanVien) {
        String query = """
                       SELECT role FROM dbo.USERS
                       WHERE username = ?
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, maNhanVien);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean rememberPassword(String account) {
        String query = """
                       UPDATE dbo.USERS
                       SET RememberPassword = 'ON'
                       WHERE username = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, account);
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public boolean notRememberPassword(String account) {
        String query = """
                       UPDATE dbo.USERS
                       SET RememberPassword = 'OFF'
                       WHERE username = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, account);
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public String getPassword(String account) {
        String query = """
                       SELECT password 
                       FROM dbo.USERS
                       WHERE username = ?
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, account);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public String checkRememberPassword(String account) {
        String query = """
                       SELECT  RememberPassword
                       FROM dbo.USERS
                       WHERE username = ?
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, account);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
