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
    private T Record;
    
    public T getRecord() {
        return Record;
    }

    public void setRecord(T Record) {
        this.Record = Record;
    }
    
}
