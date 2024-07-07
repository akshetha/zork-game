import java.util.*;

public class StartGame {
    Commands commands = new Commands();

    public void printWelcomeMessage() {
        Scanner res = new Scanner (System.in);
        System.out.println("Tired from a long long day of work. You, a young dilevery man, reach your final and faarthest destination, a lone house in the middle of the praire.");
        String next1 = res.nextLine();
        System.out.println("You walk up to the front of the house with an oddly shaped box. Standing near the main door, you realize that the dilevery is too large to simply be placed on the poarch.");
        String next2 = res.nextLine();
        System.out.println("You can spot a Welcome Sign Mailbox to your EAST with a note attached...");
    }
}
