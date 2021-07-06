package com.github.linyuzai.xswagger;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.handler.*;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.github.linyuzai.xswagger.node.SwaggerNodeFactory;
import com.github.linyuzai.xswagger.node.jackson.JacksonSwaggerNodeFactory;

import java.util.ArrayList;
import java.util.List;

public class XSwagger {

    private SwaggerNodeFactory nodeFactory = new JacksonSwaggerNodeFactory();

    private List<SwaggerHandler> handlers = new ArrayList<>();

    public SwaggerNodeFactory getNodeFactory() {
        return nodeFactory;
    }

    public void setNodeFactory(SwaggerNodeFactory nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    public void addHandler(SwaggerHandler handler) {
        handlers.add(handler);
    }

    public List<SwaggerHandler> getHandlers() {
        return handlers;
    }

    public XSwagger useDefaultHandlers() {

        addHandler(new DefinitionsHandler());

        addHandler(new BasePathHandler());
        addHandler(new PathsDescriptionHandler());
        addHandler(new PathsTagsHandler());
        addHandler(new PathsSummaryHandler());

        addHandler(new PathsParametersDescriptionHandler());
        addHandler(new PathsParametersInHandler());
        addHandler(new PathsParametersNameHandler());
        addHandler(new PathsParametersRequiredHandler());
        addHandler(new PathsParametersTypeHandler());

        addHandler(new DefinitionsRefHandler());

        addHandler(new PathsResponsesRefHandler());
        addHandler(new PathsResponsesRefArrayHandler());
        addHandler(new PathsResponsesSchemaHandler());

        addHandler(new PathsParametersArrayRemoveHandler());
        addHandler(new PathsParametersObjectRemoveHandler());

        return this;
    }

    public SwaggerDocument from(String json) {
        return handle(new SwaggerDocument(), nodeFactory.create(json));
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
