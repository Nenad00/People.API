package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import code.academy.model.requests.PostNewPersonRequest;
import code.academy.model.response.PostNewPersonResponse;
import code.academy.payloads.PostNewPersonPayload;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static code.academy.utils.ConversionUtils.jsonStringToObject;
import static code.academy.utils.ConversionUtils.objectToJsonString;

public class DeletePersonFeatureTest {
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    String createdPersonId;

    @BeforeClass
    public void beforeClass() throws Exception {
        HttpResponse postResopnse = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person",
                objectToJsonString(postNewPersonPayload.createNewPersonPayload()));

        String postResponseBodyAsString = EntityUtils.toString(postResopnse.getEntity());
        PostNewPersonResponse postNewPersonResponse = jsonStringToObject(postResponseBodyAsString, PostNewPersonResponse.class);

         createdPersonId = postNewPersonResponse.getPersonData().getId();
    }

    @BeforeTest
    public void beforeTest(){

    }


    public DeletePersonFeatureTest() throws Exception {
        response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/" + createdPersonId);
        String body = EntityUtils.toString(response.getEntity());
    }

    @AfterTest
    public void afterTest(){

    }

    @AfterClass
    public void afterClass(){

    }

}
