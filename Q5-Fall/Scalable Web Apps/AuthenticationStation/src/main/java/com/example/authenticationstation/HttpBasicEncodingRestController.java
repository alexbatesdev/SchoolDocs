package com.example.authenticationstation;

import org.springframework.web.bind.annotation.*;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/httpbasic")
public class HttpBasicEncodingRestController {

//    @RequestMapping(path="/enc/{username}/{password}", method=RequestMethod.GET)
    @GetMapping("/enc/{username}/{password}")
    public String encode(@PathVariable String username, @PathVariable String password) {
        String strUp = username + ":" + password;
        strUp = Base64.encodeBase64String(strUp.getBytes());
        strUp = "Basic " + strUp;
        return strUp;
    }

    @GetMapping("/dec/{encodedHeaderValue}")
    public Map<String, String> decode(@PathVariable String encodedHeaderValue) {
        String strUp = encodedHeaderValue;
        strUp = strUp.replace("Basic ", "");

        strUp = new String(Base64.decodeBase64(strUp));
        String[] arrUp = strUp.split(":", 2);
        Map<String, String> map = new HashMap<>();
        map.put("username", arrUp[0]);
        map.put("password", arrUp[1]);
        return map;
    }
}
