package Deserialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RegistrationFields {
    public int id;
    public String name;
    public int year;
    public String color;
    public String pantone_value;
}
