package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class BasePathHandler extends AbstractSwaggerHandler {

    public BasePathHandler() {
        super("basePath");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        document.setBasePath(node.stringValue());
    }
}
