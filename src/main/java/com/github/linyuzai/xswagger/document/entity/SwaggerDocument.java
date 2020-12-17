package com.github.linyuzai.xswagger.document.entity;

import com.github.linyuzai.xswagger.document.renderer.MarkdownRenderer;
import com.github.linyuzai.xswagger.document.renderer.SwaggerDocumentRenderer;
import com.github.linyuzai.xswagger.document.writer.MarkdownWriter;
import com.github.linyuzai.xswagger.document.writer.SwaggerDocumentWriter;
import com.github.linyuzai.xswagger.node.SwaggerNode;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SwaggerDocument {

    private String basePath;

    private Map<String, Map<String, SwaggerPath>> pathMap = new LinkedHashMap<>();

    private Map<String, SwaggerNode> definitionMap = new LinkedHashMap<>();

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public SwaggerPath getPath(String url, String method) {
        return pathMap.computeIfAbsent(url, u -> new LinkedHashMap<>()).computeIfAbsent(method, m -> new SwaggerPath(method, url));
    }

    public Collection<SwaggerPath> getPaths() {
        return pathMap.values().stream().flatMap(it -> it.values().stream()).collect(Collectors.toList());
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
        return markdown(null);
    }

    public MarkdownWriter markdown(String title) {
        MarkdownRenderer renderer = new MarkdownRenderer(title);
        renderer.render(this);
        return renderer.writer();
    }
}
