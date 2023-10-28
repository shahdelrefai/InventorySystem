import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractDatabase<T> {
    protected ArrayList<T> records;
    protected String filename;

    public AbstractDatabase(String filename)
    {
        this.records = new ArrayList<>();
        this.filename = filename;
        this.readFromFile();
    }

    public void readFromFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(createRecordFrom(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[] extractVariablesFromLine(String line)
    {
        return line.split(",");
    }
    public abstract T createRecordFrom(String line);

    public ArrayList<T> returnAllRecords()
    {
        return this.records;
    }

    protected abstract String getSearchKey(T record);
    public boolean contains(String key)
    {
        return this.records.stream().anyMatch(record -> this.getSearchKey(record).equals(key));
    }

    public T getRecord(String key)
    {
        return this.records.stream().filter(record -> this.getSearchKey(record).equals(key)).findFirst().orElse(null);
    }

    public void insertRecord(T record)
    {
        this.records.add(record);
    }

    public void deleteRecord(String key)
    {
        int indexOfRecordToRemove = records.indexOf(records.stream()
                .filter(record -> getSearchKey(record).equals(key))
                .findFirst()
                .orElse(null));

        if(indexOfRecordToRemove >= 0)
        {
            this.records.remove(indexOfRecordToRemove);
            System.out.println("Record with key " + key + " removed successfully.");
        }
        else
        {
            System.out.println("Record with key " + key + " not found.");
        }
    }

    protected abstract String getRecordLine(T record);
    public void saveToFile()
    {
        try (FileWriter writer = new FileWriter(this.filename, false)) {
            for (T record : this.records) {
                writer.write(this.getRecordLine(record) + System.lineSeparator());
            }
            System.out.println("Data saved to file " + this.filename + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
