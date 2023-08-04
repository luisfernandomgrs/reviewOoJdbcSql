import db.DB;
import entities.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conn = DB.getConnection();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("select * from tb_product");

        while (rs.next()) {
            Product p = instantiateProduct(rs);
            System.out.println(p);
        }
    }

    private static Product instantiateProduct(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setId(rs.getLong("id"));
        product.setDescription(rs.getString("description"));
        product.setImage_uri(rs.getString("image_uri"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));

        return product;
    }
}