package pw.tales.fairy.featured_block;

public class Pair<T, T1> {
    public T first = null;
    public T1 second = null;

    public Pair(T first, T1 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + first.toString() + "; " + second.toString() + "];";
    }
}
