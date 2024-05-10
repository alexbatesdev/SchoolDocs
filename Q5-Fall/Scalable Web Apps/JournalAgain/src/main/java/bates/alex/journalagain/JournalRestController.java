package bates.alex.journalagain;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalRestController {

//    @RequestMapping(path="/hello", method=RequestMethod.GET)
//    public String hello() {
//        return "Hello World";
//    }
//
//    @RequestMapping(path="/echo", method=RequestMethod.GET)
//    public String echo(@RequestParam String strText) {
//        return strText;
//    }

    private JournalBLL journalOnion = new JournalBLL();

    @RequestMapping(path="", method= RequestMethod.POST)
    public void CreateJournalEntry(@RequestBody JEntryModel entry) {
        entry.setCreateDate(LocalDateTime.now().toString());
        journalOnion.save(entry);
    }

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<JEntryModel> findAllEntries() {
        return journalOnion.getEntries();
    }
}
