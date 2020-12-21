package com.github.linyuzai.xswagger.node;

import java.util.List;

public interface SwaggerNode extends SwaggerJson {

    List<String> getLocation();

    String getKey();

    Object getValue();

    SwaggerNode getParent();

    List<? extends SwaggerNode> getChildren();
}
