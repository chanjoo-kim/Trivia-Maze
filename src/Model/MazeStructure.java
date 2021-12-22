package Model;

public abstract class MazeStructure {

     private boolean myIsPassableBool;

     void setIsPassable(boolean theIsPassableBool) {
          myIsPassableBool = theIsPassableBool;
     }

     public boolean getIsPassable() {
          return myIsPassableBool;
     }
}
