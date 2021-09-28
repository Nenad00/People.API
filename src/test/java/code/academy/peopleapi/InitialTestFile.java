package code.academy.peopleapi;


import code.academy.client.PeopleApiClient;
import code.academy.model.requests.PostNewPersonRequest;
import code.academy.model.requests.PutNewLocationRequest;
import code.academy.model.response.*;
import code.academy.payloads.PostNewPersonPayload;
import code.academy.payloads.PutNewLocationPayload;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import static code.academy.utils.ConversionUtils.*;
import static org.apache.http.HttpStatus.*;


@Test
public class InitialTestFile {
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    PostNewPersonResponse postNewPersonResponse;

    public InitialTestFile() throws Exception {
    }

   @Test
    public void welcomeMessagePeopleApiTest() throws Exception {
       response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/");
       String body = EntityUtils.toString(response.getEntity());

       GetMessageResponse getMessageResponse = jsonStringToObject(body,GetMessageResponse.class);

       Assert.assertEquals(getMessageResponse.getMessage(),"Welcome to People API");

    }
    @Test
    public void getAllPeopleTest() throws Exception{
            response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
            String body = EntityUtils.toString(response.getEntity());

            GetAllPeopleResponse getAllPeopleResponse = jsonStringToObject(body,GetAllPeopleResponse.class);

       Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_OK);
     Assert.assertEquals(getAllPeopleResponse.getCode(),"P200");
       Assert.assertEquals(getAllPeopleResponse.getNumberOfPeople(),40);
    }
    @Test
    public void getSinglePerson() throws Exception{
        response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/person/612ba20357744c30dc7e6fe7");

        String body = EntityUtils.toString(response.getEntity());

     GetSinglePerson getSinglePerson = jsonStringToObject(body,GetSinglePerson.class);


    }

    @Test
    public void postPerson() throws Exception {
        postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);


        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_CREATED);
        Assert.assertEquals(postNewPersonResponse.getCode(), "P201");
        Assert.assertEquals(postNewPersonResponse.getMessage(), "Person succesfully inserted");
        Assert.assertEquals(postNewPersonResponse.getPersonData().getName(), "Derick");

    }

    @Test
    public void postPersonTestWithoutSurname() throws Exception {
        postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
        postNewPersonRequest.setSurname(null);
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);

        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_BAD_REQUEST);
        Assert.assertEquals(postNewPersonResponse.getCode(), "P400");
        Assert.assertEquals(postNewPersonResponse.getMessage(), "Person's surname cannot be empty");

    }

    @Test
    public void deletePersonTest() throws Exception {
        HttpResponse postResopnse = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person",
                objectToJsonString(postNewPersonPayload.createNewPersonPayload()));

        String postResponseBodyAsString = EntityUtils.toString(postResopnse.getEntity());
        PostNewPersonResponse postNewPersonResponse = jsonStringToObject(postResponseBodyAsString, PostNewPersonResponse.class);

        String createdPersonId = postNewPersonResponse.getPersonData().getId();

        response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/" + createdPersonId);

        String body = EntityUtils.toString(response.getEntity());
    }


        @Test
    public void updateLocation() throws Exception {
        PutNewLocationRequest putNewLocationRequest = new PutNewLocationRequest();
        PutNewLocationPayload putNewLocationPayload = new PutNewLocationPayload();

        putNewLocationRequest = putNewLocationPayload.createNewLocationPayload();

        String newLocationPayloadAsString = objectToJsonString(putNewLocationRequest);

        response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/613f3468efc41e00046091c4", newLocationPayloadAsString);

        PutNewLocationResponse putNewLocationResponse;

        String body = EntityUtils.toString(response.getEntity());

        putNewLocationResponse = jsonStringToObject(body, PutNewLocationResponse.class);


        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_OK);
        Assert.assertEquals(putNewLocationResponse.getCode(), "P200");
        Assert.assertEquals(putNewLocationResponse.getMessage(), "Person's location succesfully updated !");
        Assert.assertEquals(putNewLocationResponse.getPerson().getLocation(),"Skopje");
    }
}

