package com.github.linyuzai.xswagger.node;

import java.util.List;

public interface SwaggerNode {

    List<String> getLocation();

    String getKey();

    Object getValue();

    String toJson();

    String stringValue();

    boolean booleanValue();

    SwaggerNode getParent();

    List<? extends SwaggerNode> getChildren();
}
