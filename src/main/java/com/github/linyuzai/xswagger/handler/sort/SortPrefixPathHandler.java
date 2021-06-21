package com.github.linyuzai.xswagger.handler.sort;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.handler.AbstractSwaggerHandler;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class SortPrefixPathHandler extends AbstractSwaggerHandler {

    private String path;

    public SortPrefixPathHandler(String path) {
        this.path = path;
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        document.sortPaths((o1, o2) -> {
            int key1 = o1.startsWith(path) ? 0 : 1;
            int key2 = o2.startsWith(path) ? 0 : 1;
            return key1 - key2;
        });
    }

    @Override
    public boolean isLocated(SwaggerNode node) {
        return true;
    }
}
