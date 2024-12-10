//Omer Mandalaoui 211695598
package listeners;
/**
 * The Counter class is used to keep track of a count. It provides methods to
 * increase, decrease, and retrieve the current count.
 */
public class Counter {
    private int counterListener = 0;
    /**
     * Adds the specified number to the current count.
     *
     * @param number the number to add to the current count
     */
    public void increase(int number) {
        this.counterListener += number;
    }
    /**
     * Subtracts the specified number from the current count.
     *
     * @param number the number to subtract from the current count
     */
    public void decrease(int number) {
        this.counterListener -= number;
    }
    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.counterListener;
    }
}

