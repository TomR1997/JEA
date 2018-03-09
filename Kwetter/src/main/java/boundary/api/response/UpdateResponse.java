/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.api.response;

import java.util.List;

/**
 *
 * @author Tomt
 */
public class UpdateResponse<T> extends ResponseBase {
    public T Record;
    
    public UpdateResponse(boolean success, List<String> messages) {
        super(success, messages);
    }

    public UpdateResponse(boolean success) {
        super(success);
    }

    public T getRecord() {
        return Record;
    }

    public void setRecord(T Record) {
        this.Record = Record;
    }
    
}
