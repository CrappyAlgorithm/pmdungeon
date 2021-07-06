package de.fhbielefeld.pmdungeon.vorgaben.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.game.GameSetup;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class IDrawableTest {

    @Mock
    Texture texture;
    @Mock
    SpriteBatch spriteBatch;

    IDrawable iDrawable;

    @Before
    public void init_mocks() throws Exception {
        MockitoAnnotations.openMocks(this).close();

        iDrawable = new IDrawable() {
            @Override
            public Point getPosition() {
                return new Point(0, 0);
            }

            @Override
            public Texture getTexture() {
                return texture;
            }
        };
    }

    // ID 38.1
    @Test
    public void testDrawWithFourParameter() {
        try (MockedStatic<GameSetup> mGameSetup = mockStatic(GameSetup.class)) {
            GameSetup.batch = spriteBatch;
            iDrawable.draw(1.1f, 1.2f, 1.3f, 1.4f);
        }
        verify(spriteBatch).begin();
        verify(spriteBatch).draw(any(Texture.class), any(float[].class), anyInt(), anyInt());
        verify(spriteBatch).end();
    }

    // ID 38.2
    @Test
    public void testDrawWithoutParameter() {
        try (MockedStatic<GameSetup> mGameSetup = mockStatic(GameSetup.class)) {
            GameSetup.batch = spriteBatch;
            iDrawable.draw();
        }
        verify(spriteBatch).begin();
        verify(spriteBatch).draw(any(Texture.class), any(float[].class), anyInt(), anyInt());
        verify(spriteBatch).end();
    }

    // ID 38.3
    @Test
    public void testDrawWithTwoParameter() {
        try (MockedStatic<GameSetup> mGameSetup = mockStatic(GameSetup.class)) {
            GameSetup.batch = spriteBatch;
            iDrawable.draw(1.1f, 1.2f);
        }
        verify(spriteBatch).begin();
        verify(spriteBatch).draw(any(Texture.class), any(float[].class), anyInt(), anyInt());
        verify(spriteBatch).end();
    }

    // ID 38.4
    @Test
    public void testDrawWithScalings() {
        try (MockedStatic<GameSetup> mGameSetup = mockStatic(GameSetup.class)) {
            GameSetup.batch = spriteBatch;
            iDrawable.drawWithScaling(1.1f, 1.2f);
        }
        verify(spriteBatch).begin();
        verify(spriteBatch).draw(any(Texture.class), any(float[].class), anyInt(), anyInt());
        verify(spriteBatch).end();
    }
}
