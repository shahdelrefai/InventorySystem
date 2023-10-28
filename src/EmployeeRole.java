import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole()
    {
        this.productsDatabase = new ProductDatabase("Products.txt");
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price)
    {
        productsDatabase.insertRecord(new Product(productID, productName, manufacturerName, supplierName, quantity, price));
    }
    public Product[] getListOfProducts()
    {
        return productsDatabase.returnAllRecords().toArray(new Product[0]);
    }

    public CustomerProduct[] getListOfPurchasingOperations()
    {
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate)
    {
        Product product = productsDatabase.getRecord(productID);

        if(product != null)
        {
            if(product.getQuantity() > 0)
            {
                product.setQuantity(product.getQuantity() - 1);
                customerProductDatabase.insertRecord(new CustomerProduct(customerSSN, productID, purchaseDate));

                productsDatabase.saveToFile();
                customerProductDatabase.saveToFile();

                System.out.println("Purchase made successfully.");
                return true;
            }
            else
            {
                System.out.println("Product is out of stock.");
                return false;
            }
        }
        else
        {
            System.out.println("Product not found.");
            return false;
        }
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate)
    {
        if (returnDate.isBefore(purchaseDate))
        {
            System.out.println("14 days have passed since purchasing this product. You can't return it.");
            return -1;
        }

        Product product = productsDatabase.getRecord(productID);
        if(product == null)
        {
            System.out.println("Product not found.");
            return -1;
        }

        String searchKey = customerSSN + "," + productID + "," + purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if(customerProductDatabase.getRecord(searchKey) == null)
        {
            System.out.println("Purchase not found.");
            return -1;
        }

        if(Period.between(purchaseDate, returnDate).getDays() > 14)
        {
            System.out.println("Return date is earlier than purchase date.. Can't do operation.");
            return -1;
        }

        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(searchKey);

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        return product.getPrice();
    }

    public void logout()
    {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}
