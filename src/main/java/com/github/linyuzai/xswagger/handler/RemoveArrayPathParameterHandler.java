package com.github.linyuzai.xswagger.handler;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.JsonPrimitive;

public class RemoveArrayPathParameterHandler extends AbstractSwaggerHandler {

    public RemoveArrayPathParameterHandler() {
        super("paths", "*", "*", "parameters", "*", "name");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        Object val = node.getValue();
        if (val instanceof JsonPrimitive) {
            if (((JsonPrimitive) val).getAsString().contains("[0].")) {
                removePathParameter(document, node);
            }
        }
        if (val instanceof TextNode) {
            if (((TextNode) val).asText().contains("[0].")) {
                removePathParameter(document, node);
            }
        }
    }
}
