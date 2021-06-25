package de.fhbielefeld.pmdungeon.vorgaben.tools;

import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.dungeonconverter.Coordinate;
import org.junit.Test;

public class PointExceptionTests {

    @Test(expected=NullPointerException.class)
    public void testCreateWithCoordinateNull() {
        Coordinate coordinate = null;
        Point point = new Point(coordinate);
    }

    @Test(expected=NullPointerException.class)
    public void testCreateWithPointNull() {
        Point otherPoint = null;
        Point point = new Point(otherPoint);
    }
}
