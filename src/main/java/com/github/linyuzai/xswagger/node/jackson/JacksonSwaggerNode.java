package com.github.linyuzai.xswagger.node.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import com.github.linyuzai.xswagger.exception.XSwaggerException;
import com.github.linyuzai.xswagger.node.AbstractSwaggerNode;
import com.github.linyuzai.xswagger.node.SwaggerJson;
import com.github.linyuzai.xswagger.node.SwaggerNode;

import java.util.*;

public class JacksonSwaggerNode extends AbstractSwaggerNode implements SwaggerJson {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JacksonSwaggerNode from(String json) {
        try {
            return new JacksonSwaggerNode(objectMapper.readTree(json));
        } catch (JsonProcessingException e) {
            throw new XSwaggerException(e);
        }
    }

    public JacksonSwaggerNode(JsonNode node) {
        super(node);
    }

    public JacksonSwaggerNode(SwaggerNode parent, String key, JsonNode node) {
        super(parent, key, node);
    }

    @Override
    protected List<? extends SwaggerNode> children(Object o) {
        if (o instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) o;
            if (objectNode.size() == 0) {
                return Collections.emptyList();
            }
            List<SwaggerNode> nodes = new ArrayList<>(objectNode.size());
            Iterator<Map.Entry<String, JsonNode>> iterator = objectNode.fields();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = iterator.next();
                nodes.add(new JacksonSwaggerNode(this, entry.getKey(), entry.getValue()));
            }
            return nodes;
        } else if (o instanceof ArrayNode) {
            ArrayNode arrayNode = (ArrayNode) o;
            if (arrayNode.size() == 0) {
                return Collections.emptyList();
            }
            List<SwaggerNode> list = new ArrayList<>(arrayNode.size());
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode jn = arrayNode.get(i);
                list.add(new JacksonSwaggerNode(this, String.valueOf(i), jn));
            }
            return list;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String toJson() {
        try {
            return objectMapper.writeValueAsString(getValue());
        } catch (JsonProcessingException e) {
            throw new XSwaggerException(e);
        }
    }

    @Override
    public Map<String, Object> toResponseMap() {
        return toResponseMap(getValue());
    }

    private Map<String, Object> toResponseMap(Object val) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (val instanceof ObjectNode) {
            JsonNode properties = ((ObjectNode) val).get("properties");
            Iterator<Map.Entry<String, JsonNode>> iterator = properties.fields();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = iterator.next();
                JsonNode property = entry.getValue();
                Object ref = property.get("$ref");
                if (ref == null) {
                    String type = property.get("type").asText();
                    if ("array".equals(type)) {
                        JsonNode items = property.get("items");
                        JsonNode refItems = items.get("$ref");
                        if (refItems == null) {
                            map.put(entry.getKey(), Collections.singletonList(type));
                        } else {
                            //children是相同的类型
                            map.put(entry.getKey(), Collections.singletonList(toResponseMap(refItems)));
                        }
                    } else {
                        JsonNode desc = property.get("description");
                        if (desc == null) {
                            map.put(entry.getKey(), "");
                        } else {
                            map.put(entry.getKey(), desc.asText());
                        }
                    }
                } else {
                    //children是相同的类型
                    map.put(entry.getKey(), toResponseMap(ref));
                }
            }
            return map;
        }
        throw new XSwaggerException("Node should be JsonObject, but " + val.getClass().getSimpleName() + " now");
    }

    @Override
    public String toResponseJson() {
        try {
            return objectMapper.writeValueAsString(toResponseMap());
        } catch (JsonProcessingException e) {
            throw new XSwaggerException(e);
        }
    }

    @Override
    public String stringValue() {
        Object val = getValue();
        if (val instanceof TextNode) {
            return ((TextNode) val).asText();
        }
        throw new XSwaggerException("Node should be TextNode, but " + val.getClass().getSimpleName() + " now");
    }

    @Override
    public boolean booleanValue() {
        Object val = getValue();
        if (val instanceof BooleanNode) {
            return ((BooleanNode) val).asBoolean();
        }
        throw new XSwaggerException("Node should be BooleanNode, but " + val.getClass().getSimpleName() + " now");
    }
}
