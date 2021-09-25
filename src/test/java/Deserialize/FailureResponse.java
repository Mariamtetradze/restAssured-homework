package Deserialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FailureResponse {
    String FaultId;
    String fault;
}
