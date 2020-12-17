package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsParametersTypeHandler extends AbstractSwaggerHandler {

    public PathsParametersTypeHandler() {
        super("paths", "*", "*", "parameters", "*", "type");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerPathParameter pathParameter = getPathParameter(document, node);
        pathParameter.setType(node.stringValue());
    }
}
