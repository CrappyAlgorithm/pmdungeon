package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class EntityControllerTest {


    @Test
    public void setupTest() {
        List mockedList = mock(List.class);
        when(mockedList.get(0)).thenReturn("first");

        assertEquals("first",mockedList.get(0));
    }
}
