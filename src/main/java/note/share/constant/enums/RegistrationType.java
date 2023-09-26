package note.share.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public enum RegistrationType {
    GOOGLE("GOOGLE"),
    BASIC("BASIC");

    private final String name;
    RegistrationType(String name){
        this.name = name;
    }

    @JsonCreator
    public static RegistrationType form(String name){
        if(Objects.isNull(name) || StringUtils.isBlank(name)){
            return null;
        }
        switch(StringUtils.lowerCase(name)){
            case "google":
                return GOOGLE;
            case "basic":
                return BASIC;
            default:
                return null;
        }
    }

    @JsonValue
    public String getName(){
        return name;
    }
}

