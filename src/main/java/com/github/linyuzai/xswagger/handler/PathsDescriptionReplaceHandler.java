package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.exception.XSwaggerException;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsDescriptionReplaceHandler extends AbstractSwaggerHandler {

    private String contains;

    private String replacement;

    public PathsDescriptionReplaceHandler(String contains, String replacement) {
        if (contains == null || contains.isEmpty()) {
            throw new XSwaggerException("Contains can not be null or empty");
        }
        if (replacement == null) {
            throw new XSwaggerException("Replacement can not be null");
        }
        this.contains = contains;
        this.replacement = replacement;
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        for (SwaggerPath path : document.getPaths()) {
            String desc = path.getDescription();
            if (desc.contains(contains)) {
                path.setDescription(desc.replaceAll(contains, replacement));
            }
        }
    }

    @Override
    public boolean isLocated(SwaggerNode node) {
        return true;
    }
}
