package com.github.linyuzai.xswagger;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.handler.SwaggerHandler;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.github.linyuzai.xswagger.node.gson.GsonSwaggerNode;
import com.github.linyuzai.xswagger.node.jackson.JacksonSwaggerNode;

import java.util.ArrayList;
import java.util.List;

public class XSwagger {

    private List<SwaggerHandler> handlers = new ArrayList<>();

    public void addHandler(SwaggerHandler handler) {
        handlers.add(handler);
    }

    public SwaggerDocument gson(String json) {
        return handle(new SwaggerDocument(), GsonSwaggerNode.from(json));
    }

    public SwaggerDocument jackson(String json) {
        return handle(new SwaggerDocument(), JacksonSwaggerNode.from(json));
    }

    public SwaggerDocument handle(SwaggerDocument document, SwaggerNode node) {
        for (SwaggerHandler handler : handlers) {
            handleSwaggerNode(document, node, handler);
        }
        return document;
    }

    private static void handleSwaggerNode(SwaggerDocument document, SwaggerNode node, SwaggerHandler handler) {
        if (handler.isLocated(node)) {
            handler.handle(document, node);
        }
        for (SwaggerNode child : node.getChildren()) {
            handleSwaggerNode(document, child, handler);
        }
    }
}
