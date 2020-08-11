package org.nerdysoft.vitaliim;


import org.nerdysoft.vitaliim.data.Coordinate;
import org.nerdysoft.vitaliim.service.Service;

public class Main {

    public static void main(String[] args) {
        Coordinate<Integer, Integer> pair1 = new Coordinate<Integer, Integer>(1,-4);
        Coordinate<Integer, Integer> pair2 = new Coordinate<Integer, Integer>(1,-3);
        Coordinate<Integer, Integer> pair3 = new Coordinate<Integer, Integer>(1,-2);
        Coordinate<Integer, Integer> pair4 = new Coordinate<Integer, Integer>(1,-1);
        Service service = new Service();
        service.addCoordinate(pair1);
        service.addCoordinate(pair2);
        service.addCoordinate(pair3);
        service.addCoordinate(pair4);
        service.printCoordinates();
        service.buildRoom();
    }

}
