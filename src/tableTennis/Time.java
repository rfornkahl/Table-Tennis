package tableTennis;

public class Time {
    public static double timeStarted = System.nanoTime(); //returns systems current time in nanoseconds

    public static double getTime(){
        return (System.nanoTime() - timeStarted) * 1E-9; //how long the game has been running in nanoseconds
        //we use 1E-9 to convert nanoSeconds to regular seconds.
    }
}
