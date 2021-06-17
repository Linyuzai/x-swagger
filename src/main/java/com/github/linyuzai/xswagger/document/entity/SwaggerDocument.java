package com.github.linyuzai.xswagger.document.entity;

import com.github.linyuzai.xswagger.document.renderer.MarkdownRenderer;
import com.github.linyuzai.xswagger.document.renderer.SwaggerDocumentRenderer;
import com.github.linyuzai.xswagger.document.writer.MarkdownWriter;
import com.github.linyuzai.xswagger.document.writer.SwaggerDocumentWriter;
import com.github.linyuzai.xswagger.node.SwaggerNode;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class SwaggerDocument {

    private String title;

    private String basePath;

    private Map<String, Map<String, SwaggerPath>> pathMap = new LinkedHashMap<>();

    private Map<String, SwaggerNode> definitionMap = new LinkedHashMap<>();

    public SwaggerDocument title(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public SwaggerPath getPath(String url, String method) {
        return pathMap.computeIfAbsent(url, u -> new LinkedHashMap<>()).computeIfAbsent(method, m -> new SwaggerPath(method, url));
    }

    public List<SwaggerPath> getPaths() {
        return pathMap.values().stream().flatMap(it -> it.values().stream()).collect(Collectors.toList());
    }

    public void sortPaths(Comparator<String> c) {
        pathMap = pathMap.entrySet().stream()
                .sorted((o1, o2) -> c.compare(o1.getKey(), o2.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, throwingMerger(), LinkedHashMap::new));
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

    public void addDefinition(SwaggerNode node) {
        definitionMap.put("#/" + node.getParent().getKey() + "/" + node.getKey(), node);
    }

    public SwaggerNode getDefinition(String key) {
        return definitionMap.get(key);
    }

    public SwaggerDocumentWriter render(SwaggerDocumentRenderer renderer) {
        renderer.render(this);
        return renderer.writer();
    }

    public MarkdownWriter markdown() {
        MarkdownRenderer renderer = new MarkdownRenderer();
        renderer.render(this);
        return renderer.writer();
    }
}
