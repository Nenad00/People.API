package code.academy.client;

import javax.net.ssl.SSLContext;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class PeopleApiClient {
    public PeopleApiClient() {
    }

    public HttpResponse getWelcomeRequest() throws Exception {
        Header contentType = new BasicHeader("Content-Type", "application/json");
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        HttpGet requets = new HttpGet("https://people-api1.herokuapp.com/");

        requets.setHeader(contentType);

        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();

        HttpResponse response = httpClient.execute(requets);

        HttpEntity entity = response.getEntity();

        String body = EntityUtils.toString(response.getEntity());

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);

        return response;
    }

    public HttpResponse getAllPeople() throws Exception {
        Header contentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        SSLContext sslContext = SSLContextBuilder
                .create().loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        HttpGet requets = new HttpGet("https://people-api1.herokuapp.com/api/people");

        requets.setHeader(contentType);

        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();

        HttpResponse response = httpClient.execute(requets);

        HttpEntity entity = response.getEntity();

        String body = EntityUtils.toString(response.getEntity());

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);

        return response;
    }

    public HttpResponse getOnePerson() throws Exception {
        Header contentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        HttpGet requets = new HttpGet("https://people-api1.herokuapp.com/api/person/");

        requets.setHeader(contentType);

        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();

        HttpResponse response = httpClient.execute(requets);

        HttpEntity entity = response.getEntity();

        String body = EntityUtils.toString(response.getEntity());

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);

        return response;
    }

    public HttpResponse deleteOnePerson() throws Exception {
        Header contentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        SSLContext sslContext = SSLContextBuilder
                .create().
                loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        HttpDelete request = new HttpDelete("https://people-api1.herokuapp.com/api/person/61426cd910dd210004fc0035");

        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();

        HttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(response.getEntity());

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);

        return response;

    }

    public HttpResponse postNewPerson() throws Exception {
        Header contentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        HttpPost request = new HttpPost("https://people-api1.herokuapp.com/api/person");

        JSONObject payloadAsObject = new JSONObject();
        payloadAsObject.put("name", "Pero");
        payloadAsObject.put("surname", "Blazevski");
        payloadAsObject.put("age", 56);
        payloadAsObject.put("isEmployed", true);
        payloadAsObject.put("location", "Skopje");

        request.setHeader(contentType);
        request.setEntity(new StringEntity(payloadAsObject.toString()));

        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();

        HttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(response.getEntity());

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);

        return response;
    }

    public HttpResponse putNewPerson() throws Exception {
        Header contentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        HttpPut request = new HttpPut("https://people-api1.herokuapp.com/api/person/613f9adcbd3c300004771f6f");

        JSONObject payloadAsObject = new JSONObject();
        payloadAsObject.put("location", "New York");

        request.setHeader(contentType);
        request.setEntity(new StringEntity(payloadAsObject.toString()));

        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();

        HttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(response.getEntity());

        HttpEntity newEntity = new StringEntity(body, ContentType.get(entity));
        response.setEntity(newEntity);

        return response;
    }
}