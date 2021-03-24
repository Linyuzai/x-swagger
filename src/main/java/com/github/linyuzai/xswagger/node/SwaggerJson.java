package com.github.linyuzai.xswagger.node;

import java.util.Map;

public interface SwaggerJson {

    String toJson();

    Map<String, Object> toResponseMap();

    String toResponseJson();

    String toResponseJson(boolean array);

    String stringValue();

    boolean booleanValue();
}
