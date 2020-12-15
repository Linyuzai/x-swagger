package com.github.linyuzai.xswagger.handler;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.JsonPrimitive;

public class PathDescriptionHandler extends AbstractSwaggerHandler {

    public PathDescriptionHandler() {
        super("paths", "*", "*", "description");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        Object val = node.getValue();
        if (val instanceof JsonPrimitive) {
            SwaggerPath path = getPath(document, node);
            path.setDescription(((JsonPrimitive) val).getAsString());
        }
        if (val instanceof TextNode) {
            SwaggerPath path = getPath(document, node);
            path.setDescription(((TextNode) val).asText());
        }
    }
}
