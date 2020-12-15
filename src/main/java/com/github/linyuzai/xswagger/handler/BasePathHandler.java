package com.github.linyuzai.xswagger.handler;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.JsonPrimitive;

public class BasePathHandler extends AbstractSwaggerHandler {

    public BasePathHandler() {
        super("basePath");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        Object val = node.getValue();
        if (val instanceof JsonPrimitive) {
            document.setBasePath(((JsonPrimitive) val).getAsString());
        }
        if (val instanceof TextNode) {
            document.setBasePath(((TextNode) val).asText());
        }
    }
}
