package server;

import error_codes.ErrorCode;
import object_protocol.Response;
import object_protocol.RequestType;
import object_protocol.Request;

import java.util.HashMap;
import java.util.List;

/**
 * Created by riana-r on 17/11/14.
 */
public class ObjectProtocol {
    private Data data;

    public ObjectProtocol(Data data){
        this.data = data;
    }
    public Response getReponse (Request request){
        RequestType type = request.getRequestType();
        String surnom= request.getSurnom();
        String nom = request.getNom();
        Response response = new Response(new HashMap<String, List<String>>(), ErrorCode.NONE);
        switch (type){
            case GET:
                break;
            case ADD:
                response.setErrorCode(data.add(nom, surnom));
                break;
            case REMOVE:
                break;
            case EDIT:
                break;
            case QUIT:
                response.setErrorCode(ErrorCode.DECONNECTED_FROM_CLIENT);
                break;
            case LIST:
                response.setContent(data.list());
                break;
        }
        return response;
    }
}
