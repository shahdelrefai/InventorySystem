public class ProductDatabase extends AbstractDatabase<Product>{
    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] variables = this.extractVariablesFromLine(line);
        return new Product(variables[0], variables[1], variables[2], variables[3], Integer.parseInt(variables[4]), Float.parseFloat(variables[5]));
    }

    @Override
    public String getSearchKey(Product record) {
        return record.getSearchKey();
    }

    @Override
    public String getRecordLine(Product record) {
        return record.lineRepresentation();
    }
}
