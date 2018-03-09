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
    
    public CreateResponse(boolean success, List<String> messages) {
        super(success, messages);
    }
    
}
