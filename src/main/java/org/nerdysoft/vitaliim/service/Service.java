package org.nerdysoft.vitaliim.service;

import org.nerdysoft.vitaliim.data.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.ListIterator;

@org.springframework.stereotype.Service
public class Service {

    private final List<Coordinate<Integer, Integer>> coordinates;
    private boolean direction = true;
    private String info="Not enough walls!";

    public Service() {
        this.coordinates = new ArrayList<Coordinate<Integer, Integer>>();
    }

    public void addCoordinate(Coordinate<Integer, Integer> coordinatePair){
        // Check for identical coordinatePair in list, if exists - print error
        ListIterator<Coordinate<Integer,Integer>> iterator = coordinates.listIterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(coordinatePair)){
                System.out.println("INVALID COORDINATES FOR X:" + coordinatePair.getX() + "| Y:" + coordinatePair.getY() + "! CAUSE: COORDINATES ALREADY ENTERED");
                info = "Identical coordinates!";
                return;
            }
        }

        // First coordinatePair can have any number combination
        if(coordinates.isEmpty()){
            coordinates.add(coordinatePair);
            System.out.println("ADDED FIRST COORDINATES X:" + coordinatePair.getX() + " | Y:" + coordinatePair.getY());
            return;
        }

        // Any other coordinatePair other than the first one can differ from the previous only by -+const OR X OR Y,
        // otherwise diagonal walls will be built
        // If coordinates differ with a number>1 than any intermediate coordinates will be added automatically in order to prevent intersection



        if(!coordinates.isEmpty()) {
            Coordinate<Integer, Integer> lastCoordinate = coordinates.get(coordinates.size() - 1);
            System.out.println(lastCoordinate);
            System.out.println(coordinatePair);

            // Checking whether the Last Coordinate is on the same Y as the First Coordinate, but has different X, so that the wall can be build
            boolean checkForX = (!lastCoordinate.getX().equals(coordinatePair.getX())) && lastCoordinate.getY().equals(coordinatePair.getY());

            // Checking whether the Last Coordinate is on the same X as the First Coordinate, but has different Y, so that the wall can be build
            boolean checkForY = (!lastCoordinate.getY().equals(coordinatePair.getY())) && lastCoordinate.getX().equals(coordinatePair.getX());

            System.out.println(checkForDirection(lastCoordinate, coordinatePair));

            if (checkForWall(lastCoordinate, coordinatePair)) {

                if (checkForX) {

                    // Adding every intermediate coordinate between lastCoordinate X and new CoordinatePair X
                    if (lastCoordinate.getX() < coordinatePair.getX()) {
                        for (int i = lastCoordinate.getX() + 1; i <= coordinatePair.getX(); i++) {
                            coordinates.add(new Coordinate<Integer, Integer>(i, coordinatePair.getY()));
                        }
                    } else {
                        for (int i = lastCoordinate.getX() - 1; i >= coordinatePair.getX(); i--) {
                            coordinates.add(new Coordinate<Integer, Integer>(i, coordinatePair.getY()));
                        }
                    }


                } else if (checkForY) {

                    // Adding every intermediate coordinate between lastCoordinate Y and new CoordinatePair Y
                    if (lastCoordinate.getY() < coordinatePair.getY()) {
                        for (int i = lastCoordinate.getY() + 1; i <= coordinatePair.getY(); i++) {
                            coordinates.add(new Coordinate<Integer, Integer>(coordinatePair.getX(), i));
                        }
                    } else {
                        for (int i = lastCoordinate.getY() - 1; i >= coordinatePair.getY(); i--) {
                            coordinates.add(new Coordinate<Integer, Integer>(coordinatePair.getX(), i));
                        }
                    }
                } else {
                    System.out.println("INVALID COORDINATES FOR X:" + coordinatePair.getX() + " | Y:" + coordinatePair.getY() + "! CAUSE: DIAGONAL WALLS");
                    info="Diagonal Walls!";
                }

            }
        }
    }



    private boolean checkForWall(Coordinate<Integer, Integer> firstCoordinate, Coordinate<Integer, Integer> lastCoordinate) {

        // Checking whether the Last Coordinate is on the same Y as the First Coordinate, but has different X, so that the wall can be build
        boolean checkForX = !lastCoordinate.getX().equals(firstCoordinate.getX()) && lastCoordinate.getY().equals(firstCoordinate.getY());

        // Checking whether the Last Coordinate is on the same X as the First Coordinate, but has different Y, so that the wall can be build
        boolean checkForY = !lastCoordinate.getY().equals(firstCoordinate.getY()) && lastCoordinate.getX().equals(firstCoordinate.getX());

        // Checking whether the Last Coordinate and First Coordinate have both X and Y different, so that wall can not be built
        boolean differentCoordinate = !lastCoordinate.getY().equals(firstCoordinate.getY()) && !lastCoordinate.getX().equals(firstCoordinate.getX());

        ListIterator<Coordinate<Integer, Integer>> listIterator = coordinates.listIterator();

        if (checkForX) {

            // Checking for intermediate X coordinates between First Coordinate and Last One to verify that there is no wall intersection

            if (lastCoordinate.getX() < firstCoordinate.getX()) {
                for (int i = lastCoordinate.getX() + 1; i < firstCoordinate.getX(); i++) {
                    while (listIterator.hasNext()) {
                        if (listIterator.next().equals(new Coordinate<Integer, Integer>(i, firstCoordinate.getY()))) {
                            System.out.println("WALL INTERSECTION");
                            info = "Wall Intersection!";
                            return false;
                        }
                    }
                }
            } else {
                for (int i = firstCoordinate.getX() + 1; i < lastCoordinate.getX(); i++) {
                    while (listIterator.hasNext()) {
                        if (listIterator.next().equals(new Coordinate<Integer, Integer>(i, firstCoordinate.getY()))) {
                            System.out.println("WALL INTERSECTION");
                            info = "Wall Intersection!";
                            return false;
                        }
                    }
                }
            }

        }else if (checkForY){

                // Checking for intermediate Y coordinates between First Coordinate and Last One to verify that there is no wall intersection

                if (lastCoordinate.getY() < firstCoordinate.getY()) {
                    for (int i = lastCoordinate.getY() + 1; i < firstCoordinate.getY(); i++) {
                        while (listIterator.hasNext()) {
                            if (listIterator.next().equals(new Coordinate<Integer, Integer>(firstCoordinate.getX(), i))) {
                                System.out.println("WALL INTERSECTION");
                                info = "Wall Intersection!";
                                return false;
                            }
                        }
                    }
                } else {
                    for (int i = firstCoordinate.getY() + 1; i < lastCoordinate.getY(); i++) {
                        while (listIterator.hasNext()) {
                            if (listIterator.next().equals(new Coordinate<Integer, Integer>(firstCoordinate.getX(), i))) {
                                System.out.println("WALL INTERSECTION");
                                info = "Wall Intersection!";
                                return false;
                            }
                        }
                    }
                }
        }else if(differentCoordinate) {
            System.out.println("INVALID COORDINATES FOR X:" + lastCoordinate.getX() + " | Y:" + lastCoordinate.getY() + "! CAUSE: WALL WILL BE DIAGONAL");
            info="Diagonal Walls!";
            return false;
        }
        return true;
    }

    private boolean checkForDirection(Coordinate<Integer, Integer> firstCoordinate, Coordinate<Integer, Integer> secondCoordinate){
        if(!firstCoordinate.equals(coordinates.get(0))) {
            Coordinate<Integer, Integer> previousCoordinate = coordinates.get(coordinates.indexOf(firstCoordinate) - 1);
            if((previousCoordinate.getY() < firstCoordinate.getY()) && (firstCoordinate.getX() > secondCoordinate.getX())){
                direction = false;
                info="Counterclockwise direction!";
            }else if((previousCoordinate.getY() > firstCoordinate.getY()) && (firstCoordinate.getX() < secondCoordinate.getX())){
                direction = false;
                info="Counterclockwise direction!";
            }else if((previousCoordinate.getX() < firstCoordinate.getX()) && (firstCoordinate.getY() < secondCoordinate.getY())){
                direction = false;
                info="Counterclockwise direction!";
            }else if((previousCoordinate.getX() > firstCoordinate.getX()) && (firstCoordinate.getY() > secondCoordinate.getY())){
                direction = false;
                info="Counterclockwise direction!";
            }else{
                direction = true;
            }
        }
        return direction;
    }

    public boolean buildRoom(){

        boolean valid = false;
        String result = "Room can not be built. Please check for diagonal walls or counterclockwise direction!";

        if(direction && coordinates.size() > 3){
            Coordinate<Integer, Integer> startCoordinate = coordinates.get(0);
            Coordinate<Integer, Integer> endCoordinate = coordinates.get(coordinates.size()-1);

            if(checkForWall(startCoordinate, endCoordinate)){
                result = "Room can be built! Press Submit to proceed.";
                valid = true;
            }
        }

        System.out.println(result);
        return valid;
    }

    public void cleanUp(){
        coordinates.removeAll(coordinates);
    }

    public List<Coordinate<Integer, Integer>> getCoordinates(){
        return Collections.unmodifiableList(coordinates);
    }

    public void printCoordinates(){
        int count = 1;
        ListIterator<Coordinate<Integer, Integer>> iterator = coordinates.listIterator();

        while(iterator.hasNext()){
            System.out.println(count + ". " + iterator.next());
            count++;
        }
    }

    public String getInfo(){
        return info;
    }

}
