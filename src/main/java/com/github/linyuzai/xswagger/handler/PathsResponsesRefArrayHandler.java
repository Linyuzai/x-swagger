package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathResponse;
import com.github.linyuzai.xswagger.node.SwaggerNode;

import java.util.List;

public class PathsResponsesRefArrayHandler extends AbstractSwaggerHandler {

    public PathsResponsesRefArrayHandler() {
        super("paths", "*", "*", "responses", "*", "*", "*", "$ref");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        String stringValue = node.stringValue();
        SwaggerNode def = document.getDefinition(stringValue);
        SwaggerNode array = node.getParent().getParent().getChild("type");
        if (def == null) {
            getPath(document, node)
                    .setResponse(new SwaggerPathResponse(stringValue.replace("#/definitions/", "")));
        } else {
            SwaggerNode title = def.getChild("title");
            boolean isArray = "array".equals(array.stringValue());
            if ("JSONObject".equals(title.stringValue())) {
                getPath(document, node)
                        .setResponse(new SwaggerPathResponse("JSONObject[]"));
            } else {
                getPath(document, node)
                        .setResponse(new SwaggerPathResponse(format(def.toResponseJson(isArray))));
            }
        }
    }
}
