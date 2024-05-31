package com.mycompany.productservice.commons;

import com.mycompany.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate _restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate)
    {
        _restTemplate = restTemplate;
    }
    public UserDto validateToken(String token)
    {
        //Call the API methods

        ResponseEntity<UserDto> response = _restTemplate.postForEntity
                ("http://userservice-env.eba-mmj3nfm9.eu-north-1.elasticbeanstalk.com/users/validate/" + token,
                null,
                UserDto.class);

        //if it is null then token is invalid
        if(response.getBody() == null)
            return null;

        return response.getBody();
    }
}
