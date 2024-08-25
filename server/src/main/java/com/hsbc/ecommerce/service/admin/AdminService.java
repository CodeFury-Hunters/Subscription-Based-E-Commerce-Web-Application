package com.hsbc.ecommerce.service.admin;

import com.hsbc.ecommerce.models.Admin;
import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    void addAdmin(Admin admin) throws SQLException;
    Admin getAdminById(int id) throws SQLException;
    List<Admin> getAllAdmins() throws SQLException;
    void updateAdmin(Admin admin) throws SQLException;
    void deleteAdmin(int id) throws SQLException;
}
