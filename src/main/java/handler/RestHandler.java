package handler;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;

import static org.apache.http.protocol.HTTP.USER_AGENT;

/**
 * Created by vdenisov on 29/11/2016.
 */
public class RestHandler {

    private static String URL = "http://rzhunemogu.ru/RandJSON.aspx?CType=%d";

    public static InputStream getContent(int param) throws Exception {


        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(String.format(URL, param));

        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        return response.getEntity().getContent();
    }
}
