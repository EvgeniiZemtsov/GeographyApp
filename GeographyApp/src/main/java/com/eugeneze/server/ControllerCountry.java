package com.eugeneze.server;

import com.eugeneze.converters.CountryConverter;
import com.eugeneze.dao.CountryRepository;
import com.eugeneze.dao.DBPool;
import com.eugeneze.dao.FindByIdSpecification;
import com.eugeneze.dao.FindByNameSpecification;
import com.eugeneze.models.Country;

import java.util.List;

public class ControllerCountry implements Controller {
    final String user = "postgres";
    final String password = "lamborgini235";
    final String url = "jdbc:postgresql://localhost:5432/geography";

    CountryRepository repository = new CountryRepository(new DBPool(user, url, password));
    CountryConverter converter = new CountryConverter();

    @Override
    public HttpResponse put(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        Country country = converter.convertJsonToObject(request.getBody());
        try {
        repository.update(country);
        response.setStatusCode(201);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setStatus("Internal Server Error");
        }
        return response;
    }

    @Override
    public HttpResponse post(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        Country country = converter.convertJsonToObject(request.getBody());
        try {
            repository.create(country);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setStatus("Internal Server Error");
        }
        return response;
    }

    @Override
    public HttpResponse get(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        String[] urlParts = request.getUrl().split("/");
        List<Country> countries;

        try {
            int id = Integer.parseInt(urlParts[2]);
            countries = repository.query(new FindByIdSpecification(id));
        } catch (NumberFormatException e) {
            String countryName = urlParts[2];
            countries = repository.query(new FindByNameSpecification(countryName));
        }

        if (!countries.isEmpty()) {
            response.addHeader("Content-Type", "application/json; charset=utf-8");
            response.setBody(converter.convertToJson(countries.get(0)));
        } else {
            response.setStatusCode(404);
            response.setStatus("NOT FOUNDED");
        }

        return response;
    }

    @Override
    public HttpResponse delete(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        String[] urlParts = request.getUrl().split("/");

        try {
            repository.delete(urlParts[2]);
        } catch (Exception e) {
            response.setStatusCode(404);
            response.setStatus("NOT FOUNDED");
        }

        return response;
    }
}
