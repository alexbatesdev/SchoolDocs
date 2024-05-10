import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Program {
    public static void main(String[] args) {
        System.out.println("Bongis");
        ReadJSON();
    }

    public static void WriteHTML(JSONObject customerRecord, int iteration) {
        int strFileName = iteration + 1;
        String strHTMLTop = String.format("<!DOCTYPE html><html><head><title>%s %s's Portfolio</title></head><body>"
                , customerRecord.get("first_name")
                , customerRecord.get("last_name")
        );
        String strHTMLCustInfo = String.format("<h1>%s</h1><p>%s %s</p><p>%s</p>"
                , LocalDate.now()
                , customerRecord.get("first_name")
                , customerRecord.get("last_name")
                , customerRecord.get("phone")
        );

        String strTableTop = "<table style=\"width:00%\"><tr><th>Type</th><th>Symbol</th><th>Price</th><th>Shares</th><th>Total</th></tr>";
        // Beginning trades loop
        String strTableContent = "";
        JSONArray jsaTrades = (JSONArray) customerRecord.get("stock_trades");
        double dblStockTotal = 0;
        double dblCash = Double.parseDouble(customerRecord.get("beginning_balance").toString().substring(1));
        for (int i = 0; i < jsaTrades.size(); i++) {
            JSONObject trade = (JSONObject) jsaTrades.get(i);
            double dblSharePrice = Double.parseDouble(trade.get("price_per_share").toString().substring(1));
            int intShareCount = Integer.parseInt(trade.get("count_shares").toString());
            System.out.println(dblSharePrice);

            if (Objects.equals(trade.get("type").toString(), "Sell")) {
                dblCash += (dblSharePrice * intShareCount);
                dblStockTotal -= (dblSharePrice * intShareCount);
            } else {
                dblCash -= (dblSharePrice * intShareCount);
                dblStockTotal += (dblSharePrice * intShareCount);
            }


            strTableContent += String.format("<tr><td>%s</td><td>%s</td><td>$%.2f</td><td>%d</td><td>$%.2f</td></tr>"
                    , trade.get("type")
                    , trade.get("stock_symbol")
                    , dblSharePrice
                    , intShareCount
                    , (dblSharePrice * intShareCount)
            );
        }
        // End trades loop
        String strTableBot = "</table>";

        String strHTMLBot = String.format("<p>Cash: $%.2f</p><p>Stock Value: $%.2f</p></body></html>", dblStockTotal, dblCash);

        StringBuilder sb = new StringBuilder();
        sb.append(strHTMLTop);
        sb.append(strHTMLCustInfo);
        sb.append(strTableTop);
        sb.append(strTableContent);
        sb.append(strTableBot);
        sb.append(strHTMLBot);
        System.out.println(sb.toString());

        try {
            FileWriter fw;
            fw = new FileWriter("data\\" + strFileName + ".html");
            fw.write(sb.toString());

            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void ReadJSON() {
        try {
            Object json = new JSONParser().parse(new FileReader("data\\stocks.json"));
            JSONArray jsonA = (JSONArray) json;
            System.out.println("Read in JSON successfully");

            for (int i=0; i < jsonA.size(); i++) {

                JSONObject customerRecord = (JSONObject)  jsonA.get(i);
                WriteHTML(customerRecord, i);
                HTMLtoPDF.generatePDF(String.format("data/%d.html", i+1));

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
