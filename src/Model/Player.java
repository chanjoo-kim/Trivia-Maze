package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;

public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 4859347544175785067L;

    private static final Player mySinglePlayerInstance = new Player();

    private transient IntegerProperty myXPos;
    private transient IntegerProperty myYPos;

    private Player() {

        myXPos = new SimpleIntegerProperty(1);
        myYPos = new SimpleIntegerProperty(1);
    }

    public static Player getInstance() {
        return  mySinglePlayerInstance;
    }

    public int getXPosAsInt() {
         return myXPos.get();
    }

    public IntegerProperty getXPosAsIntProperty() {
        return myXPos;
    }

    public int getYPosAsInt() {
        return myYPos.get();
    }

    public IntegerProperty getYPosAsIntProperty() {
        return myYPos;
    }

    public void movePlayerNorth() {
        myYPos.set(myYPos.get() - 1);
    }

    public void movePlayerSouth() {
        myYPos.set(myYPos.get() + 1);
    }

    public void movePlayerWest() {
        myXPos.set(myXPos.get() - 1);
    }

    public void movePlayerEast() {
        myXPos.set(myXPos.get() + 1);
    }

    @Serial
    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.defaultWriteObject();
        out.writeObject(getXPosAsInt());
        out.writeObject(getYPosAsInt());
    }

    @Serial
    private void readObject(ObjectInputStream in)
            throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        getInstance().myXPos.set((int) in.readObject());
        getInstance().myYPos.set((int) in.readObject());
    }

    @Serial
    private Object readResolve() {
        return getInstance();
    }
}
