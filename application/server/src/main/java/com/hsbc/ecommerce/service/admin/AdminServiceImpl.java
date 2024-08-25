package com.hsbc.ecommerce.service.admin;

import com.hsbc.ecommerce.dao.AdminDAO;
import com.hsbc.ecommerce.models.Admin;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        adminDAO.saveAdmin(admin);
    }

    @Override
    public Admin getAdminById(int id) throws SQLException {
        return adminDAO.getAdminById(id);
    }

    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        return adminDAO.getAllAdmins();
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        adminDAO.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(int id) throws SQLException {
        adminDAO.deleteAdmin(id);
    }
}
