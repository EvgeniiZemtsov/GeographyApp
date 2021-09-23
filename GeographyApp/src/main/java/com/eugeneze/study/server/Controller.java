package com.eugeneze.study.server;

public interface Controller {
    HttpResponse put(HttpRequest request);
    HttpResponse post(HttpRequest request);
    HttpResponse get(HttpRequest request);
    HttpResponse delete(HttpRequest request);
}
