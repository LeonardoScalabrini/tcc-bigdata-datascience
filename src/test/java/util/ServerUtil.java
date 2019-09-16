package util;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ServerUtil {

    public static final int PORT = 8888;

    public void commits(int status, String response) {
        stubFor(get(urlEqualTo("/github"))
                .withBasicAuth("username", "password")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse().withHeader("X-RateLimit-Limit", "100")
                    .withHeader("Content-Type", "application/json").withBody(response).withStatus(status)));
    }
}
