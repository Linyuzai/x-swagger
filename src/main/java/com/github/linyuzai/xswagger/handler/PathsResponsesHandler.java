package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathResponse;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsResponsesHandler extends AbstractSwaggerHandler {

    public PathsResponsesHandler() {
        super("paths", "*", "*", "responses", "*", "*", "$ref");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        getPath(document, node).setResponse(new SwaggerPathResponse(node.toJson()));
    }
}
