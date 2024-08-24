package com.hsbc.ecommerce.dao.ProductDAO;

import com.hsbc.ecommerce.models.OrderProduct;
import java.util.List;

public interface ProductorderDAO {
    void deleteOrderProduct(int orderId, int productId);
    List<OrderProduct> getOrderProductsByOrderId(int orderId);
}
