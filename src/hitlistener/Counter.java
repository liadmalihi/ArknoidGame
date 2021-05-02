//ID:318948809
package hitlistener;

/**
 * The type Game.Counter.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Game.Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increase.
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        counter = counter + number;
    }

    /**
     * Decrease.
     * subtract number from current count
     *
     * @param number the number
     */

    public void decrease(int number) {
        counter = counter - number;
    }

    /**
     * Get value int.
     * get current count
     *
     * @return the int
     */
    public int getValue() {
        return this.counter;
    }
}