package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsParametersRequiredHandler extends AbstractSwaggerHandler {

    public PathsParametersRequiredHandler() {
        super("paths", "*", "*", "parameters", "*", "required");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerPathParameter pathParameter = getPathParameter(document, node);
        pathParameter.setRequired(node.booleanValue());
    }
}
