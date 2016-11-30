package handler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by vdenisov on 29/11/2016.
 */
public class JsonHandler {
    private static JSONParser parser = new JSONParser();


    public static String getContent(InputStream json) throws IOException {
        String content;

        try {

            Object obj = parser.parse(new InputStreamReader(json, "windows-1251"));

            JSONObject jsonObject = (JSONObject) obj;

            content = (String) jsonObject.get("content");
        } catch (ParseException e)

        {
            e.printStackTrace();
            content = "error";
        }
        return content;
    }
}
