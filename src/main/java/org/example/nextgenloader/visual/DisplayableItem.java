package org.example.nextgenloader.visual;

import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;

import java.util.Optional;

public class DisplayableItem implements PropertySheet.Item {

    private String name;


    private String description;

    public void setDescription(String description) {
        this.description = description;
    }



    public DisplayableItem(String name) {
        this.name = name;
    }

    @Override
    public Class<?> getType() {
        return null;
    }

    @Override
    public String getCategory() {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue(Object o) {

    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.empty();
    }


    @Override
    public String toString() {
        return getName();
    }
}
