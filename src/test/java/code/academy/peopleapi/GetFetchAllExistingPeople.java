package code.academy.peopleapi;


import code.academy.client.PeopleApiClient;
import code.academy.model.requests.PostNewPersonRequest;
import code.academy.model.response.GetAllPeopleResponse;
import code.academy.model.response.PostNewPersonResponse;
import code.academy.payloads.PostNewPersonPayload;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static code.academy.utils.ConversionUtils.jsonStringToObject;
import static org.apache.http.HttpStatus.SC_OK;

@Test
public class GetFetchAllExistingPeople {
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    PostNewPersonResponse postNewPersonResponse = new PostNewPersonResponse();
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();

    public GetFetchAllExistingPeople() throws Exception {
    }

    @Test
    public void FetchAllExistingPeople() throws Exception {
        response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
        String body = EntityUtils.toString(response.getEntity());

        GetAllPeopleResponse getAllPeopleResponse = jsonStringToObject(body,GetAllPeopleResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_OK);
        Assert.assertEquals(getAllPeopleResponse.getCode(),"P200");
        Assert.assertEquals(getAllPeopleResponse.getMessage(),"List of people successfully fetched");

        Assert.assertNotNull(getAllPeopleResponse.getNumberOfPeople());
        Assert.assertNotNull(getAllPeopleResponse.getPeopleData().size());
    }
 @Test
    public void NumberOfPeopleCorrectlyCounting() throws Exception {
     response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
     String body = EntityUtils.toString(response.getEntity());

     GetAllPeopleResponse getAllPeopleResponse = jsonStringToObject(body,GetAllPeopleResponse.class);

     Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_OK);
     Assert.assertEquals(getAllPeopleResponse.getCode(),"P200");
     Assert.assertEquals(getAllPeopleResponse.getPeopleData().size(),getAllPeopleResponse.getNumberOfPeople());
 }

}
