package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Admin;
import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {
    void saveAdmin(Admin admin) throws SQLException;
    Admin getAdminById(int id) throws SQLException;
    Admin getAdminByEmail(String email) throws SQLException;
    List<Admin> getAllAdmins() throws SQLException;
    void updateAdmin(Admin admin) throws SQLException;
    void deleteAdmin(int id) throws SQLException;
}