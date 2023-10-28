import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {
    private String customerSSN, productID;
    private LocalDate purchaseDate;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate)
    {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }

    public String getCustomerSSN()
    {
        return this.customerSSN;
    }

    public String getProductID()
    {
        return this.productID;
    }

    public LocalDate getPurchaseDate()
    {
        return this.purchaseDate;
    }

    public String lineRepresentation()
    {
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getSearchKey()
    {
        return lineRepresentation();
    }
}
