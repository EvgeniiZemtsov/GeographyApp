package com.eugeneze.servlets;

import com.eugeneze.converters.CountryConverter;
import com.eugeneze.dao.CountryRepository;
import com.eugeneze.dao.DBPool;
import com.eugeneze.dao.specifications.CountryFindByIdSpecification;
import com.eugeneze.dao.specifications.CountryFindByNameSpecification;
import com.eugeneze.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    private CountryRepository repository = context.getBean("countryRepository", CountryRepository.class);
    private CountryConverter converter = context.getBean("countryConverter", CountryConverter.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Pattern pattern = Pattern.compile("localhost:8080/countries/\\d+");
        Matcher matcher = pattern.matcher(request.getRequestURL());

        PrintWriter printWriter = response.getWriter();
        List<Country> result;

        if (matcher.find()) {
            String[] urlParts = request.getRequestURL().toString().split("/");
            result = repository.query(new CountryFindByIdSpecification(Integer.parseInt(urlParts[4])));
        } else {
            result = repository.query(new CountryFindByNameSpecification(request.getParameter("name")));
        }

        try {
            for (Country country : result) {
                String countryJson = converter.convertToJson(country);
                printWriter.append(countryJson);
                printWriter.append("\n");
            }
        } catch (NullPointerException e) {
            printWriter.append("The country " + request.getParameter("name") + " not founded. We are sorry");
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
            requestBody.append("\n");
        }

        Country country = converter.convertJsonToObject(requestBody.toString());
        repository.create(country);
        reader.close();
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        while (reader.ready()) {
            requestBody.append(reader.readLine());
            requestBody.append("\n");
        }
        repository.update(converter.convertJsonToObject(requestBody.toString()));
        reader.close();

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] urlParts = request.getRequestURL().toString().split("/");
        repository.delete(Integer.parseInt(urlParts[4]));
    }
}
