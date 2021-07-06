package de.fhbielefeld.pmdungeon.vorgaben.graphic;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class TextStageTest {

    //ID 19.1
    @Test(expected = NullPointerException.class)
    public void testCreateWithbachNotNull() {
        TextStage textStage = new TextStage(mock(SpriteBatch.class));
    }


    //ID 19.2
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithbachNull() {
        TextStage textStage = new TextStage(null);
    }

}
