import java.util.*;

import java.util.ArrayList;

public class House {
    public Room[][] rooms;

    public Room[][] setUpRoom() {
        rooms = new Room[4][3];
        ArrayList<Items> listOfItems = new ArrayList<>();

        rooms[0][0] = new Room("entrance", false, true, false, true, false, "Welcome to the House of Prairie",
                listOfItems, "");

        listOfItems = new ArrayList<>();
        ItemProperty property = new ItemProperty(true, true, true, false, false, false);
        Items item = new Items("note", 0.1, "instruction",
                "Hey Axel. I'm the owner of this place. I'm sorry I'm not home. Can you do me a huge favor and put it inside for me.",
                true, property);
        listOfItems.add(item);
        rooms[0][1] = new Room("mailbox", false, false, false, false, true,
                "A red mailbox stands before you. The note still hanging on the right side of it; upon closer look it seems to have your own name on it!",
                listOfItems, "");

        listOfItems = new ArrayList<>();// no item
        rooms[0][2] = new Room("bush", false, false, false, false, true,
                "You're at a bush...what are you doing here???", listOfItems, "");

        /***********************************************************************************************/

        listOfItems = new ArrayList<>();// no item
        property = new ItemProperty(true, false, true, false, false, false);
        item = new Items("housekey", 0.2, "key",
                "BAM! Your first key! You can finally enter the house! The door is to your east.", true,
                property);
        listOfItems.add(item);
        rooms[1][0] = new Room("hallway", false, false, true, true, false,
                "A passageway between the porch and the maindoor. You can feel the key under the mat.",
                listOfItems, "note");

        listOfItems = new ArrayList<>();// no item
        rooms[1][1] = new Room("hall", false, true, false, true, true,
                "Do us all a flavor and put it in the living room.", listOfItems, "housekey");

        listOfItems = new ArrayList<>(); // no item
        rooms[1][2] = new Room("study", true, true, false, false, true,
                "Room is locked. What can you do now? Try roaming around more.", listOfItems, "");

        /***********************************************************************************************/

        listOfItems = new ArrayList<>();
        property = new ItemProperty(false, false, true, false, false, false);
        item = new Items("lamp", 10.5, "object",
                "lamp is lighted. It shines bright in front of you. It brings your attention to a drawer along with a map.",
                false, property);
        listOfItems.add(item);
        property = new ItemProperty(false, true, false, false, false, false);
        item = new Items("map", 0.2, "drawing",
                "A map titled Endeavor shows a forest surrounding a cliff. To the north, is a box titled village",
                true, property);
        listOfItems.add(item);
        property = new ItemProperty(false, false, false, false, true, false);
        item = new Items("reddrawer", 20.9, "object", "Drawer has a large metal chain with a small key", true,
                property);
        listOfItems.add(item);
        property = new ItemProperty(true, false, true, false, false, false);
        item = new Items("redkey", 0.2, "key", "Finally, a world of my own", true, property);
        listOfItems.add(item);
        rooms[2][0] = new Room("bedroom", false, false, false, true, false,
                "The bed room has a simplistic look with a single table, drawer, and a bed layed with considerable space. "
                        +
                        "There are drawings on the table with a lamp lit beside. They looks like they make a map",
                listOfItems, "");

        listOfItems = new ArrayList<>();
        property = new ItemProperty(false, false, false, false, true, false);
        item = new Items("painting", 1.0, "object",
                "You carefully examine the painting which looks unusually lifted from the wall.\n",
                false, property);
        listOfItems.add(item);
        property = new ItemProperty(true, true, true, false, false, false);
        item = new Items("letter", 0.1, "instruction", "Do not worry. I will be back soon. " +
                "The door to the attic was repaired from the last incident and the key open the attic is there in the red drawer in the library room",
                false, property);
        listOfItems.add(item);
        property = new ItemProperty(true, false, true, false, false, false);
        item = new Items("crimsonkey", 0.5, "key", "You have collected the crimson key!!", false, property);
        listOfItems.add(item);
        rooms[2][1] = new Room("living", false, true, true, true, true,
                "You see the package on the table. You head behind to find that the door behind you is locked... "
                        +
                        "You are locked in a one story house with a bed and office, a kitchen, a simple living room, and one locked room."
                        +
                        " Find an alternative exit!",
                listOfItems, "");

        listOfItems = new ArrayList<>();
        property = new ItemProperty(false, false, false, false, true, false);
        item = new Items("knife", 5.0, "object", "The knives are stuck to the block inventory... how odd",
                false, property);
        listOfItems.add(item);
        property = new ItemProperty(false, false, true, false, false, true);
        item = new Items("orange", 0.5, "object", "You have an orange", false, property);
        listOfItems.add(item);
        property = new ItemProperty(false, false, true, false, false, true);
        item = new Items("apple", 0.5, "object", "You have an apple", false, property);
        listOfItems.add(item);
        rooms[2][2] = new Room("kitchen", false, false, true, false, true,
                "The kitchen seems to be quite ordinary. You see knives on a block." +
                        "There are some fruits to eat",
                listOfItems, "");

        /***********************************************************************************************/

        listOfItems = new ArrayList<>();// no item
        rooms[3][0] = new Room("vacant", false, false, false, false, false,
                "This is a hollow room. You can not navigate here. Oop", listOfItems, "");

        listOfItems = new ArrayList<>();
        property = new ItemProperty(false, false, false, false, true, false);
        item = new Items("redbook", 1.0, "object", "The red book from the library is looked", true, property);
        listOfItems.add(item);
        property = new ItemProperty(true, false, false, false, false, false);
        item = new Items("metalkey", 0.2, "key", "Property of the Alchemist", true, property);
        listOfItems.add(item);
        rooms[3][1] = new Room("library", true, false, true, true, false,
                "This room looks like a library with a hammock laid in the middle. You see an attic door to your east.",
                listOfItems, "redkey");

        listOfItems = new ArrayList<>();// no item
        rooms[3][2] = new Room("attic", true, false, false, false, true,
                "The attic is jammed with antique clocks and cupboards. In the middle there is an arched structure glowing yellow. "
                        +
                        "You enter the room and the glowing light sucks you in. As you see the light getting brighter you realize that you havse to get out here and hurry it up. You have to find the owner of the house or someone that can help you get out.",
                listOfItems, "metalkey");

        /***********************************************************************************************/

        return rooms;
    }

    public Room[][] setUpUpSideDownWorld() {
        rooms = new Room[4][4];
        ArrayList<Items> listOfItems = new ArrayList<>();

        rooms[0][0] = new Room("forest", false, true, false, true, false, "You are in a deep forest",
                listOfItems, "");

        listOfItems = new ArrayList<>();// no item
        rooms[0][1] = new Room("forest", false, true, false, true, true, "You are in a deep forest",
                listOfItems, "");

        listOfItems = new ArrayList<>();
        ItemProperty property = new ItemProperty(false, false, true, false, false, false);
        Items item = new Items("map", 0.2, "drawing",
                "You are in Endevor, the Alchemist’s (who is this alchemist???) world\n [ v ] [ v ] [ v ] [ f ]\n"
                        +
                        "[ v ] [ v ] [ v ] [ f(x) ]\n" +
                        "[ f ] [ f ] [ f ] [ f ]\n" +
                        "[ f ] [ f ] [ c ] [ f ]\n ",
                true, property);
        listOfItems.add(item);

        property = new ItemProperty(false, false, false, false, true, false);
        item = new Items("village", 0.2, "drawing",
                "Under the cliff, You can see a village. It seems to be a world on its own. The air is chill and the houses seem contrary to your modern ones. You feel like you've travelled backwards in time. "
                        +
                        "Upon closer examination, you notice that the people and houses there seem to be covered in a thin layer of ice."
                        +
                        " In the middle of this spectle, you see a long townhall and lights glowing all around it.",
                false, property);
        listOfItems.add(item);
        rooms[0][2] = new Room("cliff", false, true, false, true, true,
                "You seem to have landed on top of a cliff. This place is surrounded with a dense forest. This seems familiar.",
                listOfItems, "");

        listOfItems = new ArrayList<>();// no item
        rooms[0][3] = new Room("forest", false, true, false, false, true, "You are in a deep forest",
                listOfItems, "");

        /*********************************************************************************************/
        listOfItems = new ArrayList<>();// no item
        rooms[1][0] = new Room("forest", false, true, true, true, false, "You are in a deep forest",
                listOfItems, "");

        listOfItems = new ArrayList<>();
        rooms[1][1] = new Room("forest", false, true, true, true, true, "You are in a deep forest", listOfItems,
                "");

        listOfItems = new ArrayList<>();// no item
        rooms[1][2] = new Room("forest", false, true, true, true, true, "You are in a deep forest", listOfItems,
                "");

        listOfItems = new ArrayList<>();// no item
        rooms[1][3] = new Room("forest", false, true, true, false, true, "You are in a deep forest",
                listOfItems, "");

        /***********************************************************************************************/

        rooms[2][0] = new Room("village", false, true, true, true, false,
                "You are in a village. It's very silent.", listOfItems, "");

        listOfItems = new ArrayList<>();// no item
        rooms[2][1] = new Room("townhouse", true, true, true, true, true,
                "You drop down to a hallway. To the East and West are rooms. These rooms are melded to the frame. They look like they can not be entered. The only way you can go is north...",
                listOfItems, "");

        listOfItems = new ArrayList<>();// no item
        rooms[2][2] = new Room("village", false, true, true, true, true,
                "Its very quiet here. The people don't talk. They walk along the town's dimly lit passages almost like they are in a trans.",
                listOfItems, "");

        listOfItems = new ArrayList<>();
        property = new ItemProperty(true, false, false, false, false, false);
        item = new Items("ladder", 30.0, "object", "You can use this to climb a certain sort of house!", true,
                property);
        listOfItems.add(item);
        rooms[2][3] = new Room("forest", false, true, true, false, true, "You are in a deep forest",
                listOfItems, "");

        /***********************************************************************************************/

        rooms[3][0] = new Room("village", false, false, true, true, false, "You are in a deep forest",
                listOfItems, "");

        listOfItems = new ArrayList<>();// no item
        property = new ItemProperty(false, false, false, true, false, false);
        item = new Items("old man", 50.0, "human", "You talk to the old man.", true, property);
        listOfItems.add(item);
        rooms[3][1] = new Room("townhouse", true, false, true, true, true,
                "You look around you in curiousity. The walls are lined in what looks like gold. To your surprise, you see the old man smile. This is the first time in a while that you've seen a smile on someone. He seems more human than the others in the village.",
                listOfItems, "");

        listOfItems = new ArrayList<>();// no item
        rooms[3][2] = new Room("village", false, false, true, true, true, "You are in a deep forest",
                listOfItems, "");

        listOfItems = new ArrayList<>();
        rooms[3][3] = new Room("forest", false, false, true, false, true, "You are in a deep forest",
                listOfItems, "");

        return rooms;
    }
}
