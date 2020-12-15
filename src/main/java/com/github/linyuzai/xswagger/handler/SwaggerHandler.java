package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public interface SwaggerHandler {

    boolean isLocated(SwaggerNode node);

    void handle(SwaggerDocument document, SwaggerNode node);
}
