package com.github.linyuzai.xswagger.node;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSwaggerNode implements SwaggerNode {

    private final List<String> location;

    private String key;

    private Object value;

    private SwaggerNode parent;

    private List<? extends SwaggerNode> children;

    public AbstractSwaggerNode(Object value) {
        this(null, null, value);
    }

    public AbstractSwaggerNode(SwaggerNode parent, String key, Object value) {
        this.location = new LinkedList<>();
        if (parent != null) {
            this.location.addAll(parent.getLocation());
        }
        if (key != null) {
            this.key = key;
            this.location.add(key);
        }
        this.value = value;
        this.parent = parent;
        this.children = children(value);
    }

    @Override
    public List<String> getLocation() {
        return location;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public SwaggerNode getParent() {
        return parent;
    }

    @Override
    public List<? extends SwaggerNode> getChildren() {
        return children;
    }

    protected abstract List<? extends SwaggerNode> children(Object o);
}
