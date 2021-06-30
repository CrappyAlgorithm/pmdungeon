package de.fhbielefeld.pmdungeon.vorgaben.graphic;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.interfaces.IHUDElement;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Test;

import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class TextStageTest {

    //ID=19.1
    @Test(expected = NullPointerException.class)
    public void testCreateWithbachNotNull() {
        TextStage textStage = new TextStage(mock(SpriteBatch.class));
    }


    //ID=19.2
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithbachNull() {
        TextStage textStage = new TextStage(null);
    }

}
