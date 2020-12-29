package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathResponse;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsResponsesRefArrayHandler extends AbstractSwaggerHandler {

    public PathsResponsesRefArrayHandler() {
        super("paths", "*", "*", "responses", "*", "*", "*", "$ref");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerNode def = document.getDefinition(node.stringValue());
        getPath(document, node).setResponse(new SwaggerPathResponse(format(def.toResponseJson())));
    }
}
