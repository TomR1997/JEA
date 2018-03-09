package boundary.api.response;

import java.util.List;

/**
 *
 * @author Tomt
 */
public class ResponseBase {
    private boolean success;
    private List<String> messages;

    public ResponseBase(boolean success, List<String> messages) {
        this.success = success;
        this.messages = messages;
    }

    public ResponseBase(boolean success) {
        this.success = success;
    }
    
    public void addMessage(String message){
        this.messages.add(message);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
}
