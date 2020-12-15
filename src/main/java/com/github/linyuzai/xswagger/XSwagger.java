package com.github.linyuzai.xswagger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.handler.sort.SortNodeHandler;
import com.github.linyuzai.xswagger.handler.SwaggerHandler;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.github.linyuzai.xswagger.node.gson.GsonSwaggerNode;
import com.github.linyuzai.xswagger.node.jackson.JacksonSwaggerNode;

import java.util.ArrayList;
import java.util.List;

public class XSwagger {

    private List<SortNodeHandler> sortHandlers = new ArrayList<>();

    private List<SwaggerHandler> preHandlers = new ArrayList<>();

    private List<SwaggerHandler> handlers = new ArrayList<>();

    private List<SwaggerHandler> postHandlers = new ArrayList<>();

    public void addSortHandler(SortNodeHandler handler) {
        sortHandlers.add(0, handler);
    }

    public void addPreHandler(SwaggerHandler handler) {
        preHandlers.add(handler);
    }

    public void addHandler(SwaggerHandler handler) {
        handlers.add(handler);
    }

    public void addPostHandler(SwaggerHandler handler) {
        postHandlers.add(handler);
    }

    public SwaggerDocument gson(String json) {
        return handle(new SwaggerDocument(), GsonSwaggerNode.from(json));
    }

    public SwaggerDocument jackson(String json) throws JsonProcessingException {
        return handle(new SwaggerDocument(), JacksonSwaggerNode.from(json));
    }

    public SwaggerDocument handle(SwaggerDocument document, SwaggerNode node) {
        handle0(document, node, sortHandlers);
        handle0(document, node, preHandlers);
        handle0(document, node, handlers);
        handle0(document, node, postHandlers);
        return document;
    }

    private static void handle0(SwaggerDocument document, SwaggerNode node, List<? extends SwaggerHandler> handlers) {
        for (SwaggerHandler handler : handlers) {
            if (handler.isLocated(node)) {
                handler.handle(document, node);
            }
        }
        for (SwaggerNode child : node.getChildren()) {
            handle0(document, child, handlers);
        }
    }
}
