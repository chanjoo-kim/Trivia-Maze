package Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serial;
import java.io.Serializable;

public class Door extends MazeStructure implements Serializable {

    @Serial
    private static final long serialVersionUID = 7974737325075828765L;

    private final transient BooleanProperty myIsOpen;
    private final transient BooleanProperty myIsLocked;
    private final transient BooleanProperty myIsSelected;
    private Question myQuestion;

    Door(final Question theQuestion) {
        myIsLocked = new SimpleBooleanProperty(false);
        myIsOpen = new SimpleBooleanProperty(false);
        myIsSelected = new SimpleBooleanProperty(false);
        myQuestion = theQuestion;
        setIsPassable(false);
    }

    public Question getQuestion() {
        return myQuestion;
    }

    public BooleanProperty getIsOpenAsProperty() {
        return myIsOpen;
    }

    public BooleanProperty getIsLockedAsProperty() {
        return myIsLocked;
    }

    public BooleanProperty getIsSelectedAsProperty() {
        return myIsSelected;
    }

    public void setIsOpen(final boolean theNewOpenBool) {
        if(theNewOpenBool) {
            setIsPassable(true);
        }
        myIsOpen.setValue(theNewOpenBool);
    }

    public void setIsLocked(final boolean theNewLockedBool) {
        myIsLocked.setValue(theNewLockedBool);
    }

    public void setIsSelected(final boolean theNewSelectedBool) {
        myIsSelected.setValue(theNewSelectedBool);
    }

}
