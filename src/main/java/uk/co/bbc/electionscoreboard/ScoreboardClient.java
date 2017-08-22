package uk.co.bbc.electionscoreboard;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 * Created by Chris on 20-Aug-17.
 */
public class ScoreboardClient {

    private BufferedReader br;
    private FileReader fr;
    private InputStream in;

    public String readFile(String file) {

        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public void post(String xmlContent) {
        StringEntity entity = new StringEntity(xmlContent, ContentType.create(
                "text/xml", Consts.UTF_8));
        entity.setChunked(true);
        HttpPost httppost = new HttpPost(
                "http://localhost:8080/scores");

        httppost.setEntity(entity);
        httppost.setHeader("Accept", "application/xml");
        httppost.setHeader("Content-type", "application/xml");

        CloseableHttpClient client = HttpClients.createDefault();

        try {
            HttpResponse response = client.execute(httppost);
            System.out.println(response.toString());
            in=response.getEntity().getContent();
            String body = IOUtils.toString(in);
            System.out.println(body);
        } catch (ClientProtocolException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public String get() {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/scores");
        request.setHeader("Accept", "application/xml");
        request.setHeader("Content-type", "application/xml");
        StringBuffer result = new StringBuffer();

// add request header
        // request.addHeader("User-Agent", USER_AGENT);
        try {
            HttpResponse response = client.execute(request);

            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        ScoreboardClient scoreboardClient = new ScoreboardClient();
        DecimalFormat myFormatter = new DecimalFormat("000");

        for (int i = 1; i <= 650; i++) {
            System.out.println("result"+myFormatter.format(i));
            String resultFile = "C:\\Users\\Chris\\work\\election-results\\result" + myFormatter.format(i) + ".xml";
            String xmlContent = scoreboardClient.readFile(resultFile);
            scoreboardClient.post(xmlContent);
            System.out.println(scoreboardClient.get());
        }

    }
}
