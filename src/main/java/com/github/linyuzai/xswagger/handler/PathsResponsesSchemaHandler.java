package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathResponse;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsResponsesSchemaHandler extends AbstractSwaggerHandler {

    public PathsResponsesSchemaHandler() {
        super("paths", "*", "*", "responses", "*", "schema", "type");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        if ("object".equals(node.stringValue())) {
            getPath(document, node).setResponse(new SwaggerPathResponse("object"));
        }
    }
}
