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

    public String commits(GitHubConfig gitHubConfig, String branch) throws IOException {

        HttpClient client = getHttpClient(gitHubConfig);

        HttpUriRequest request = RequestBuilder.get().setUri(String.format("%s/commits?sha=%s",
                gitHubConfig.repository, branch)).build();

        HttpResponse httpResponse = client.execute(request);
        Header[] headers = httpResponse.getAllHeaders();
        for (Header header : headers) {
            System.out.println("Key : " + header.getName()
                    + " ,Value : " + header.getValue());
        }

        return EntityUtils.toString(httpResponse.getEntity());
    }

    public String issues(GitHubConfig gitHubConfig, Integer page, Integer perPage) throws IOException {

        HttpClient client = getHttpClient(gitHubConfig);

        HttpUriRequest request = RequestBuilder.get().setUri(String.format("%s/issues?state=closed&page=%s&per_page=%s",
                gitHubConfig.repository, page, perPage)).build();

        HttpResponse httpResponse = client.execute(request);
        Header[] headers = httpResponse.getAllHeaders();
        for (Header header : headers) {
            System.out.println("Key : " + header.getName()
                    + " ,Value : " + header.getValue());
        }

        return EntityUtils.toString(httpResponse.getEntity());
    }

    public String commit(GitHubConfig gitHubConfig, String commit) throws IOException {

        HttpClient client = getHttpClient(gitHubConfig);

        HttpUriRequest request = RequestBuilder.get().setUri(String.format("%s/commits/%s",
                gitHubConfig.repository, commit)).build();

        HttpResponse httpResponse = client.execute(request);
        Header[] headers = httpResponse.getAllHeaders();
        for (Header header : headers) {
            System.out.println("Key : " + header.getName()
                    + " ,Value : " + header.getValue());
        }

        return EntityUtils.toString(httpResponse.getEntity());
    }

    private HttpClient getHttpClient(GitHubConfig gitHubConfig) {
        String encoding = Base64.getEncoder().encodeToString((gitHubConfig.username + ":" + gitHubConfig.password).getBytes());

        Header content = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        Header auth = new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
        return HttpClients.custom()
                .setDefaultHeaders(Arrays.asList(auth, content))
                .build();
    }
}
