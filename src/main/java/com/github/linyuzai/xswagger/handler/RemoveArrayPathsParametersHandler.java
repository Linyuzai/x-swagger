package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class RemoveArrayPathsParametersHandler extends AbstractSwaggerHandler {

    public RemoveArrayPathsParametersHandler() {
        super("paths", "*", "*", "parameters", "*", "name");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        String sv = node.stringValue();
        if (sv != null && sv.contains("[0].")) {
            removePathParameter(document, node);
        }
    }
}
