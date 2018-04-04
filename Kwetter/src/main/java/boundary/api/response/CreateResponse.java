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
public class CreateResponse<T> extends ResponseBase {
    private T Record;
    private List<T> Records;

    public List<T> getRecords() {
        return Records;
    }

    public void setRecords(List<T> Records) {
        this.Records = Records;
    }

    public T getRecord() {
        return Record;
    }

    public void setRecord(T Record) {
        this.Record = Record;
    }
    
}
