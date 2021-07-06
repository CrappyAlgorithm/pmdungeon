package de.fhbielefeld.pmdungeon.vorgaben.graphic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.junit.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class TextStageTest {

    //ID 19.1
    @Test
    public void testCreateWithbachNotNull() {
        SpriteBatch batch = mock(SpriteBatch.class);
        TextStage textStage;
        try (MockedConstruction<Stage> mStage = mockConstruction(Stage.class)) {
            textStage = new TextStage(batch);
            assertEquals(1, mStage.constructed().size());
        }

    }

    //ID 19.2
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithbachNull() {
        TextStage textStage = new TextStage(null);
    }

}
