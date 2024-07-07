import java.util.*;

import java.util.ArrayList;

public class Items {
    private String itemName;

    private double itemWeight;

    private String itemType;

    private boolean isImportant;
    private ItemProperty itemProperties;

    private String description;


    public Items(String itemName, double itemWeight, String itemType, String description, boolean isImportant, ItemProperty properties) {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemType = itemType;
        this.description = description;
        this.isImportant = isImportant;
        this.itemProperties = properties;
    }

    public String getDescription() {
        return description;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public ItemProperty getItemProperties() {
        return itemProperties;
    }

    public void setItemProperties(ItemProperty itemProperties) {
        this.itemProperties = itemProperties;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void removeItem(Items item, ArrayList<Items> roomItems) {
        for(int i=0;i<roomItems.size();i++) {
            if(roomItems.get(i).equals(item))
                roomItems.remove(i);
        }
    }

    public boolean isItem(String aString) {
        if(aString.equals(itemName))
            return true;
        return false;
    }

}
