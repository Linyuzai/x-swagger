package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsDescriptionHandler extends AbstractSwaggerHandler {

    public PathsDescriptionHandler() {
        super("paths", "*", "*", "description");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerPath path = getPath(document, node);
        path.setDescription(node.stringValue());
    }
}
