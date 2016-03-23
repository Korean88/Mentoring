package com.epam.mentoring.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Andrey on 23.03.2016.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface RandomNumberGenerator {

    @WebMethod
    int generate(int lowerBound, int upperBound);
}
