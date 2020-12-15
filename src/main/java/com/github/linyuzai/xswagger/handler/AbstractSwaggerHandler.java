package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.locator.Locator;
import com.github.linyuzai.xswagger.locator.array.ArrayLocator;
import com.github.linyuzai.xswagger.node.SwaggerNode;

import java.util.Collections;
import java.util.List;

public abstract class AbstractSwaggerHandler implements SwaggerHandler {

    private List<Locator> locators;

    public AbstractSwaggerHandler(String... locations) {
        this(new ArrayLocator(locations));
    }

    public AbstractSwaggerHandler(Locator locator) {
        this(Collections.singletonList(locator));
    }

    public AbstractSwaggerHandler(List<Locator> locators) {
        this.locators = locators;
    }

    public List<Locator> getLocators() {
        return locators;
    }

    @Override
    public boolean isLocated(SwaggerNode node) {
        for (Locator locator : locators) {
            if (locator.locate(node)) {
                return true;
            }
        }
        return false;
    }

    public SwaggerPath getPath(SwaggerDocument document, SwaggerNode node) {
        return document.getPath(node.getLocation().get(1), node.getLocation().get(2));
    }

    public SwaggerPathParameter getPathParameter(SwaggerDocument document, SwaggerNode node) {
        return document.getPath(node.getLocation().get(1), node.getLocation().get(2)).getPathParameter(node.getLocation().get(4));
    }

    public void removePathParameter(SwaggerDocument document, SwaggerNode node) {
        document.getPath(node.getLocation().get(1), node.getLocation().get(2)).removePathParameter(node.getLocation().get(4));
    }
}
