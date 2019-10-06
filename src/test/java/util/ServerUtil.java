package util;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ServerUtil {

    public static final int PORT = 8888;

    public void commits(int status, String response) {
        stubFor(get(urlEqualTo("/github/commits?sha=master&page=1&per_page=100"))
                .withBasicAuth("username", "password")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse().withHeader("X-RateLimit-Limit", "100")
                    .withHeader("Content-Type", "application/json").withBody(response).withStatus(status)));
    }

    public void issues(int status, String response) {
        stubFor(get(urlEqualTo("/github/issues?state=closed&page=1&per_page=100"))
                .withBasicAuth("username", "password")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse().withHeader("X-RateLimit-Limit", "100")
                        .withHeader("Content-Type", "application/json").withBody(response).withStatus(status)));
    }

    public void commit(int status, String response) {
        stubFor(get(urlEqualTo("/github/commits/019abc9980b9855bf04086c44b68f3282119c6b3"))
                .withBasicAuth("username", "password")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse().withHeader("X-RateLimit-Limit", "100")
                        .withHeader("Content-Type", "application/json").withBody(response).withStatus(status)));
    }
}
