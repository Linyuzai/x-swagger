package com.github.linyuzai.xswagger.node.gson;

import com.github.linyuzai.xswagger.exception.XSwaggerException;
import com.github.linyuzai.xswagger.node.AbstractSwaggerNode;
import com.github.linyuzai.xswagger.node.SwaggerJson;
import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.google.gson.*;

import java.util.*;

public class GsonSwaggerNode extends AbstractSwaggerNode implements SwaggerJson {

    private static Gson gson = new Gson();

    private static JsonParser parser = new JsonParser();

    public static GsonSwaggerNode from(String json) {
        return new GsonSwaggerNode(parser.parse(json));
    }

    public GsonSwaggerNode(JsonElement element) {
        super(element);
    }

    public GsonSwaggerNode(SwaggerNode parent, String key, JsonElement element) {
        super(parent, key, element);
    }

    @Override
    protected List<? extends SwaggerNode> children(Object o) {
        if (o instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) o;
            if (jsonObject.size() == 0) {
                return Collections.emptyList();
            }
            List<SwaggerNode> nodes = new ArrayList<>(jsonObject.size());
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                nodes.add(new GsonSwaggerNode(this, entry.getKey(), entry.getValue()));
            }
            return nodes;
        } else if (o instanceof JsonArray) {
            JsonArray jsonArray = (JsonArray) o;
            if (jsonArray.size() == 0) {
                return Collections.emptyList();
            }
            List<SwaggerNode> list = new ArrayList<>(jsonArray.size());
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement je = jsonArray.get(i);
                list.add(new GsonSwaggerNode(this, String.valueOf(i), je));
            }
            return list;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String toJson() {
        return gson.toJson(getValue());
    }

    @Override
    public Map<String, Object> toResponseMap() {
        return toResponseMap(getValue());
    }

    private Map<String, Object> toResponseMap(Object val) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (val instanceof JsonObject) {
            JsonObject properties = ((JsonObject) val).getAsJsonObject("properties");
            if (properties == null) {
                return map;
            }
            for (Map.Entry<String, JsonElement> entry : properties.entrySet()) {
                JsonObject property = (JsonObject) entry.getValue();
                Object ref = property.get("$ref");
                if (ref == null) {
                    String type = property.get("type").getAsString();
                    if ("array".equals(type)) {
                        JsonObject items = property.getAsJsonObject("items");
                        JsonElement refItems = items.get("$ref");
                        if (refItems == null) {
                            JsonElement itemType = items.get("type");
                            if (itemType instanceof JsonPrimitive) {
                                map.put(entry.getKey(), Collections.singletonList(itemType.getAsString()));
                            } else {
                                map.put(entry.getKey(), Collections.emptyList());
                            }
                        } else {
                            //children是相同的类型
                            if (refItems instanceof JsonNull) {
                                map.put(entry.getKey(), Collections.singletonList("对象本身"));
                            } else {
                                map.put(entry.getKey(), Collections.singletonList(toResponseMap(refItems)));
                            }
                        }
                    } else {
                        JsonElement desc = property.get("description");
                        if (desc == null) {
                            map.put(entry.getKey(), "");
                        } else {
                            map.put(entry.getKey(), desc.getAsString());
                        }
                    }
                } else {
                    if (ref instanceof JsonNull) {
                        map.put(entry.getKey(), "对象本身");
                    } else {
                        map.put(entry.getKey(), toResponseMap(ref));
                    }
                }
            }
            return map;
        }
        throw new XSwaggerException("Node should be JsonObject, but " + val.getClass().getSimpleName() + " now");
    }

    @Override
    public String toResponseJson(boolean array) {
        if (array) {
            return gson.toJson(Collections.singletonList(toResponseMap()));
        } else {
            return gson.toJson(toResponseMap());
        }
    }

    @Override
    public String stringValue() {
        Object val = getValue();
        if (val instanceof JsonPrimitive) {
            return ((JsonPrimitive) val).getAsString();
        }
        throw new XSwaggerException("Node should be JsonPrimitive, but " + val.getClass().getSimpleName() + " now");
    }

    @Override
    public boolean booleanValue() {
        Object val = getValue();
        if (val instanceof JsonPrimitive) {
            return ((JsonPrimitive) val).getAsBoolean();
        }
        throw new XSwaggerException("Node should be JsonPrimitive, but " + val.getClass().getSimpleName() + " now");
    }
}
