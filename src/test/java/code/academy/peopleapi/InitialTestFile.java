package code.academy.peopleapi;


import code.academy.client.PeopleApiClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class InitialTestFile {
PeopleApiClient peopleApiClient = new PeopleApiClient();
HttpResponse response;

    @Test
        public void welcomeMessagePeopleApiTest() throws Exception {
            String expectedMessage = "Welcome to People API";

            response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/");

            String body = EntityUtils.toString(response.getEntity());
            JSONObject bodyAsObject = new JSONObject(body);

            String messageAsString = bodyAsObject.get("message").toString();

            Assert.assertEquals(messageAsString, expectedMessage);
        }
    @Test
    public void deletePerson() throws Exception {
         String expectedMessage = "Person with id=61433b8846b462000438c50d has been succesfully deleted";

         response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/61433b8846b462000438c50d");

         String body = EntityUtils.toString(response.getEntity());
         JSONObject bodyAsObject = new JSONObject(body);

         String messageAsString = bodyAsObject.get("message").toString();

         Assert.assertEquals(messageAsString, expectedMessage);
     }
    @Test
    public void postPerson() throws Exception{
        String expectedMessage = "Person succesfully inserted";

        JSONObject payloadAsObject = new JSONObject();
       payloadAsObject.put("name", "Derick");
       payloadAsObject.put("surname", "Rose");
       payloadAsObject.put("age", 47);
       payloadAsObject.put("isEmployed", true);
       payloadAsObject.put("location", "Ohrid");

       response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person",payloadAsObject);

       String body = EntityUtils.toString(response.getEntity());
       JSONObject bodyAsObject = new JSONObject(body);
       String messageAsString = bodyAsObject.get("message").toString();

       Assert.assertEquals(messageAsString,expectedMessage);
   }
    @Test
    public void updateLocation() throws Exception{
        String expectedMessage = "Person's location succesfully updated !";

        JSONObject payloadAsObject = new JSONObject();
        payloadAsObject.put("location", "Skopje");

        response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/613f3468efc41e00046091c4",payloadAsObject);

        String body = EntityUtils.toString(response.getEntity());

        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();

        Assert.assertEquals(messageAsString,expectedMessage);
    }
    @Test
    public void postPersonTestWithoutSurname () throws Exception{
        String expectedMessage = "Person's surname cannot be empty";
        JSONObject payloadAsObject = new JSONObject();
        payloadAsObject.put("name", "Pero");
        payloadAsObject.put("age", 56);
        payloadAsObject.put("isEmployed", true);
        payloadAsObject.put("location", "Skopje");

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person", payloadAsObject);
        String body = EntityUtils.toString(response.getEntity());

        JSONObject bodyAsObject = new JSONObject(body);
        String massageAsString = bodyAsObject.get("message").toString();

        Assert.assertEquals(massageAsString,expectedMessage);
    }

    }


