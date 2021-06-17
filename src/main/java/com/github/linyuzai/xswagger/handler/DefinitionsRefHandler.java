package com.github.linyuzai.xswagger.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

public class DefinitionsRefHandler extends AbstractSwaggerHandler {

    public DefinitionsRefHandler() {
        super("$ref");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        Object val = node.getValue();
        if (val instanceof JsonPrimitive) {
            List<String> location = node.getLocation();
            String strVal = node.stringValue();
            JsonElement je;
            if (isSelf(location, strVal)) {
                je = JsonNull.INSTANCE;
            } else {
                SwaggerNode def = document.getDefinition(strVal);
                if (def == null) {
                    je = new JsonPrimitive(strVal.replace("#/definitions/", ""));
                } else {
                    je = (JsonElement) def.getValue();
                }
            }
            ((JsonObject) node.getParent().getValue()).add("$ref", je);
        }
        if (val instanceof TextNode) {
            List<String> location = node.getLocation();
            String strVal = node.stringValue();
            JsonNode jn;
            if (isSelf(location, strVal)) {
                jn = NullNode.getInstance();
            } else {
                SwaggerNode def = document.getDefinition(strVal);
                if (def == null) {
                    jn = new TextNode(strVal.replace("#/definitions/", ""));
                } else {
                    jn = (JsonNode) def.getValue();
                }
            }
            ((ObjectNode) node.getParent().getValue()).set("$ref", jn);
        }
    }

    private boolean isSelf(List<String> location, String value) {
        return location.size() > 2 && "definitions".equals(location.get(0)) && value.equals("#/definitions/" + location.get(1));
    }
}
