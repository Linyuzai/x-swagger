package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class DefinitionsHandler extends AbstractSwaggerHandler {

    public DefinitionsHandler() {
        super("definitions", "*");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        document.addDefinition(node);
    }
}
