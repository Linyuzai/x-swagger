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

    public static String format(String json) {
        if (json == null) {
            return null;
        }
        int level = 0;
        StringBuilder builder = new StringBuilder("\n");
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (level > 0 && '\n' == builder.charAt(builder.length() - 1)) {
                for (int j = 0; j < level; j++) {
                    builder.append("\t");
                }
            }
            switch (c) {
                case '{':
                case '[':
                    builder.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    builder.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    builder.append("\n");
                    level--;
                    for (int j = 0; j < level; j++) {
                        builder.append("\t");
                    }
                    builder.append(c);
                    break;
                default:
                    builder.append(c);
                    break;
            }
        }

        return builder.toString();
    }
}
