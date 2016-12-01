package handler;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.telegram.telegrambots.logging.BotLogger;

import java.io.InputStream;

import static org.apache.http.protocol.HTTP.USER_AGENT;

/**
 * Created by vdenisov on 29/11/2016.
 */
public class RestHandler {

    private static final String LOGTAG = "RESTHANDLER";

    private static String URL = "http://rzhunemogu.ru/RandJSON.aspx?CType=%d";

    public static InputStream getContent(int param) throws Exception {


        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(String.format(URL, param));

        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);
        if (response == null) {
            BotLogger.error(LOGTAG, String.format("реквест к сервису %s вернул нулевое значение", URL));
        }

        return response.getEntity().getContent();
    }
}
