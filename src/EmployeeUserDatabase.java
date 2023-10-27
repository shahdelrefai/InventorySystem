public class EmployeeUserDatabase extends AbstractDatabase<EmployeeUser> {
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
        String[] variables = this.extractVariablesFromLine(line);
        return new EmployeeUser(variables[0], variables[1], variables[2], variables[3], variables[4]);
    }

    @Override
    protected String getSearchKey(EmployeeUser record) {
        return record.getSearchKey();
    }

    @Override
    protected String getRecordLine(EmployeeUser record) {
        return record.lineRepresentation();
    }
}
