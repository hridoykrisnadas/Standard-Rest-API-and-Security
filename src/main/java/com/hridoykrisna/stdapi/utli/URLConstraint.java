package com.hridoykrisna.stdapi.utli;

public class URLConstraint {
    public static final String VERSION = "/v1";
    public static final String API = "/api";

    private URLConstraint() {
    }

    public static class ProductManagement {
        public static final String ROOT = API+VERSION;
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete";
        public static final String GET_DETAILS = "/details";
        public static final String GET_ALL = "/all-products";
    }
}
