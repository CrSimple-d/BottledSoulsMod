package net.crsimple.bottledsouls.config;

import java.util.Collection;
import java.util.HashSet;

public class ActivableCollection<T> {
    public boolean enable = false;
    public final Collection<T> collection;

    public ActivableCollection(boolean enable,Collection<T> collection) {
        this.enable = enable;
        this.collection = collection;
    }
    public ActivableCollection() {
        this.collection = new HashSet<>();
    }

    public boolean contains(T t) {
        return isEnable() && collection.contains(t);
    }

    public boolean isEnable() {
        return enable;
    }

    public Collection<T> getCollection() {
        return collection;
    }

    public void toggle() {
        this.enable = !this.enable;
    }
}
