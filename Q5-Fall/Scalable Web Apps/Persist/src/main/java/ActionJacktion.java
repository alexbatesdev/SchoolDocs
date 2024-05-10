import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionJacktion {

    private File file = new File("entries.json");

    public void save(OneJournal journal) throws IOException {
        List<OneJournal> entries = findAll();
        entries.add(journal);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); //used for less common data types
        //make pretty
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(file, entries);


    }

    public List<OneJournal> findAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        List<OneJournal> entries = null;
        try {
            entries = mapper.readValue(file, new TypeReference<List<OneJournal>>() {});
            System.out.println("Entries: " + entries);
        } catch (Exception e) {
            System.out.println("Oops: " + e.getMessage());
        }
        return entries;
    }

    public OneJournal findById(int id) {
        List<OneJournal> entries = findAll();
        for (OneJournal entry : entries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

}
