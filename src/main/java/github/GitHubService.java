package github;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.util.Arrays;
import java.util.Base64;

public class GitHubService {

    public HttpResponse commits(GitHubConfig gitHubConfig, String branch, Integer page, Integer perPage) throws Exception {

        HttpClient client = getHttpClient(gitHubConfig);

        HttpUriRequest request = RequestBuilder.get().setUri(String.format("%s/commits?sha=%s&page=%s&per_page=%s",
                gitHubConfig.repository, branch, page, perPage)).build();

        return client.execute(request);
    }

    public HttpResponse issues(GitHubConfig gitHubConfig, Integer page, Integer perPage) throws Exception {

        HttpClient client = getHttpClient(gitHubConfig);

            HttpUriRequest request = RequestBuilder.get().setUri(String.format("%s/issues?state=closed&page=%s&per_page=%s",
                gitHubConfig.repository, page, perPage)).build();

        return client.execute(request);
    }

    public HttpResponse commit(GitHubConfig gitHubConfig, String commit) throws Exception {

        HttpClient client = getHttpClient(gitHubConfig);

        HttpUriRequest request = RequestBuilder.get().setUri(String.format("%s/commits/%s",
                gitHubConfig.repository, commit)).build();

        return client.execute(request);
    }

    public HttpResponse raw(GitHubConfig gitHubConfig, Commit commit, File file) throws Exception {
        HttpClient client = getHttpClient(gitHubConfig);
        HttpUriRequest request = RequestBuilder.get().setUri(String.format("%s/%s/%s",
                gitHubConfig.raw, commit.getSha(), file.getFilename())).build();

        return client.execute(request);
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
