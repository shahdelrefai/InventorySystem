public class ProductDatabase extends AbstractDatabase<Product>{
    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] variables = this.extractVariablesFromLine(line);
        return new Product(variables[0], variables[1], variables[2], variables[3], Integer.parseInt(variables[4]), Integer.parseInt(variables[5]));
    }

    @Override
    protected String getSearchKey(Product record) {
        return record.getSearchKey();
    }

    @Override
    protected String getRecordLine(Product record) {
        return record.lineRepresentation();
    }
}
