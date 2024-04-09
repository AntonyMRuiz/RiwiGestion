package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Company;
import entity.Contract;
import entity.Vacancy;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractModel implements CRUD {
    @Override
    public boolean create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contract objContract = (Contract) obj;
        boolean result = false;

        try {
            String slq = "INSERT INTO contract(status, salary, vacancyId, coderId) VALUES (?,?,?,?);";
            PreparedStatement pStatement = objConnection.prepareStatement(slq);

            pStatement.setString(1, objContract.getStatus());
            pStatement.setDouble(2, objContract.getSalary());
            pStatement.setInt(3, objContract.getVacancyId());
            pStatement.setInt(4, objContract.getCoderId());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Created Successfully Contract");
                JOptionPane.showMessageDialog(null, objContract.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error creating Contract: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> list = new ArrayList<>();

        try {
            String slq = "SELECT * FROM contract " +
                    "INNER JOIN coder ON contract.coderId = coder.id " +
                    "INNER JOIN vacancy ON contract.vacancyId = vacancy.id " +
                    "INNER JOIN company ON vacancy.companyId = company.id ;";

            PreparedStatement pStatement = objConnection.prepareStatement(slq);
            ResultSet rs = pStatement.executeQuery();

            fillContract(list, rs);

        } catch (SQLException e) {
            System.out.println("Error finding purchase: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return list;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contract objContract = (Contract) obj;
        boolean result = false;

        try {
            String sql = "UPDATE contract SET status = ?, salary = ?, vacancyId = ?, coderId = ? WHERE id = ?;";
            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            pStatement.setString(1, objContract.getStatus());
            pStatement.setDouble(2, objContract.getSalary());
            pStatement.setInt(3, objContract.getVacancyId());
            pStatement.setInt(4, objContract.getCoderId());
            pStatement.setInt(5, objContract.getId());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Updated Successfully Contract");
            }

        } catch (SQLException e) {
            System.out.println("Error updating Contract: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contract objContract = (Contract) obj;
        boolean result = false;

        try {
            String sql = "DELETE FROM contract WHERE id = ?;";

            PreparedStatement pStatement = objConnection.prepareStatement(sql);
            pStatement.setInt(1, objContract.getId());

            result = pStatement.executeUpdate() > 0;

            if (result) {
                JOptionPane.showMessageDialog(null, "Deleted Successfully Contract");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting Contract: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return result;
    }

    @Override
    public List<Object> getByField(String field, String search) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> filter = new ArrayList<>();

        try {
            String sql = "SELECT * FROM contract " +
                    "INNER JOIN coder ON contract.coderId = coder.id " +
                    "INNER JOIN vacancy ON contract.vacancyId = vacancy.id " +
                    "INNER JOIN company ON vacancy.companyId = company.id ;" +
                    "WHERE "+ field +" = ?;";

            PreparedStatement pStatement = objConnection.prepareStatement(sql);

            switch (field) {
                case "product.name" , "store.locate", "store.name", "customer.name":
                    pStatement.setString(1, search);
                    break;

                case "product.id", "store.id", "product.stock":
                    pStatement.setInt(1, Integer.parseInt(search));
                    break;

                case "product.price":
                    pStatement.setFloat(1, Float.parseFloat(search));
                    break;
            }

            ResultSet rs = pStatement.executeQuery();
            fillContract(filter, rs);

        } catch (SQLException e) {
            System.out.println("Error finding Purchase: " + e.getMessage() );
        }

        ConfigDB.closeConnection();
        return filter;
    }

    private void fillContract(List<Object> list, ResultSet rs) {

        try {
            while (rs.next()) {
                Contract objContract = new Contract();

                objContract.setId(rs.getInt("contract.id"));
                objContract.setStatus(rs.getString("contract.status"));
                objContract.setSalary(rs.getDouble("contract.salary"));
                objContract.setVacancyId(rs.getInt("contract.vacancyId"));
                objContract.setCoderId(rs.getInt("contract.coderId"));

                Coder objCoder = new Coder();
                objCoder.setId(rs.getInt("coder.id"));
                objCoder.setName(rs.getString("coder.name"));
                objContract.setVacancyId(rs.getInt("coder.vacancyId"));
                objContract.setCoderId(rs.getInt("coder.coderId"));

                objContract.setCoder(objCoder);

                Vacancy objVacancy = new Vacancy();
                objVacancy.setId(rs.getInt("vacancy.id"));
                objVacancy.setTitle(rs.getString("vacancy.title"));
                objVacancy.setDescription(rs.getString("vacancy.description"));
                objVacancy.setDuration(rs.getString("vacancy.duration"));
                objVacancy.setCompanyId(rs.getInt("vacancy.companyId"));
                objVacancy.setTechnology(rs.getString("vacancy.technology"));

                Company objCompany = new Company();
                objCompany.setId(rs.getInt("company.id"));
                objCompany.setName(rs.getString("company.name"));
                objCompany.setSector(rs.getString("company.sector"));
                objCompany.setLocation(rs.getString("company.location"));
                objCompany.setContact(rs.getString("company.contact"));

                objVacancy.setCompany(objCompany);
                objContract.setVacancy(objVacancy);

                list.add(objContract);
            }
        } catch (SQLException e) {
            System.out.println("Error fill Contract: " + e.getMessage() );
        }
    }
}
