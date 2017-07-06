package org.yadavvi.crossword;

/**
 * Created by vishal on 6/7/17.
 */
public abstract class Places implements Comparable<Places> {
    int length;

    public Places(int length) {
        this.length = length;
    }

    @Override
    public int compareTo(Places o) {
        return this.length - o.length;
    }
}
