public class Product {
    private String productID, productName, manufacturerName, supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price)
    {
        this.productID =productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity()
    {
        return this.quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String lineRepresentation()
    {
        return this.productID + "," + this.productName + "," + this.manufacturerName + "," + this.supplierName + "," + this.quantity + "," + this.price;
    }

    public String getSearchKey()
    {
        return this.productID;
    }
}
