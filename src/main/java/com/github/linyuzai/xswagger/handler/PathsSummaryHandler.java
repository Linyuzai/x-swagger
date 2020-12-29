package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsSummaryHandler extends AbstractSwaggerHandler {

    public PathsSummaryHandler() {
        super("paths", "*", "*", "summary");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerPath path = getPath(document, node);
        if (path.getDescription() == null || path.getDescription().trim().isEmpty()) {
            path.setDescription(node.stringValue());
        }
    }
}
