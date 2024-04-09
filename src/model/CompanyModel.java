package model;

import database.CRUD;
import database.ConfigDB;
import entity.Company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyModel implements CRUD {
    @Override
    public boolean create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Company objCompany = (Company) obj;
        boolean result = false;

        try {
            String slq = "INSERT INTO company(name, sector, location, contact) VALUES (?,?,?,?);";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);

            pStatement.setString(1, objCompany.getName());
            pStatement.setString(2, objCompany.getSector());
            pStatement.setString(3, objCompany.getLocation());
            pStatement.setString(4, objCompany.getContact());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Created Successfully Company");
            }

        } catch (SQLException e) {
            System.out.println("Error creating Company: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> list = new ArrayList<>();

        try {
            String slq = "SELECT * FROM company;";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);
            ResultSet rs = pStatement.executeQuery();

            fillCompany(list, rs);

        } catch (SQLException e) {
            System.out.println("Error finding Company: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return list;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Company objCompany = (Company) obj;

        boolean result = false;

        try {
            String sql = "UPDATE company SET name = ?, sector = ?, location = ?, contact = ? WHERE id = ?;";
            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            pStatement.setString(1, objCompany.getName());
            pStatement.setString(2, objCompany.getSector());
            pStatement.setString(3, objCompany.getLocation());
            pStatement.setString(4, objCompany.getContact());
            pStatement.setInt(5, objCompany.getId());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Updated Successfully Company");
            }

        } catch (SQLException e) {
            System.out.println("Error updating Company: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Company objCompany = (Company) obj;
        boolean result = false;

        try {
            String sql = "DELETE FROM company WHERE id = ?;";
            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            pStatement.setInt(1, objCompany.getId());
            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Deleted Successfully Company");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting Company: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public List<Object> getByField(String field, String search) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> filter = new ArrayList<>();

        try {
            String sql = "SELECT * FROM company WHERE "+ field +" = ?;";

            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            switch (field) {
                case "company.name", "company.sector", "company.location", "company.contact":
                    pStatement.setString(1, search);
                    break;

                case "company.id":
                    pStatement.setInt(1, Integer.parseInt(search));
                    break;
            }

            ResultSet rs = pStatement.executeQuery();

            fillCompany(filter, rs);

        } catch (SQLException e) {
            System.out.println("Error finding Company: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return filter;
    }

    private void fillCompany(List<Object> list, ResultSet rs) {

        try {
            while (rs.next()) {
                Company objCompany = new Company();

                objCompany.setId(rs.getInt("company.id"));
                objCompany.setName(rs.getString("company.name"));
                objCompany.setSector(rs.getString("company.sector"));
                objCompany.setLocation(rs.getString("company.location"));
                objCompany.setContact(rs.getString("company.contact"));

                list.add(objCompany);
            }
        } catch (SQLException e) {
            System.out.println("Error fill Company: " + e.getMessage() );
        }
    }

}
