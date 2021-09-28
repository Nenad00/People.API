package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import code.academy.model.requests.PostNewPersonRequest;
import code.academy.model.response.PostNewPersonResponse;
import code.academy.payloads.PostNewPersonPayload;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static code.academy.utils.ConversionUtils.jsonStringToObject;
import static code.academy.utils.ConversionUtils.objectToJsonString;
import static org.apache.http.HttpStatus.*;

@Test
public class PostSaveNewPersonTest {
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    PostNewPersonResponse postNewPersonResponse = new PostNewPersonResponse();
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    String personOneId;
    String personTwoId;
    String personThreeID;

    public PostSaveNewPersonTest() throws Exception {
    }

    @Test
    public void PostPerson() throws Exception {

        postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);


        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_CREATED);
        Assert.assertEquals(postNewPersonResponse.getCode(), "P201");
        Assert.assertEquals(postNewPersonResponse.getMessage(), "Person succesfully inserted");

        Assert.assertEquals(postNewPersonResponse.getPersonData().getName(), postNewPersonRequest.getName());
        Assert.assertEquals(postNewPersonResponse.getPersonData().getSurname(), postNewPersonRequest.getSurname());
        Assert.assertEquals(postNewPersonResponse.getPersonData().getAge(), postNewPersonRequest.getAge());
        Assert.assertEquals(postNewPersonResponse.getPersonData().getIsEmployed(), postNewPersonRequest.getIsEmployed());
        Assert.assertEquals(postNewPersonResponse.getPersonData().getLocation(), postNewPersonRequest.getLocation());

        personOneId = postNewPersonResponse.getPersonData().getId();
    }



    @Test
    public void PostPersonWithoutAge() throws Exception {
        postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
        postNewPersonRequest.setAge(null);
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);

        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertNull(postNewPersonResponse.getPersonData().getAge());

        personTwoId = postNewPersonResponse.getPersonData().getId();

    }
   @Test
   public void PostPersonWithoutLocation() throws Exception{
       postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
       postNewPersonRequest.setLocation(null);
       String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

       response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);

       String body = EntityUtils.toString(response.getEntity());

       postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

       Assert.assertNull(postNewPersonResponse.getPersonData().getLocation());

       personThreeID = postNewPersonResponse.getPersonData().getId();

   }
    @Test
    public void PostPersonWithoutSurname() throws Exception {
        postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
        postNewPersonRequest.setSurname(null);
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);

        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_BAD_REQUEST);
        Assert.assertEquals(postNewPersonResponse.getCode(),"P400");
        Assert.assertEquals(postNewPersonResponse.getMessage(),"Person's surname cannot be empty");
    }
    @Test
    public void PostPersonWithoutName() throws Exception{
        postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
        postNewPersonRequest.setName(null);
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);

        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_BAD_REQUEST);
        Assert.assertEquals(postNewPersonResponse.getCode(),"P400");
        Assert.assertEquals(postNewPersonResponse.getMessage(),"Person's name cannot be empty");
    }
    @Test
   public void PostPersonWithoutIsEmploye() throws Exception{
        postNewPersonRequest = postNewPersonPayload.createNewPersonPayload();
        postNewPersonRequest.setIsEmployed(null);
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);

        String body = EntityUtils.toString(response.getEntity());

        postNewPersonResponse = jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_BAD_REQUEST);
        Assert.assertEquals(postNewPersonResponse.getCode(),"P400");
        Assert.assertEquals(postNewPersonResponse.getMessage(),"Person must provide if he is employed or not");
    }
  @Test
  public void PostPersonEmployeAsString() throws Exception {
      JSONObject jsonObject = new JSONObject();
      jsonObject = postNewPersonPayload.createNewPersonPayloadEmployeAsString();


      response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", jsonObject.toString());

      String body = EntityUtils.toString(response.getEntity());

      JSONObject bodyAsObject = new JSONObject(body);
      String messageAsString = bodyAsObject.get("message").toString();

      Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_INTERNAL_SERVER_ERROR);
      String expectedMessage = "Person validation failed:";
      Boolean passes = messageAsString.contains(expectedMessage);
      Assert.assertTrue(passes);
  }
 /* this test cannot be provide
   @Test
    public void PostPersonNameSurnameAsInteger() throws Exception{
    JSONObject jsonObject = new JSONObject();
    jsonObject = postNewPersonPayload.PostPersonNameSurnameAsInteger();

    String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

    response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", newPersonPayloadAsString);

    String body = EntityUtils.toString(response.getEntity());


    Assert.assertEquals(response.getStatusLine().getStatusCode(),SC_BAD_REQUEST);
    Assert.assertEquals(postNewPersonResponse.getCode(),"P400");
    Assert.assertEquals(postNewPersonResponse.getMessage(),);
}*/
@AfterClass
    public void afterClass() throws Exception{
    peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/" + personOneId);
    peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/" + personTwoId) ;
    peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/" + personThreeID);
}

}