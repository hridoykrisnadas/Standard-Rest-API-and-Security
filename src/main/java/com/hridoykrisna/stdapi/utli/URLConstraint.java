package com.hridoykrisna.stdapi.utli;

public class URLConstraint {
    public static final String VERSION = "/v1";
    public static final String API = "/api";

    private URLConstraint() {
    }

    public static class ProductManagement {
        public static final String ROOT = API + VERSION+"/product";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
        public static final String GET_DETAILS = "/details/{id}";
        public static final String GET_ALL = "/all-products";
    }

    public static class AuthManagement {
        public static final String ROOT = API +"/auth";
        public static final String CREATE = "/login";
    }
}
