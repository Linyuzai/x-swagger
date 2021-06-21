package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsParametersObjectRemoveHandler extends AbstractSwaggerHandler {

    public PathsParametersObjectRemoveHandler() {
        super("paths", "*", "*", "parameters", "*", "name");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        String sv = node.stringValue();
        if (sv != null && sv.contains(".")) {
            removePathParameter(document, node);
        }
    }
}
