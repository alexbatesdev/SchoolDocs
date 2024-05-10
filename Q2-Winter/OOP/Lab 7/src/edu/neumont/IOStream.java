package edu.neumont;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class IOStream {

    public static void writeToStream(InputStream input, OutputStream output) throws Exception {
        int c;
        while ((c = input.read()) != -1) {
            output.write(c);
        }
    }

    public static void writeToStream(String string, OutputStream output) throws Exception {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        output.write(bytes);
    }

    public static void writeToConsole(InputStream input) throws Exception {
        int c;
        while ((c = input.read()) != -1) {
            System.out.print((char)c);
        }
    }

    public static String readToString(InputStream input) throws Exception {
        int size = input.available();
        byte[] bytes = new byte[size];
        input.read(bytes);

        return new String(bytes);
    }
}
