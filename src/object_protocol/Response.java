package object_protocol;

import error_codes.ErrorCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by riana-r on 17/11/14.
 */
public class Response implements Serializable {
    ErrorCode errorCode;
    Map<String,List<String>> content;


    public Response(Map<String, List<String>> content, ErrorCode errorCode) {
        this.content = content;
        this.errorCode = errorCode;
    }


    public Map<String,List<String>> getContent() {
        return content;
    }

    public void setContent(Map<String,List<String>> content) {
        this.content = content;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
