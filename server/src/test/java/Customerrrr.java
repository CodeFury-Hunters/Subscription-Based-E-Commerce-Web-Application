import  com.hsbc.ecommerce.service.customer.CustomerActionServiceImpl;
import com.hsbc.ecommerce.service.order.*;
import com.hsbc.ecommerce.service.orderproduct.*;

import com.hsbc.ecommerce.models.Order;
import com.hsbc.ecommerce.models.Subscription;
import com.hsbc.ecommerce.service.order.OrderService;
import com.hsbc.ecommerce.service.subscription.SubscriptionService;
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

class CustomerActionServiceImplTest {

    @Mock
    private OrderService orderService;

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private CustomerActionServiceImpl customerActionService;

    private Order order;
    private Subscription subscription;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1);
        order.setCustomerId(1001);


        subscription = new Subscription();
        subscription.setId(1);
        subscription.setCustomerId(1001);

    }

    @Test
    void placeOrder() throws SQLException {
        doNothing().when(orderService).createOrder(order);

        customerActionService.placeOrder(order);

        verify(orderService, times(1)).createOrder(order);
    }

    @Test
    void viewOrderHistory() throws SQLException {
        List<Order> orders = Arrays.asList(order);
        when(orderService.getOrdersByCustomerId(1001)).thenReturn(orders);

        List<Order> retrievedOrders = customerActionService.viewOrderHistory(1001);

        assertNotNull(retrievedOrders);
        assertEquals(1, retrievedOrders.size());
        verify(orderService, times(1)).getOrdersByCustomerId(1001);
    }

    @Test
    void viewSubscriptions() throws SQLException {
        List<Subscription> subscriptions = Arrays.asList(subscription);
        when(subscriptionService.getSubscriptionsByCustomerId(1001)).thenReturn(subscriptions);

        List<Subscription> retrievedSubscriptions = customerActionService.viewSubscriptions(1001);

        assertNotNull(retrievedSubscriptions);
        assertEquals(1, retrievedSubscriptions.size());
        verify(subscriptionService, times(1)).getSubscriptionsByCustomerId(1001);
    }
}
