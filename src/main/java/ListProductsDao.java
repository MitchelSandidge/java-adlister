import java.util.ArrayList;
import java.util.List;


// This is the DAO (Data Access Object)
public class ListProductsDao implements Products {
    // list all the products, find a product by ID, create a product

    // This will list all the products in the database table
    private List<Product> products;

    // Constructor for the DAO class
    public ListProductsDao(){
        this.products = new ArrayList<>();
        // add some dummy data

        Product hammer = new Product();
        hammer.setId(1);
        hammer.setTitle("A bad hammer");
        hammer.setPriceInCents(3000);
        hammer.setDescription("A Bad hammer");
        products.add(hammer);

        Product xbox = new Product();
        xbox.setId(2);
        xbox.setTitle("Xbox Series X");
        xbox.setPriceInCents(50000);
        xbox.setDescription("This will be more powerful than a gaming PC");
        products.add(xbox);

        Product chiaPet = new Product();
        chiaPet.setId(3);
        chiaPet.setTitle("1999 Chia Pet");
        chiaPet.setPriceInCents(100);
        chiaPet.setDescription("A useless product");
        products.add(chiaPet);
    }

    // Implement our interface requirements (from Products.java)
    @Override
    public Product findById(long id) {
        // We want to return the 'Product' object for the ID passed
        // (full row in the database)

        return products.get((int)id -1);
    }

    @Override
    public long createProduct(Product product) {
        // Create a product and insert to our ArrayList
        // assign an ID

        product.setId(products.size()+1); // auto increment

        products.add(product); // when we call createProduct we are sending in a 'Product' type object
                            // this will add that product to the ArrayList

        return product.getId();
    }
}
