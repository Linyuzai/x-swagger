package com.github.linyuzai.xswagger.document.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SwaggerPath {

    private String method;

    private String path;

    private String description;

    private Map<String, SwaggerPathParameter> parameterMap = new LinkedHashMap<>();

    private SwaggerPathResponse response;

    public SwaggerPath() {
    }

    public SwaggerPath(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SwaggerPathParameter getPathParameter(String index) {
        return parameterMap.computeIfAbsent(index, i -> new SwaggerPathParameter());
    }

    public void removePathParameter(String index) {
        parameterMap.remove(index);
    }

    public Collection<SwaggerPathParameter> getPathParameters() {
        return parameterMap.values();
    }

    public SwaggerPathResponse getResponse() {
        return response;
    }

    public void setResponse(SwaggerPathResponse response) {
        this.response = response;
    }
}
