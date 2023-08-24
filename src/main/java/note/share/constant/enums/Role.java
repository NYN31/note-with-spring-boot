package note.share.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private String name;
    Role(String name){
        this.name = name;
    }

    @JsonCreator
    public static Role fromName(String name){
        if(Objects.isNull(name) || StringUtils.isBlank(name)){
            return null;
        }
        switch (StringUtils.lowerCase(name)){
            case "user":
                return USER;
            case "admin":
                return ADMIN;
            default:
                return null;
        }
    }

    @JsonValue
    public String getName(){
        return name;
    }
}
