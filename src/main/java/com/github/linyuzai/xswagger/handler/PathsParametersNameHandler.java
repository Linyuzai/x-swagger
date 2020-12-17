package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsParametersNameHandler extends AbstractSwaggerHandler {

    public PathsParametersNameHandler() {
        super("paths", "*", "*", "parameters", "*", "name");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerPathParameter pathParameter = getPathParameter(document, node);
        pathParameter.setName(node.stringValue());
    }
}
