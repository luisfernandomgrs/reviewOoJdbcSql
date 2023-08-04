package entities;

public class Product {
    private Long id;
    private String description;
    private String image_uri;
    private String name;
    private Double price;

    public Product() {
    }

    public Product(Long id, String description, String image_uri, String name, Double price) {
        this.id = id;
        this.description = description;
        this.image_uri = image_uri;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image_uri='" + image_uri + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
