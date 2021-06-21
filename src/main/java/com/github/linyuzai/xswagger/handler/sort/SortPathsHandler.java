package com.github.linyuzai.xswagger.handler.sort;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.handler.AbstractSwaggerHandler;
import com.github.linyuzai.xswagger.handler.SwaggerHandler;
import com.github.linyuzai.xswagger.node.SwaggerNode;

import java.util.ArrayList;
import java.util.List;

public class SortPathsHandler extends AbstractSwaggerHandler {

    private List<SwaggerHandler> handlers = new ArrayList<>();

    public SortPathsHandler full(String path) {
        handlers.add(0, new SortFullPathHandler(path));
        return this;
    }

    public SortPathsHandler prefix(String path) {
        handlers.add(0, new SortPrefixPathHandler(path));
        return this;
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        for (SwaggerHandler handler : handlers) {
            handler.handle(document, node);
        }
    }
}
