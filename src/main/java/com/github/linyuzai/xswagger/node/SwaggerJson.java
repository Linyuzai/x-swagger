package com.github.linyuzai.xswagger.node;

import java.util.Map;

public interface SwaggerJson {

    String toJson();

    Map<String, Object> toResponseMap();

    String toResponseJson();

    String stringValue();

    boolean booleanValue();
}
