import com.hsbc.ecommerce.service.admin.AdminServiceImpl;

import com.hsbc.ecommerce.dao.AdminDAO;
import com.hsbc.ecommerce.models.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceImplTest {

    @Mock
    private AdminDAO adminDAO;

    @InjectMocks
    private AdminServiceImpl adminService;

    private Admin admin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new Admin();
        admin.setId(1);
        admin.setName("Admin Name");
        admin.setEmail("admin@example.com");
    }

    @Test
    void addAdmin() throws SQLException {
        doNothing().when(adminDAO).saveAdmin(admin);

        adminService.addAdmin(admin);

        verify(adminDAO, times(1)).saveAdmin(admin);
    }

    @Test
    void getAdminById() throws SQLException {
        when(adminDAO.getAdminById(1)).thenReturn(admin);

        Admin retrievedAdmin = adminService.getAdminById(1);

        assertNotNull(retrievedAdmin);
        assertEquals(1, retrievedAdmin.getId());
        verify(adminDAO, times(1)).getAdminById(1);
    }

    @Test
    void getAllAdmins() throws SQLException {
        List<Admin> admins = Arrays.asList(admin);
        when(adminDAO.getAllAdmins()).thenReturn(admins);

        List<Admin> retrievedAdmins = adminService.getAllAdmins();

        assertNotNull(retrievedAdmins);
        assertEquals(1, retrievedAdmins.size());
        verify(adminDAO, times(1)).getAllAdmins();
    }

    @Test
    void updateAdmin() throws SQLException {
        doNothing().when(adminDAO).updateAdmin(admin);

        adminService.updateAdmin(admin);

        verify(adminDAO, times(1)).updateAdmin(admin);
    }

    @Test
    void deleteAdmin() throws SQLException {
        doNothing().when(adminDAO).deleteAdmin(1);

        adminService.deleteAdmin(1);

        verify(adminDAO, times(1)).deleteAdmin(1);
    }
}
