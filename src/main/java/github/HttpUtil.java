package github;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {

    public String fromHttpResponse(HttpResponse httpResponse){
        try {
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String fromHeader(HttpResponse httpResponse, String headerName){
        Header[] headers = httpResponse.getAllHeaders();
        for (Header header : headers) {
            if(headerName.equals(header.getName()))
                return header.getValue();
        }
        return null;
    }
}
