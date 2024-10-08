package com.hsbc.ecommerce.daoImpl;

import com.hsbc.ecommerce.dao.AdminDAO;
import com.hsbc.ecommerce.models.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private final Connection connection;

    public AdminDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin (name, email, password, created_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            stmt.setTimestamp(4, new Timestamp(admin.getCreatedAt().getTime()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admin.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Admin getAdminById(int id) throws SQLException {
        String sql = "SELECT * FROM admin WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractAdminFromResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Admin getAdminByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM admin WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractAdminFromResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                admins.add(extractAdminFromResultSet(rs));
            }
        }
        return admins;
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        String sql = "UPDATE admin SET name = ?, email = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            stmt.setInt(4, admin.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAdmin(int id) throws SQLException {
        String sql = "DELETE FROM admin WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Admin extractAdminFromResultSet(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getInt("id"));
        admin.setName(rs.getString("name"));
        admin.setEmail(rs.getString("email"));
        admin.setPassword(rs.getString("password"));
        admin.setCreatedAt(rs.getTimestamp("created_at"));
        return admin;
    }
}
