public interface Products {

    // this method will return an object of 'Product'
    // searched by ID
    Product findById(long id);

    // this method will rinsert a 'Product' into the tabel,
    // the returns product's ID
    long createProduct(Product product);


    // we already have 'Create', and 'Delete' of 'CRUD',
    // we could add update/delete/etc
}
