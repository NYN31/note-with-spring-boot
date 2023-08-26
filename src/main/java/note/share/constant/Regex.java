package note.share.constant;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern MOBILE_NUMBER_REGEX = Pattern.compile("(^(\\+88)?(01)[2-9][0-9]{8,8}$)");
    public static final Pattern EMAIL_REGEX = Pattern.compile("(^[a-zA-Z0-9.\\-_]+@{1,1}[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)",
            Pattern.CASE_INSENSITIVE);
}
