package com.github.linyuzai.xswagger;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.handler.SwaggerDocumentHandler;
import com.github.linyuzai.xswagger.handler.SwaggerHandler;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.github.linyuzai.xswagger.node.gson.GsonSwaggerNode;
import com.github.linyuzai.xswagger.node.jackson.JacksonSwaggerNode;

import java.util.ArrayList;
import java.util.List;

public class XSwagger {

    private List<SwaggerHandler> preHandlers = new ArrayList<>();

    private List<SwaggerHandler> handlers = new ArrayList<>();

    private List<SwaggerHandler> postHandlers = new ArrayList<>();

    private List<SwaggerDocumentHandler> sortHandlers = new ArrayList<>();

    private List<SwaggerDocumentHandler> documentHandlers = new ArrayList<>();

    public void addPreHandler(SwaggerHandler handler) {
        preHandlers.add(handler);
    }

    public void addHandler(SwaggerHandler handler) {
        handlers.add(handler);
    }

    public void addPostHandler(SwaggerHandler handler) {
        postHandlers.add(handler);
    }

    public void addSortHandler(SwaggerDocumentHandler handler) {
        sortHandlers.add(0, handler);
    }

    public void addDocumentHandler(SwaggerDocumentHandler handler) {
        documentHandlers.add(handler);
    }

    public SwaggerDocument gson(String json) {
        return handle(new SwaggerDocument(), GsonSwaggerNode.from(json));
    }

    public SwaggerDocument jackson(String json) {
        return handle(new SwaggerDocument(), JacksonSwaggerNode.from(json));
    }

    public SwaggerDocument handle(SwaggerDocument document, SwaggerNode node) {
        if (!preHandlers.isEmpty()) {
            handleSwaggerNode(document, node, preHandlers);
        }
        if (!handlers.isEmpty()) {
            handleSwaggerNode(document, node, handlers);
        }
        if (!postHandlers.isEmpty()) {
            handleSwaggerNode(document, node, postHandlers);
        }
        if (!sortHandlers.isEmpty()) {
            handleSwaggerDocument(document, sortHandlers);
        }
        if (!documentHandlers.isEmpty()) {
            handleSwaggerDocument(document, documentHandlers);
        }
        return document;
    }

    private static void handleSwaggerDocument(SwaggerDocument document, List<? extends SwaggerDocumentHandler> handlers) {
        for (SwaggerDocumentHandler handler : handlers) {
            handler.handle(document);
        }
    }

    private static void handleSwaggerNode(SwaggerDocument document, SwaggerNode node, List<? extends SwaggerHandler> handlers) {
        for (SwaggerHandler handler : handlers) {
            if (handler.isLocated(node)) {
                handler.handle(document, node);
            }
        }
        for (SwaggerNode child : node.getChildren()) {
            handleSwaggerNode(document, child, handlers);
        }
    }
}
