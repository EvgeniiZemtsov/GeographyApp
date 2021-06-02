package com.eugeneze.servlets;

import com.eugeneze.converters.CountryConverter;
import com.eugeneze.dao.CountryRepository;
import com.eugeneze.dao.DBPool;
import com.eugeneze.dao.FindByIdSpecification;
import com.eugeneze.dao.FindByNameSpecification;
import com.eugeneze.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/countries/*", "/countries"})
public class CountryServlet extends HttpServlet {

    private final String user = "postgres";
    private final String password = "lamborgini235";
    private final String url = "jdbc:postgresql://localhost:5432/geography";

    CountryRepository repository = new CountryRepository(new DBPool(user, url, password));
    CountryConverter converter = new CountryConverter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Pattern pattern = Pattern.compile("localhost:8080/countries/\\d+");
        Matcher matcher = pattern.matcher(request.getRequestURL());

        PrintWriter printWriter = response.getWriter();
        List<Country> result;

        if (matcher.find()) {
            String[] urlParts = request.getRequestURL().toString().split("/");
            result = repository.query(new FindByIdSpecification(Integer.parseInt(urlParts[4])));
        } else {
            result = repository.query(new FindByNameSpecification(request.getParameter("name")));
        }

        for (Country country : result) {
            String countryJson = converter.convertToJson(country);
            printWriter.append(countryJson);
            printWriter.append("\n");
        }

        response.setHeader("Content-Type", "application/json");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        while (reader.ready()) {
            requestBody.append(reader.readLine());
        }
        repository.create(converter.convertJsonToObject(requestBody.toString()));
        reader.close();
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        while (reader.ready()) {
            requestBody.append(reader.readLine());
        }
        repository.update(converter.convertJsonToObject(requestBody.toString()));
        reader.close();

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] urlParts = request.getRequestURL().toString().split("/");
        repository.delete(urlParts[4]);
    }
}
