import java.util.*;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class Main {
    public static void main(String[] args) {
        StartGame strGame = new StartGame();
        strGame.printWelcomeMessage();

        strGame.commands.processStep();
    }

    //run();
}