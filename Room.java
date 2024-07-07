import java.util.*;

import java.util.ArrayList;

public class Room {

    private boolean locked;
    private boolean canGoNorth;
    private boolean canGoEast;
    private boolean canGoWest;
    private boolean canGoSouth;
    private String description;
    private String name;
    public ArrayList<Items> roomItems;

    private String mandatoryItem;

    public Room(String name, boolean locked, boolean canGoNorth, boolean canGoSouth, boolean canGoEast, boolean canGoWest, String description, ArrayList <Items> roomItems, String mandatoryItem) {

        this.name = name;
        this.locked = locked;
        this.canGoNorth = canGoNorth;
        this.canGoSouth = canGoSouth;
        this.canGoWest = canGoWest;
        this.canGoEast = canGoEast;
        this.description = description;
        this.roomItems = roomItems;
        this.mandatoryItem = mandatoryItem;
    }

    public String getMandatoryItem() {
        return mandatoryItem;
    }
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean getCanGoNorth() {
        return canGoNorth;
    }

    public boolean getCanGoSouth() {
        return canGoSouth;
    }

    public boolean getCanGoEast() {
        return canGoEast;
    }

    public boolean getCanGoWest() {
        return canGoWest;
    }

    public boolean getLocked(){
        return locked;
    }

    public ArrayList<Items> getRoomItems() {
        return roomItems;
    }

    public ArrayList<Items> getImportantItems() {
        ArrayList<Items> importantItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.isImportant()) {
                importantItems.add(item);
            }
        }
        return importantItems;
    }

    public ArrayList<Items> getTalkableItems() {
        ArrayList<Items> collectableItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.getItemProperties().isTalkable()) {
                collectableItems.add(item);
            }
        }
        return collectableItems;
    }

    public ArrayList<Items> getLookableItems() {
        ArrayList<Items> collectableItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.getItemProperties().isLookable()) {
                collectableItems.add(item);
            }
        }
        return collectableItems;
    }

    public ArrayList<Items> getCollectableItems() {
        ArrayList<Items> collectableItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.getItemProperties().isCollectable()) {
                collectableItems.add(item);
            }
        }
        return collectableItems;
    }

    public ArrayList<String> getCollectableItemNames() {
        ArrayList<String> collectableItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.getItemProperties().isCollectable()) {
                collectableItems.add(item.getItemName());
            }
        }
        return collectableItems;
    }

    public ArrayList<Items> getReadableItems() {
        ArrayList<Items> readableItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.getItemProperties().isReadable()) {
                readableItems.add(item);
            }
        }
        return readableItems;
    }

    public ArrayList<Items> getUsableItems() {
        ArrayList<Items> usableItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.getItemProperties().isUsable()) {
                usableItems.add(item);
            }
        }
        return usableItems;
    }

    public ArrayList<Items> getFoodItems() {
        ArrayList<Items> foodItems = new ArrayList<>();
        for (Items item : roomItems) {
            if (item.getItemProperties().isFood()) {
                foodItems.add(item);
            }
        }
        return foodItems;
    }

    public void setLocked(){
        this.locked = true;
    }

    public void releaseLock(){
        this.locked = false;
    }

    public String toString() {
        return name + "\n\n" + description + "\nNorth: " + canGoNorth + "\nEast: " + canGoEast + "\nSouth: " + canGoSouth
                + "\nWest: " + canGoWest + "\n";
    }

    public void remove(Items item){
        roomItems.remove(item);
    }

    public void removeItems(ArrayList<Items> listOfItemsToBeRemoved){
        roomItems.removeAll(listOfItemsToBeRemoved);
    }
}


