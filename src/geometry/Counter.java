package geometry;

/**
 * @author Ohad Marmor - 207481524.
 */
public class Counter {
    // field:
    private int counter;

    /**
     * constructor.
     * @param c - an integer.
     */
    public Counter(int c) {
        this.counter = c;
    }
    /**
     * add number to current count.
     * @param number - an integer.
     */
    public void increase(int number) {
        this.counter += number;
    }
    /**
     * subtract number from current count.
     * @param number - an integer.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * accessor.
     * @return - the value of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}
