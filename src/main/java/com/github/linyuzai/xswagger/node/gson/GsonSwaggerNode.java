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
        return null;
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