public class OneJournal {
    public int id;
    public String text;
    public String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OneJournal(int id, String text, String date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public OneJournal() {
    }

    @Override
    public String toString() {
        return "OneJournal [date=" + date + ", id=" + id + ", text=" + text + "]";
    }
}
