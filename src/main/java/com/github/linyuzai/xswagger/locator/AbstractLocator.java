package com.github.linyuzai.xswagger.locator;

import com.github.linyuzai.xswagger.node.SwaggerNode;

import java.util.List;

public abstract class AbstractLocator implements Locator {

    protected List<String> locations;

    public AbstractLocator(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getLocations() {
        return locations;
    }

    @Override
    public boolean locate(SwaggerNode node) {
        if (locations.size() > node.getLocation().size()) {
            return false;
        } else if (locations.size() == node.getLocation().size()) {
            return locate0(locations, node.getLocation());
        } else {
            int size = node.getLocation().size() - locations.size();
            for (int i = 1; i <= size; i++) {
                List<String> l2 = node.getLocation().subList(i, i + locations.size());
                if (locate0(locations, l2)) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean locate0(List<String> l1, List<String> l2) {
        if (l1.size() == l2.size()) {
            for (int i = 0; i < l1.size(); i++) {
                String s1 = l1.get(i);
                String s2 = l2.get(i);
                if (!"*".equals(s1) && !"*".equals(s2) && !s1.equals(s2)) {
                    return false;
                }
            }
            return true;
        } else {
            throw new RuntimeException("Size is diff");
        }
    }
}
