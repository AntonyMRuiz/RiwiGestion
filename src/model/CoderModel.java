package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {

    @Override
    public boolean create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        boolean result = false;

        try {
            String slq = "INSERT INTO coder(name, lastName, document, cohorte, cv, clan) VALUES (?,?,?,?,?,?);";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);

            pStatement.setString(1, objCoder.getName());
            pStatement.setString(2, objCoder.getLastName());
            pStatement.setString(3, objCoder.getDocument());
            pStatement.setInt(4, objCoder.getCohorte());
            pStatement.setString(5, objCoder.getCv());
            pStatement.setString(6, objCoder.getClan());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Created Successfully Coder");
            }

        } catch (SQLException e) {
            System.out.println("Error creating Coder: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> list = new ArrayList<>();

        try {
            String slq = "SELECT * FROM coder;";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);

            ResultSet rs = pStatement.executeQuery();
            fillCoder(list, rs);

        } catch (SQLException e) {
            System.out.println("Error finding Coder: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return list;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;

        boolean result = false;

        try {
            String sql = "UPDATE coder SET name = ?, lastName = ?, document = ?, cohorte = ?, cv = ? WHERE id = ?;";
            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            pStatement.setString(1, objCoder.getName());
            pStatement.setString(2, objCoder.getLastName());
            pStatement.setString(3, objCoder.getDocument());
            pStatement.setInt(4, objCoder.getCohorte());
            pStatement.setString(5, objCoder.getCv());
            pStatement.setInt(6, objCoder.getId());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Updated Successfully Coder");
            }

        } catch (SQLException e) {
            System.out.println("Error updating Coder: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        boolean result = false;

        try {
            String sql = "DELETE FROM coder WHERE id = ?;";
            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            pStatement.setInt(1, objCoder.getId());
            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Deleted Successfully Coder");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting Coder: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public List<Object> getByField(String field, String search) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> filter = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coder WHERE "+ field +" = ?;";

            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            switch (field) {
                case "coder.name", "coder.lastName", "coder.document", "coder.cv":
                    pStatement.setString(1, search);
                    break;

                case "coder.id", "coder.cohorte":
                    pStatement.setInt(1, Integer.parseInt(search));
                    break;
            }

            ResultSet rs = pStatement.executeQuery();
            fillCoder(filter, rs);

        } catch (SQLException e) {
            System.out.println("Error finding Coder: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return filter;
    }

    private void fillCoder(List<Object> list, ResultSet rs) {

        try {
            while (rs.next()) {
                Coder objCoder = new Coder();

                objCoder.setId(rs.getInt("coder.id"));
                objCoder.setName(rs.getString("coder.name"));
                objCoder.setLastName(rs.getString("coder.lastName"));
                objCoder.setDocument(rs.getString("coder.document"));
                objCoder.setCohorte(rs.getInt("coder.cohorte"));
                objCoder.setCv(rs.getString("coder.cv"));
                objCoder.setClan(rs.getString("coder.clan"));

                list.add(objCoder);
            }
        } catch (SQLException e) {
            System.out.println("Error fill Coder: " + e.getMessage() );
        }
    }

}
