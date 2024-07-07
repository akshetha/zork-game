import java.util.*;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Items> inventory = new ArrayList<>();

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public void addToInventory(Items item) {
        inventory.add(item);
    }

    public void addListToInventory(ArrayList<Items> listOfItem) {
        inventory.addAll(listOfItem);
    }

    public boolean isItemPresentInInventory(Items items) {
        if (inventory.contains(items)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isItemNamePresentInInventory(String itemName) {
        if (itemName.equals("")) {
            return true;
        }

        for(int i=0;i<inventory.size();i++) {
            if(inventory.get(i).getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAllItemPresentInInventory(ArrayList<Items> itemList) {
        for(int i=0; i < itemList.size(); i ++) {
            if (!inventory.contains(itemList.get(i))) {
                return false;
            }
        }
        return true;
    }


    public void printInventory() {
        System.out.println("\n Inventory has :");
        for(int i=0;i<inventory.size();i++) {
            System.out.println(inventory.get(i).getItemName());
        }
    }
}
