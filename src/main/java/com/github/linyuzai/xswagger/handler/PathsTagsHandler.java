package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsTagsHandler extends AbstractSwaggerHandler {

    public PathsTagsHandler() {
        super("paths", "*", "*", "tags");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerPath path = getPath(document, node);
        if (path.getTag() == null || path.getTag().trim().isEmpty()) {
            path.setTag(node.stringValue());
        }
    }
}
