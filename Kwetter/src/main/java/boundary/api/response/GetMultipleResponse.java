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
public class GetMultipleResponse<T> extends ResponseBase {
    private List<T> Records;

    public GetMultipleResponse(boolean success, List<String> messages) {
        super(success, messages);
    }

    public GetMultipleResponse(boolean success) {
        super(success);
    }

    public List<T> getRecords() {
        return Records;
    }

    public void setRecords(List<T> Records) {
        this.Records = Records;
    }
    
}
