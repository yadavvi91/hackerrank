package org.yadavvi.crossword;

/**
 * Created by vishal on 6/7/17.
 */
public class Horizontal extends Places {
    int start;
    int end;
    int row;

    public Horizontal(int start, int end, int row) {
        super(end - start + 1);
        this.start = start;
        this.end = end;
        this.row = row;
    }

    @Override
    public String toString() {
        return "[" + row + "][" + start + "]-[" + row + "][" + end + "]";
    }
}
