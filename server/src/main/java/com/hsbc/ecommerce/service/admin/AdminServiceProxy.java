package com.hsbc.ecommerce.service.admin;

import com.hsbc.ecommerce.dao.AdminDAO;
import com.hsbc.ecommerce.models.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceProxy implements AdminService {

    private AdminService adminService;
    private boolean isAuthenticated;
    private boolean isAuthorized;

    public AdminServiceProxy(String username, String password, AdminDAO adminDAO) {
        // Simple authentication check (replace with real authentication logic)
        if (username.equals("admin") && password.equals("admin123")) {
            this.isAuthenticated = true;
            this.isAuthorized = true; // Here you could also add a role check for finer authorization
            this.adminService = new AdminServiceImpl(adminDAO); // Initialize AdminServiceImpl with actual AdminDAO instance
        } else {
            this.isAuthenticated = false;
            this.isAuthorized = false;
        }
    }

    private void checkAccess() throws SecurityException {
        if (!isAuthenticated) {
            throw new SecurityException("User is not authenticated.");
        }
        if (!isAuthorized) {
            throw new SecurityException("User is not authorized.");
        }
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        checkAccess();
        adminService.addAdmin(admin);
    }

    @Override
    public Admin getAdminById(int id) throws SQLException {
        checkAccess();
        return adminService.getAdminById(id);
    }

    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        checkAccess();
        return adminService.getAllAdmins();
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        checkAccess();
        adminService.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(int id) throws SQLException {
        checkAccess();
        adminService.deleteAdmin(id);
    }
}
