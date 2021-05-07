package com.eugeneze.server;

public class Router {

    private Controller controller;
    private HttpRequest request;
    private HttpResponse response;

    public String handle(HttpRequest request) {
        this.request = request;
        chooseController();
        invokeMethod();
        return response.message();
    }

    private void chooseController() {
        String url = request.getUrl();
        if (url.contains("countries")) {
            controller = new ControllerCountry();
        } else if (url.contains("cities")) {
            controller = new ControllerCity();
        } else if (url.contains("currencies")) {
            controller = new ControllerCurrency();
        } else if (url.contains("languages")) {
            controller = new ControllerLanguage();
        } else if (url.contains("headsofstates")) {
            controller = new ControllerHeadOfState();
        }
    }

    private void invokeMethod() {
        HttpMethod method = request.getMethod();
        if (method == HttpMethod.GET) {
            response = controller.get(request);
        } else if (method == HttpMethod.POST) {
            response = controller.post(request);
        } else if (method == HttpMethod.PUT) {
            response = controller.put(request);
        } else if (method == HttpMethod.DELETE) {
            response = controller.delete(request);
        }
    }
}
