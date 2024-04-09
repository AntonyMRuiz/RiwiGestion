package model;

import database.CRUD;
import database.ConfigDB;
import entity.Company;
import entity.Vacancy;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacancyModel implements CRUD {

    @Override
    public boolean create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacancy objVacancy = (Vacancy) obj;
        boolean result = false;

        try {
            String slq = "INSERT INTO vacancy(title, description, duration, status, technology, companyId) VALUES (?,?,?,?,?,?);";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);

            pStatement.setString(1, objVacancy.getTitle());
            pStatement.setString(2, objVacancy.getDescription());
            pStatement.setString(3, objVacancy.getDuration());
            pStatement.setString(4, objVacancy.getStatus());
            pStatement.setString(5, objVacancy.getTechnology());
            pStatement.setInt(6, objVacancy.getCompanyId());

            result = pStatement.executeUpdate() > 0;
            ResultSet rs = pStatement.getGeneratedKeys();
            if (rs.next()) {
                objVacancy.setId(rs.getInt("id"));
            }

            if (result) {
                JOptionPane.showMessageDialog(null, "Created Successfully Vacancy");
            }

        } catch (SQLException e) {
            System.out.println("Error creating Vacancy: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> list = new ArrayList<>();

        try {
            String slq = "SELECT * FROM vacancy INNER JOIN company ON vacancy.companyId = company.id;";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);
            ResultSet rs = pStatement.executeQuery();

            fillVacancy(list, rs);

        } catch (SQLException e) {
            System.out.println("Error finding Product: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return list;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacancy objVacancy = (Vacancy) obj;
        boolean result = false;

        try {
            String sql = "UPDATE vacancy SET title = ?, description = ?, duration = ?, status = ?, technology = ?, companyId = ? WHERE id = ?;";
            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            pStatement.setString(1, objVacancy.getTitle());
            pStatement.setString(2, objVacancy.getDescription());
            pStatement.setString(3, objVacancy.getDuration());
            pStatement.setString(4, objVacancy.getStatus());
            pStatement.setString(5, objVacancy.getTechnology());
            pStatement.setInt(6, objVacancy.getCompanyId());
            pStatement.setInt(7, objVacancy.getId());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Updated Successfully Vacancy");
            }

        } catch (SQLException e) {
            System.out.println("Error updating Vacancy: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacancy objVacancy = (Vacancy) obj;
        boolean result = false;

        try {
            String sql = "DELETE FROM vacancy WHERE id = ?;";

            PreparedStatement pStatement = objConnection.prepareStatement(sql);
            pStatement.setInt(1, objVacancy.getId());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Deleted Successfully Vacancy");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting Vacancy: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    public boolean updateStatus(String status, int id) {
        Connection objConnection = ConfigDB.openConnection();
        boolean result = false;

        try {
            String slq = "UPDATE vacancy SET status = ? WHERE id = ?;";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);
            pStatement.setString(1, status);
            pStatement.setInt(2, id);
            result = pStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error change status Vacancy: " + e.getMessage() );
        }

        return result;
    }

    @Override
    public List<Object> getByField(String field, String search) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> filter = new ArrayList<>();

        try {
            String sql = "SELECT * FROM vacancy " +
                    "INNER JOIN company ON vacancy.companyId = company.id WHERE "+ field +" = ?;";

            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            switch (field) {
                case "company.name", "company.sector", "company.location", "company.contact",
                     "vacancy.title", "vacancy.description", "vacancy.duration", "vacancy.status", "vacancy.technology":
                    pStatement.setString(1, search);
                    break;

                case "vacancy.id", "company.id" :
                    pStatement.setInt(1, Integer.parseInt(search));
                    break;

            }

            ResultSet rs = pStatement.executeQuery();
            fillVacancy(filter, rs);

        } catch (SQLException e) {
            System.out.println("Error finding Vacancy: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return filter;
    }

    private void fillVacancy(List<Object> list, ResultSet rs) {

        try {
            while (rs.next()) {
                Vacancy objVacancy = new Vacancy();

                objVacancy.setId(rs.getInt("vacancy.id"));
                objVacancy.setTitle(rs.getString("vacancy.title"));
                objVacancy.setDescription(rs.getString("vacancy.description"));
                objVacancy.setDuration(rs.getString("vacancy.duration"));
                objVacancy.setStatus(rs.getString("vacancy.status"));
                objVacancy.setTechnology(rs.getString("vacancy.technology"));

                Company objCompany = new Company();
                objCompany.setId(rs.getInt("company.id"));
                objCompany.setName(rs.getString("company.name"));
                objCompany.setLocation(rs.getString("company.location"));
                objCompany.setContact(rs.getString("company.contact"));

                objVacancy.setCompany(objCompany);

                list.add(objVacancy);
            }

        } catch (SQLException e) {
            System.out.println("Error fill Vacancy: " + e.getMessage() );
        }
    }
}
