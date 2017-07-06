package org.yadavvi.crossword;

/**
 * Created by vishal on 6/7/17.
 */
public class Vertical extends Places {
    int start;
    int end;
    int column;

    public Vertical(int start, int end, int column) {
        super(end - start);
        this.start = start;
        this.end = end;
        this.column = column;
    }

    @Override
    public String toString() {
        return "[" + start + "][" + column + "]-[" + end + "][" + column + "]";
    }

}
