import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conn = DB.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM tb_order " +
                "INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id " +
                "INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");

        Map<Long, Order> orders = new HashMap<>();
        Map<Long, Product> products = new HashMap<>();
        while (rs.next()) {
            Long orderId = rs.getLong("order_id");
            if (orders.get(orderId) == null) {
                Order order = instantiateOrder(rs);
                orders.put(orderId, order);
            }

            Long productId = rs.getLong("product_id");
            if (products.get(productId) == null) {
                Product product = instantiateProduct(rs);
                products.put(productId, product);
            }
            orders.get(orderId).getProducts().add(products.get(productId));
        }

        for (Long orderId : orders.keySet()) {
            Order order = orders.get(orderId);
            System.out.println("\nOrder ID: " + order.getId() + " | At Moment: " + order.getMoment() + " | Status: " + order.getStatus());

            for (Product product : order.getProducts()) {
                System.out.println("\t"+ product.getDescription());
            }
        }
    }
    private static Order instantiateOrder(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setId(rs.getLong("order_id"));
        order.setLatitude(rs.getDouble("latitude"));
        order.setLongitude(rs.getDouble("longitude"));
        order.setMoment(rs.getTimestamp("moment").toInstant());
        order.setStatus(OrderStatus.values()[rs.getInt("status")]);

        return order;
    }
    private static Product instantiateProduct(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setId(rs.getLong("product_id"));
        product.setDescription(rs.getString("description"));
        product.setImage_uri(rs.getString("image_uri"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));

        return product;
    }
}