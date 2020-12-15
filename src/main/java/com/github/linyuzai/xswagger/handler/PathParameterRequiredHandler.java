package com.github.linyuzai.xswagger.handler;

import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.JsonPrimitive;

public class PathParameterRequiredHandler extends AbstractSwaggerHandler {

    public PathParameterRequiredHandler() {
        super("paths", "*", "*", "parameters", "*", "required");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        Object val = node.getValue();
        if (val instanceof JsonPrimitive) {
            SwaggerPathParameter pathParameter = getPathParameter(document, node);
            pathParameter.setRequired(((JsonPrimitive) val).getAsBoolean());
        }
        if (val instanceof BooleanNode) {
            SwaggerPathParameter pathParameter = getPathParameter(document, node);
            pathParameter.setRequired(((BooleanNode) val).asBoolean());
        }
    }
}
