
// This DAO class will provide access to our Model data,
// but will not require accessing ListProductsDao class directly
// Instead we will reference the Interface
public class DaoFactory {
    private static Products productsDao;

    public static Products getProductsDao() {
        if(productsDao == null) {
            productsDao = new ListProductsDao(); // this is the ONLY reference to the
                                                        // ListProductsDao class
        }
        // if not null, return productsDao that already exists
            // this is an instance of the ProductsDao class
        return productsDao;
    }
}
