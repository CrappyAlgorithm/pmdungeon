package de.fhbielefeld.pmdungeon.vorgaben.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.game.Controller.MainController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class GameSetupTest {

    @Mock
    MainController mainController;

    @Mock
    SpriteBatch spriteBatch;

    @Mock
    Screen screen;

    @Before
    public void init_mocks() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        GameSetup.batch = spriteBatch;
    }

    // ID 34.1
    @Test
    public void testConstructor() {
        GameSetup gameSetup = new GameSetup(mainController);
        assertEquals(mainController, gameSetup.mc);
    }

    // ID 35.1
    @Test
    public void testConstructorWithNull() {
        GameSetup gameSetup = new GameSetup(null);
        assertEquals(null, gameSetup.mc);
    }

    // ID 36.1
    @Test
    public void testCreate() {
        GameSetup gameSetup = new GameSetup(null); // only testable with mc = null
        try (MockedConstruction<SpriteBatch> mSpriteBatch = mockConstruction(SpriteBatch.class,
                (mock, context) -> {
                    // possible to set additional stubbing
                })) {
            gameSetup.create();
        }
        assertNull(gameSetup.getScreen());
        assertNotEquals(spriteBatch, GameSetup.batch);
    }

    // ID 37.1
    @Test
    public void testDispose() {
        GameSetup gameSetup = new GameSetup(mainController);
        gameSetup.dispose();
        verify(spriteBatch).dispose();
    }
}
