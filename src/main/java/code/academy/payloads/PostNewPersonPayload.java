package code.academy.payloads;

import code.academy.model.requests.PostNewPersonRequest;
import org.json.JSONObject;

public class PostNewPersonPayload {


    public PostNewPersonRequest createNewPersonPayload() {
        return PostNewPersonRequest.builder()
                .name("Derric")
                .surname("Rose")
                .age(32)
                .isEmployed(true)
                .location("Chicago")
                .build();
    }

    public JSONObject createNewPersonPayloadEmployeAsString(){
          JSONObject personObject = new JSONObject();
          personObject.put("name","Horhe");
          personObject.put("surname","papito");
          personObject.put("age",35);
          personObject.put("isEmployed","kako string");
          personObject.put("location","Ohrid");

        return personObject;
    }
  public JSONObject PostPersonNameSurnameAsInteger(){
      JSONObject personObject = new JSONObject();
      personObject.put("name",12);
      personObject.put("surname",13);
      personObject.put("age",35);
      personObject.put("isEmployed","kako string");
      personObject.put("location","Ohrid");

      return personObject;
  }

}