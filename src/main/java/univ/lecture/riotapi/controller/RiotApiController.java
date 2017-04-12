package univ.lecture.riotapi.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;
import univ.lecture.riotapi.model.CalcInformation;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1")
@Log4j
public class RiotApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${riot.api.endpoint}")
    private String riotApiEndpoint;

    @Value("${riot.api.key}")
    private String riotApiKey;
    
    @Value("${endpoint}")
    private String Endpoint;

    @RequestMapping(value = "/calc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CalcInformation queryCalcInfo(@RequestBody String calcNeed) throws UnsupportedEncodingException {
        final String url = Endpoint;
        Calculator calc = new Calculator();
        
        int teamID = 3;
   
        long now = System.currentTimeMillis();
        double result = calc.calculate(calcNeed);
 
 //       String response = restTemplate.getForObject(url, String.class);
        
        Map<String, Object> parsedMap = new HashMap<>();
        
 //       Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(response);
//
//        parsedMap.forEach((key, value) -> log.info(String.format("key [%s] type [%s] value [%s]", key, value.getClass(), value)));
//
//        Map<String, Object> calcDetail = (Map<String, Object>) parsedMap.values().toArray()[0];
//        int teamIdinfo = 3;
//        String inTime   = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
//        long now = Long.parseLong(inTime);
//        double result = 0;
        
        parsedMap.put("teamId", teamID);
        parsedMap.put("now", now);
        parsedMap.put("result", result);
        
//        String msg = restTemplate.postForObject(url, parsedMap, String.class);
 
        
        CalcInformation calcinfo = new CalcInformation(teamID, now, result, restTemplate.postForObject(url, parsedMap, String.class));

        return calcinfo;
    }
}
