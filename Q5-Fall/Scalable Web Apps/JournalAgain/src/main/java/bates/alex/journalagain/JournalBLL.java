package bates.alex.journalagain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class JournalBLL {
    private ArrayList<JEntryModel> entries = new ArrayList<JEntryModel>();

    public void save(JEntryModel entry) {
        entries.add(entry);
    }

    public ArrayList<JEntryModel> getEntries() {
        return entries;
    }

    public void findbByID() {

    }
}
