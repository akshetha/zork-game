import java.util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Commands {

    ArrayList<String> listOfCommands = new ArrayList<>();

    Scanner myObj = new Scanner(System.in);

    Room[][] rooms;

    Room currentRoom;

    private int nowY;

    private int nowX;

    ArrayList<String> validDirections = new ArrayList<>();

    private int times = 0;

    Inventory inventory = new Inventory();

    boolean isGameOver = false;

    House hs = new House();

    public Commands() {
        listOfCommands.add("go");
        listOfCommands.add("read");
        listOfCommands.add("collect");
        listOfCommands.add("eat");
        listOfCommands.add("look");
        listOfCommands.add("use");
        listOfCommands.add("talk");
        listOfCommands.add("skip");

        validDirections.add("east");
        validDirections.add("west");
        validDirections.add("north");
        validDirections.add("south");

        rooms = hs.setUpRoom();

        this.nowY = 0;
        this.nowX = 0;

        currentRoom = rooms[nowX][nowY];
    }

    public void processStep() {
        boolean navigationCompleted;
        while (!isGameOver) {
            navigationCompleted = false;
            showAll();
            String command = myObj.nextLine().trim().toLowerCase();

            if (listOfCommands.contains(command)) {
                if (command.equals("go")) {
                    while (!navigationCompleted) {
                        System.out.println("Enter the direction you would like to move in");
                        command = myObj.nextLine();
                        navigationCompleted = processNavigation(command);
                        // System.out.println("navigationCompleted"+navigationCompleted);
                    }
                } else if (command.equals("skip")) {
                    System.out.println("****** Exiting the Game ******");
                    break;
                } else {
                    System.out.println("You have not navigated yet. Please navigate first");
                }
            } else {
                System.out.println("Invalid command. Please enter a valid command");
            }
        }
    }

    private boolean processNavigation(String direction) {
        currentRoom = rooms[nowX][nowY];
        ArrayList<Items> itemsImportant = currentRoom.getImportantItems();
        Room previousRoom = currentRoom;

        try {
            Room grid;

            if (validDirections.contains(direction.toLowerCase())) {
                String mandatoryItemName = currentRoom.getMandatoryItem();

                unlockRoomIfKeyPresent();

                if (inventory.isAllItemPresentInInventory(itemsImportant)) {
                    isGameOver = false;
                    switch (direction) {
                        case "north":
                            grid = rooms[nowX + 1][nowY];
                            System.out.println("Mandatory Items " + grid.getMandatoryItem());
                            if (currentRoom.getCanGoNorth() && (!grid.getLocked())
                                    && inventory.isItemNamePresentInInventory(grid.getMandatoryItem())) {
                                nowX = nowX + 1;
                                System.out.println("You moved " + direction + " New Location: " + grid.getName());
                            } else {
                                System.out.println("Unable to navigate " + direction
                                        + ". Either there is no way (or) the room "
                                        + grid.getName()
                                        + " is locked (or) a mandatory item has not been collected in the earlier step.");
                                return false;
                            }
                            break;
                        case "south":
                            grid = rooms[nowX - 1][nowY];
                            if (currentRoom.getCanGoSouth() && (!grid.getLocked())
                                    && inventory.isItemNamePresentInInventory(grid.getMandatoryItem())) {
                                nowX = nowX - 1;
                                System.out.println("You have moved " + direction + " you are in " + grid.getName());
                            } else {
                                System.out.println("Unable to navigate " + direction
                                        + ". Either there is no way (or) the room "
                                        + grid.getName()
                                        + " is locked (or) a mandatory item has not been collected in the earlier step.");
                                return false;
                            }
                            break;
                        case "east":
                            grid = rooms[nowX][nowY + 1];
                            if (currentRoom.getCanGoEast() && (!grid.getLocked())
                                    && inventory.isItemNamePresentInInventory(grid.getMandatoryItem())) {
                                nowY = nowY + 1;
                                System.out.println("You have moved " + direction + " you are in " + grid.getName());
                            } else {
                                System.out.println("Unable to navigate " + direction
                                        + ". Either there is no way (or) the room "
                                        + grid.getName()
                                        + " is locked (or) a mandatory item has not been collected in the earlier step.");
                                return false;
                            }
                            break;
                        case "west":
                            grid = rooms[nowX][nowY - 1];
                            if (currentRoom.getCanGoWest() && (!grid.getLocked())
                                    && inventory.isItemNamePresentInInventory(grid.getMandatoryItem())) {
                                nowY = nowY - 1;
                                System.out.println("You have moved " + direction + " you are in " + grid.getName());
                            } else {
                                System.out.println("Unable to navigate " + direction
                                        + ". Either there is no way (or) the room "
                                        + grid.getName()
                                        + " is locked (or) a mandatory item has not been collected in the earlier step.");
                                return false;
                            }
                            break;
                    }
                } else {
                    System.out.println("You missed to collect the mandatory item : " + mandatoryItemName
                            + " needed to navigate to next room");
                    isGameOver = true;
                }
            } else {
                System.out.println("You have entered an invalid direction");
                return false;
            }

            currentRoom = rooms[nowX][nowY];
            if (currentRoom.getName().equals("attic")) {
                // uncomment this if u want to test only first room
                // if (inventory.isItemNamePresentInInventory("crimsonkey")) {
                // System.out.println("You have successfully completed the game");
                // } else {
                // System.out.println("You lost the game. You have not collected the crimson key
                // to unlock the attic");
                // }
                // isGameOver = true;
                System.out.println(currentRoom.getDescription());
                inventory.printInventory();
                rooms = hs.setUpUpSideDownWorld();
                nowX = 0;
                nowY = 2;
                currentRoom = rooms[nowX][nowY];
            } else if (currentRoom.getName().equals("living") && previousRoom.getName().equals("hall")) {
                previousRoom.setLocked();
            } else if (currentRoom.getName().equals("cliff")) {
                if (!inventory.isItemNamePresentInInventory("map")) {
                    System.out.println(
                            "SHOOT! You don't have a map in your inventory. You forgot to collect the map! Now you have to explore the land by yourself!!");
                    isGameOver = true;
                }
            }
            // System.out.println(currentRoom.getName());
            System.out.println(currentRoom.getDescription());
        } catch (Exception e) {
            System.out.println("Invalid Navigation. You can't move " + direction);
            return false;
        }
        return processRoomItems();
    }

    private void unlockRoomIfKeyPresent() {
        if (rooms[3][1].getLocked() && inventory.isItemNamePresentInInventory("redkey") && nowY == 1) {
            System.out.println("Unlocking Library Room");
            rooms[3][1].releaseLock();
        }
        if (rooms[3][2].getLocked() && inventory.isItemNamePresentInInventory("metalkey")) {
            System.out.println("Unlocking the Attic");
            rooms[3][2].releaseLock();
        }
        if (inventory.isItemNamePresentInInventory("ladder")) {
            rooms[2][1].releaseLock();
            if (times < 1) {
                System.out.println("Unlocking the Town House");
                times++;
            }
        }

        if (!rooms[2][1].getLocked())

        {
            rooms[3][1].releaseLock();
        }
    }

    private boolean processRoomItems() {
        ArrayList<Items> listOfRoomItems = currentRoom.getRoomItems();
        boolean itemStatus = false;

        if (listOfRoomItems.isEmpty()) {
            System.out.println("There are no items to collect in this room");
            itemStatus = true;
        }

        if (!listOfRoomItems.isEmpty() && !inventory.isAllItemPresentInInventory(listOfRoomItems)) {
            // System.out.println("You have " + listOfRoomItems.size() + " items to
            // collect");
            Items item;
            ItemProperty properties;
            for (int i = 0; i < listOfRoomItems.size(); i++) {
                itemStatus = false;
                item = listOfRoomItems.get(i);
                properties = item.getItemProperties();

                if (properties.isReadable()) {
                    System.out.println("You have " + item.getItemName() + " to read");
                } else if (properties.isCollectable()) {
                    System.out.println("You have " + item.getItemName() + " to collect");
                } else if (properties.isFood()) {
                    System.out.println("The room has " + item.getItemName() + " to eat");
                } else if (properties.isUsable()) {
                    System.out.println("The room has " + item.getItemName() + " to use");
                } else if (properties.isLookable()) {
                    System.out.println("The room has " + item.getItemName() + " to look");
                } else if (properties.isTalkable()) {
                    System.out.println("The room has " + item.getItemName() + " to talk");
                } else {
                    System.out.println("item is unidendifiable");
                }

                while (!itemStatus) {
                    showAll();
                    String command = myObj.nextLine();

                    if (command.equals("skip")) { // todo multiple items skip
                        // if (currentRoom.getCollectableItems().size() == 1) {
                        itemStatus = true;
                        listOfRoomItems = new ArrayList<>();
                        break;
                        // } else {
                        // System.out.println("The item " + item.getItemName() + " is skipped. Continue
                        // to next item");
                        // itemStatus = false;
                        // }
                    } else {
                        itemStatus = processCommand(command, item);
                        i = currentRoom.getCollectableItems().size() - 1;
                    }
                }
            }
        }

        if (itemStatus && inventory.isAllItemPresentInInventory(listOfRoomItems)) {
            // System.out.println("You have completed the navigation successfully");
            isGameOver = false;
        }

        return itemStatus;
    }

    private boolean processCommand(String command, Items item) {
        boolean isCommandSuccessful = false;

        if (listOfCommands.contains(command) && isCommandAllowed(command)) {
            switch (command) {
                case "read":
                    isCommandSuccessful = processReadAction(item);
                    break;
                case "collect":
                    isCommandSuccessful = processCollectItems(item);
                    break;
                case "eat":
                    isCommandSuccessful = processEatableItems(item);
                    break;
                case "look":
                    isCommandSuccessful = lookAtTheItems(item);
                    break;
                case "use":
                    isCommandSuccessful = useTheItems(item);
                    break;
                case "talk":
                    isCommandSuccessful = talkToThePerson(item);
                    break;
                default:
                    System.out.println("Invalid command. Operation can not be performed on this item");
                    break;
            }
        } else {
            System.out.println("I'm sorry, I don't understand that command");
        }

        return isCommandSuccessful;
    }

    // LOOK HERE
    private boolean talkToThePerson(Items item) {
        ArrayList<Items> items = currentRoom.getTalkableItems();
        Scanner why = new Scanner(System.in);
        if (items.contains(item)) {
            System.out.println(item.getDescription());
            if (item.getItemProperties().isCollectable()) {
                inventory.addToInventory(item);
            }
            System.out.println("You look at the " + item.getItemName());
            currentRoom.remove(item);

            if (currentRoom.getName().equals("townhouse") && inventory.isItemNamePresentInInventory("crimsonkey")) {
                System.out.println("\nHello There: You say quietly with a subtle wave");
                String blep = why.nextLine();
                System.out.println(
                        "The man turns around almost instantly. He raises his hand to shake yours.\n\nWelcome to my home, Axel!: He says as his smile gets deeper.");
                String blop = why.nextLine();
                System.out.println("I assume you have delivered my package and would like to go back home?");
                String bpop = why.nextLine();
                System.out.println(
                        "You nod in complete confusion. Your eyes still staring at the man.\nYou must be the Alchemist then: You question");
                String bpod = why.nextLine();
                System.out.println(
                        "The man's expression changes to a subtle smile.\n\nI understand it might be quite a shock to be teleported: He says\n\nBut I simply wanted to share what was just mine with someone like yourself.");
                String bwod = why.nextLine();
                System.out.println(
                        "And with that, with no say, you get brushed into a bright light. This light feels familiar like the time when you first came into this world. As you take a deep breath, you land back on the porch if your house. You can see your delivery truck park right along side the road. The praire feels the same as when you left it.");

                // "The man turns around almost instantly. He raises his hand to shake
                // yours.\n\nWelcome to my house, Axel!: He says as his smile gets deeper\n \nI
                // assume you have delivered my package and would like to go back home?\n You
                // nod in complete confusion. Your eyes still staring at the man.\n \nThe man's
                // expression changes.\n\nI understand it might be quite a shock to ");
                System.exit(0);
            } else {
                System.out.println("\nHello There: You say quietly with a subtle wave");
                String blep = why.nextLine();
                System.out.println(
                        "The man turns around almost instantly. He raises his hand to shake yours.\n\nWelcome to my home, Axel!: He says as his smile gets deeper.");
                String blop = why.nextLine();
                System.out.println("I assume you have delivered my package and would like to go back home?");
                String bpop = why.nextLine();
                System.out.println(
                        "You nod in complete confusion. Your eyes still staring at the man.\nYou must be the Alchemist then: You question");
                String bpod = why.nextLine();
                System.out.println(
                        "The man's expression changes to a subtle smile.\n\nI understand it might be quite a shock to be teleported: He says\n\nBut I simply wanted to share what was just mine with someone like yourself.");
                String bwod = why.nextLine();
                System.out.println(
                        "You however were not as great as I had imagined. I see that you do not have the crimson key. How sad.\n \nWith that, the Alchemist teleport you into the forest. \n[You are stuck here forever]");
                System.exit(1);
            }

            return true;
        } else {
            System.out.println("Item " + item.getItemName() + " is not talkable. Try different option");
        }
        return false;
    }

    private boolean useTheItems(Items item) {
        ArrayList<Items> items = currentRoom.getUsableItems();

        if (items.contains(item)) {

            if (currentRoom.getName().equals("cliff")) {
                if (!inventory.isItemNamePresentInInventory("map")) {
                    System.out.println(
                            "You do not have map in your inventory. You missed to collect the map. Explore the village by yourself");
                    return true;
                }
            }

            System.out.println(item.getDescription());
            if (item.getItemProperties().isCollectable()) {
                inventory.addToInventory(item);
            }

            if (item.getItemName().equals("lamp")) {
                System.out.println(item.getDescription());
            } else if (item.getItemName().equals("map")) {
                System.out.println("You are viewing the map. " + item.getDescription());
            }
            currentRoom.remove(item);
            return true;
        } else {
            System.out.println("Item " + item.getItemName() + " is not usable. Try different option");
        }

        return false;
    }

    private boolean lookAtTheItems(Items item) {
        ArrayList<Items> items = currentRoom.getLookableItems();

        if (items.contains(item)) {
            System.out.println(item.getDescription());
            if (item.getItemProperties().isCollectable() || item.getItemName().equals("map")) {
                inventory.addToInventory(item);
            }
            currentRoom.remove(item);
            System.out.println("You finished looking at " + item.getItemName());
            if (item.getItemName().equals("painting") || item.getItemName().equals("reddrawer")
                    || item.getItemName().equals("redbook")) {
                System.out.println("Hurray!!!! you found " + currentRoom.getCollectableItemNames().toString()
                        + " at the " + item.getItemName());
                inventory.addListToInventory(currentRoom.getCollectableItems());
                currentRoom.removeItems(currentRoom.getCollectableItems());
            }

            return true;
        } else {
            System.out.println("Item " + item.getItemName() + " is not lookable. Try different option");
        }

        return false;
    }

    private boolean processEatableItems(Items item) {
        ArrayList<Items> eatableitems = currentRoom.getFoodItems();

        if (eatableitems.contains(item)) {
            System.out.println(item.getDescription()); // todo life add and minus
            if (item.getItemProperties().isCollectable()) {
                inventory.addToInventory(item);
            }
            System.out.println("You finished eating " + item.getItemName());
            currentRoom.remove(item);
            return true;
        } else {
            System.out.println("Item " + item.getItemName() + " is not eatable. Try different option");
        }

        return false;
    }

    private boolean processCollectItems(Items item) {
        ArrayList<Items> itemsToBeCollected = currentRoom.getCollectableItems();

        if (itemsToBeCollected.contains(item)) {
            System.out.println("******" + item.getDescription() + "******");
            inventory.addToInventory(item);
            System.out.println("You have collected the item: " + item.getItemName() + " with description: "
                    + item.getDescription());
            currentRoom.remove(item);
            return true;
        }
        return false;
    }

    private boolean processReadAction(Items item) {
        ArrayList<Items> itemsToBeRead = currentRoom.getReadableItems();

        if (itemsToBeRead.contains(item)) {
            System.out.println("The letter is read and the content is :");
            System.out.println("******" + item.getDescription() + "******");
            if (item.getItemProperties().isCollectable() || item.getItemName().equals("map")) {
                inventory.addToInventory(item);
            }

            currentRoom.remove(item);
            return true;
        } else {
            System.out.println("Item " + item.getItemName() + " is not eatable. Try different option");
        }
        return false;
    }

    private boolean isCommandAllowed(String command) {
        ArrayList<Items> collectableItems = currentRoom.getCollectableItems();
        ArrayList<Items> itemsToConsider = new ArrayList<>();

        switch (command) {
            case "go":
                if (!collectableItems.isEmpty() && !inventory.isAllItemPresentInInventory(collectableItems)) {
                    System.out.println("You can not proceed further. You have not collected/read the important item");
                    return false;
                }
                break;
            case "read":
                itemsToConsider = currentRoom.getReadableItems();
                break;
            case "collect":
                itemsToConsider = currentRoom.getCollectableItems();
                break;
            case "eat":
                itemsToConsider = currentRoom.getFoodItems();
                break;
            case "look":
                itemsToConsider = currentRoom.getLookableItems();
                break;
            case "use":
                itemsToConsider = currentRoom.getUsableItems();
                break;
            case "talk":
                itemsToConsider = currentRoom.getTalkableItems();
                break;
            case "skip":
                return true;
        }

        if (itemsToConsider.isEmpty()) {
            System.out.println("Invalid Command. There is no item to " + command + " in this room");
            return false;
        } else if (inventory.isAllItemPresentInInventory(itemsToConsider)) {
            System.out.println("The Item already present in your inventory");
            return false;
        } else {
            return true;
        }
    }

    private void showAll() {
        System.out.println("\nEnter the command how you want to proceed");
        for (String listOfCommand : listOfCommands) {
            System.out.print(listOfCommand + "\n");
        }
        System.out.println();
    }
}
