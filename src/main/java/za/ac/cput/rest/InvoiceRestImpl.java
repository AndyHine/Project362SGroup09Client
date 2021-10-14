package za.ac.cput.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class InvoiceRestImpl {

    //Attributes
        private static RestTemplate restTemplate = new RestTemplate(); //Use rest template to send http requests
        private final static String BASE_URL = "http://localhost:8080/invoice/saveInvoice"; //Url endpoint to get login details

    public static boolean saveInvoice(String studentNumber, String eventCode, String totalPrice)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE); //Set the accept header and content type

        UriComponentsBuilder queryParams = UriComponentsBuilder.fromHttpUrl(BASE_URL) //Add the query params needed for this request
                .queryParam("studentNumber", studentNumber)
                .queryParam("eventCode", eventCode)
                .queryParam("totalPrice", totalPrice);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<Boolean> response = restTemplate.exchange(queryParams.build().toUriString(), HttpMethod.POST, entity, boolean.class); //Create the request

        if(response.getBody()) //Return true/false based on the response received
        {
            return true;
        }
        else{
            return false;
        }
    }
}
