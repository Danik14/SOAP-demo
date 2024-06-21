package com.example.demo;

import com.github.danik14.soap_demo.GetCountryRequest;
import com.github.danik14.soap_demo.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://github.com/Danik14/soap-demo";

    private final CountryService countryService;

    @Autowired
    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryService.findCountry(request.getName()));

        return response;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryRequest")
//    @ResponsePayload
//    public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
//        Country country = new Country();
//        country.setName(request.getName());
//        country.setPopulation(request.getPopulation());
//        country.setCapital(request.getCapital());
//        country.setCurrency(request.getCurrency());
//        countryRepository.save(country);
//        AddCountryResponse response = new AddCountryResponse();
//        response.setStatus("Country added successfully");
//        return response;
//    }
}
