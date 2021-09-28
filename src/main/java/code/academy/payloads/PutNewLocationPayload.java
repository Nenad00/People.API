package code.academy.payloads;

import code.academy.model.requests.PutNewLocationRequest;

public class PutNewLocationPayload {
    public PutNewLocationRequest createNewLocationPayload() {
        return PutNewLocationRequest.builder()
                .location("Skopje")
                .build();
    }
}