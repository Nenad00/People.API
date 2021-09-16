package code.academy.peopleapi;


import code.academy.client.PeopleApiClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

@Test
public class InitialTestFile {
PeopleApiClient peopleApiClient = new PeopleApiClient();
HttpResponse response;

    @Test
    public void initialTest() throws Exception{
       response = peopleApiClient.getWelcomeRequest();
       String body = EntityUtils.toString(response.getEntity());

       response = peopleApiClient.getOnePerson();
       String onePreson = EntityUtils.toString(response.getEntity());

       response = peopleApiClient.getAllPeople();
       String allPeople = EntityUtils.toString(response.getEntity());

       response = peopleApiClient.deleteOnePerson();
       String deletePerson = EntityUtils.toString(response.getEntity());

       response = peopleApiClient.postNewPerson();
       String postPerson = EntityUtils.toString(response.getEntity());

       response = peopleApiClient.putNewPerson();
       String updateLocation = EntityUtils.toString(response.getEntity());

    }

}
