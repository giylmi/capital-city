package ru.kpfu.itis.capitalcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.capitalcity.api.ApiResponse;
import ru.kpfu.itis.capitalcity.api.HttpResponse;
import ru.kpfu.itis.capitalcity.model.Credentials;
import ru.kpfu.itis.capitalcity.service.CredentialsService;
import ru.kpfu.itis.capitalcity.validator.CredentialsValidator;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
@RestController
public class AuthController {

    @Autowired
    private CredentialsValidator credentialsValidator;

    @Autowired
    private CredentialsService credentialsService;

    @RequestMapping(name = "/signup", method = RequestMethod.POST)
    public ApiResponse signup(@ModelAttribute Credentials credentials, BindingResult result){
        ApiResponse response = new ApiResponse();
        credentialsValidator.validate(credentials, result);
        if (result.hasErrors()) {
            response.setHttpResponse(HttpResponse.FAIL);
            result.getAllErrors().stream().forEach(e -> response.addError(e.toString()));
        }
        else {
            credentialsService.save(credentials);
            response.setHttpResponse(HttpResponse.SUCCESS);
            // todo: temporary for debug. credentials should not be transferred from server to client
            response.setResponseData(credentials);
        }
        return response;
    }
}
