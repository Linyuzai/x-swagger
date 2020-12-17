package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsParametersInHandler extends AbstractSwaggerHandler {

    public PathsParametersInHandler() {
        super("paths", "*", "*", "parameters", "*", "in");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerPathParameter pathParameter = getPathParameter(document, node);
        pathParameter.setIn(node.stringValue());
    }
}
