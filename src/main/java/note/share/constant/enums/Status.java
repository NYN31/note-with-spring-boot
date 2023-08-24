package note.share.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String name;

    Status(String name){
        this.name = name;
    }

    @JsonCreator
    public static Status fromName(String name){
        if(Objects.isNull(name) || StringUtils.isBlank(name)){
            return null;
        }
        switch (StringUtils.lowerCase(name)){
            case "active":
                return ACTIVE;
            case "inactive":
                return INACTIVE;
            default:
                return null;
        }
    }

    @JsonValue
    public String getName(){
        return name;
    }
}
