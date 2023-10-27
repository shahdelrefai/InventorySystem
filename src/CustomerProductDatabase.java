import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends AbstractDatabase<CustomerProduct>{

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] variables = this.extractVariablesFromLine(line);
        return new CustomerProduct(variables[0], variables[1], LocalDate.parse(variables[2]));
    }

    @Override
    protected String getSearchKey(CustomerProduct record) {
        return record.getSearchKey();
    }

    @Override
    protected String getRecordLine(CustomerProduct record) {
        return record.lineRepresentation();
    }
}
