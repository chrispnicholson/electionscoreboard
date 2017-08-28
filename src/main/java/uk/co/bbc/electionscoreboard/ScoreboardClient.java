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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 * Created by Chris on 20-Aug-17.
 */
public class ScoreboardClient {

    private BufferedReader br;
    private FileReader fr;
    private InputStream in;

    static {
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.ERROR);
        Logger.getLogger("httpclient").setLevel(Level.ERROR);
    }

    public String readFile(String file) {

        Path path = FileSystems.getDefault().getPath(file);
        String content = "";

        try {
            content = new String(Files.readAllBytes(path.toAbsolutePath()));
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
            in=response.getEntity().getContent();
            String body = IOUtils.toString(in);
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

    public static void main(String[] args) throws InterruptedException {

        ScoreboardClient scoreboardClient = new ScoreboardClient();
        DecimalFormat myFormatter = new DecimalFormat("000");

        for (int i = 1; i <= 650; i++) {
            String resultFile = "src/main/resources/election-results/result" + myFormatter.format(i) + ".xml";
            String xmlContent = scoreboardClient.readFile(resultFile);
            scoreboardClient.post(xmlContent);
            System.out.println(scoreboardClient.get());
            Thread.sleep(1000l);
        }

    }
}
