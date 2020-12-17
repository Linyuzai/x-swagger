package com.github.linyuzai.xswagger.handler.sort;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.handler.AbstractSwaggerHandler;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class SortNodesHandler extends AbstractSwaggerHandler {

    public SortNodesHandler(String... locations) {
        super(locations);
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        node.getParent().getChildren().sort((o1, o2) -> {
            int key1 = o1.getKey().equals(node.getKey()) ? 0 : 1;
            int key2 = o2.getKey().equals(node.getKey()) ? 0 : 1;
            return key1 - key2;
        });
    }
}
