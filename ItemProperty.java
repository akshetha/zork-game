import java.util.*;

public class ItemProperty {
    private boolean isCollectable;

    private boolean isReadable;

    private boolean isUsable;

    private boolean isFood;

    private boolean talkable;

    private boolean lookable;

    public ItemProperty(boolean isCollectable, boolean isReadable, boolean isUsable, boolean talkable, boolean lookable, boolean isFood) {
        this.isCollectable = isCollectable;
        this.isReadable = isReadable;
        this.isUsable = isUsable;
        this.talkable = talkable;
        this.lookable = lookable;
        this.isFood = isFood;
    }

    public boolean isReadable() {
        return isReadable;
    }

    public boolean isCollectable() {
        return isCollectable;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public boolean isTalkable() {
        return talkable;
    }

    public boolean isLookable() {
        return lookable;
    }

    public boolean isFood() {
        return isFood;
    }
}
