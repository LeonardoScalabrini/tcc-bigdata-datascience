package github;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class GitHubService {

    public String commits() throws IOException {

        String encoding = Base64.getEncoder().encodeToString("leonardoscalabrini01@gmail.com:leo96100@hac".getBytes());

        Header content = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        Header auth = new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
        HttpClient client = HttpClients.custom()
                .setDefaultHeaders(Arrays.asList(auth, content))
                .build();

        HttpUriRequest request = RequestBuilder.get().setUri("https://api.github.com/repos/iluwatar/java-design-patterns/commits").build();

        HttpResponse httpResponse = client.execute(request);
        Header[] headers = httpResponse.getAllHeaders();
        for (Header header : headers) {
            System.out.println("Key : " + header.getName()
                    + " ,Value : " + header.getValue());
        }

        return EntityUtils.toString(httpResponse.getEntity());
    }
}
