package com.github.linyuzai.xswagger.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.node.AbstractSwaggerNode;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class DefinitionsRefHandler extends AbstractSwaggerHandler {

    public DefinitionsRefHandler() {
        super("$ref");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        Object val = node.getValue();
        if (val instanceof JsonPrimitive) {
            SwaggerNode def = document.getDefinition(((JsonPrimitive) val).getAsString());
            ((JsonObject) node.getParent().getValue()).add("$ref", (JsonElement) def.getValue());
        }
        if (val instanceof TextNode) {
            SwaggerNode def = document.getDefinition(((TextNode) val).asText());
            ((ObjectNode) node.getParent().getValue()).set("$ref", (JsonNode) def.getValue());
        }
    }
}
