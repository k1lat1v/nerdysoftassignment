package org.nerdysoft.vitaliim.data;

import org.springframework.lang.NonNull;

public class Coordinate<T, U> {

    private Integer x;
    private Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x= x;
        this.y= y;
    }

    public Coordinate(){
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @NonNull
    public void setX(Integer x) {
        this.x = x;
    }

    @NonNull
    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate<Integer, Integer> that = (Coordinate<Integer, Integer>) o;

        // Check for identical coordinates
        if(x.equals(that.x) && y.equals(that.y)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates(X:" + x +" | Y:" + y + ").";
    }
}
