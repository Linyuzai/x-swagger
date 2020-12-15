package com.github.linyuzai.xswagger.handler;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.JsonPrimitive;

public class PathParameterDescriptionHandler extends AbstractSwaggerHandler {

    public PathParameterDescriptionHandler() {
        super("paths", "*", "*", "parameters", "*", "description");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        Object val = node.getValue();
        if (val instanceof JsonPrimitive) {
            SwaggerPathParameter pathParameter = getPathParameter(document, node);
            pathParameter.setDescription(((JsonPrimitive) val).getAsString());
        }
        if (val instanceof TextNode) {
            SwaggerPathParameter pathParameter = getPathParameter(document, node);
            pathParameter.setDescription(((TextNode) val).asText());
        }
    }
}
