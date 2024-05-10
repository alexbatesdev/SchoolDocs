package com.example.finalfour;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseHTML implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected TextField urlInput;

    @FXML
    protected Label lookat;

    @FXML
    protected Label output;

    @FXML
    protected void onParseHTML() {
        URL url = null;
        InputStream inputStream = null;
        StringBuffer sb = null;
        try {
            url = new URL("https://" + urlInput.getText());
            inputStream = url.openStream();
            int pointer = 0;
            sb = new StringBuffer();
            while ((pointer = inputStream.read()) != -1) {
                try {
                    sb.append((char) pointer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pointer++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String html = sb.toString();
        System.out.println(html);

        Pattern p = Pattern.compile("<a href=\"(.*?)\".*>.*");
        Matcher m = p.matcher(html);
        lookat.setVisible(true);
        output.setVisible(true);
        while (m.find()) {
            output.setText(output.getText() + m.group(0) + "\n");
            System.out.println(m.group(0) + " - " + m.group(1));
        }
    }
}
