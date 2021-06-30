package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.dungeonconverter.DungeonConverter;
import de.fhbielefeld.pmdungeon.vorgaben.game.GameSetup;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.HUD;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.TextStage;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Constants;
import de.fhbielefeld.pmdungeon.vorgaben.tools.DungeonCamera;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.InvocationTargetException;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class MainControllerTest {

    @Mock LevelController mLevelController;
    @Mock EntityController mEntityController;
    @Mock DungeonCamera mDungeonCamera;
    @Mock HUD mHUD;
    @Mock TextStage mTextStage;
    @Mock GL20 mGL;
    @Mock SpriteBatch mSpriteBatch;
    @Mock Application app;
    @InjectMocks MainController mMainController = mock(MainController.class);
    @InjectMocks MainController mainController = new MainController();

    @Before
    public void init_mocks() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    // ID=15.1
    @Test(expected = NullPointerException.class)
    public void testFirstFrameFinishedSetupTrue() throws InvocationTargetException, IllegalAccessException {
        mMainController.finishedSetup = true;
        mMainController.firstFrame();
        doCallRealMethod().when(mMainController).firstFrame();
        mMainController.firstFrame();

        verify(mLevelController).loadDungeon(any());
        assertEquals(false, mMainController.firstFrame);
    }

    // ID=15.2
    @Test(expected = UnsatisfiedLinkError.class)
    public void testFirstFrameFinishedSetupFalse() throws InvocationTargetException, IllegalAccessException {
        mMainController.finishedSetup = false;
        mMainController.firstFrame();
        doCallRealMethod().when(mMainController).firstFrame();
        Gdx.app = app;

        mMainController.firstFrame();
        assertEquals(true, mMainController.finishedSetup);
        assertEquals(false, mMainController.firstFrame);
    }

    // ID=16.1
    @Test
    public void testRenderFirstFrameTrue() throws InvocationTargetException, IllegalAccessException {
        mMainController.firstFrame = true;
        doCallRealMethod().when(mMainController).render(anyFloat());
        doNothing().when(mMainController).firstFrame();

        // mock static classes
        try (MockedStatic<Gdx> mGdx = mockStatic(Gdx.class);
             MockedStatic<GameSetup> mGameSetup = mockStatic(GameSetup.class)
        ) {
            Gdx.gl = mGL;
            GameSetup.batch = mSpriteBatch;
            mMainController.render(1.0F);
        }

        verify(mMainController).firstFrame();
        verify(mGL).glClearColor(0, 0, 0, 1);
        verify(mGL).glClear(GL_COLOR_BUFFER_BIT);
        verify(mSpriteBatch).begin();
        verify(mSpriteBatch).end();
        verify(mSpriteBatch).setProjectionMatrix(mDungeonCamera.combined);
        verify(mLevelController).update();
        verify(mEntityController).update();
        verify(mHUD).draw();
        verify(mTextStage).draw();
    }

    // ID=16.2
    @Test
    public void testRenderFirstFrameFalse() throws InvocationTargetException, IllegalAccessException {
        mMainController.firstFrame = false;
        doCallRealMethod().when(mMainController).render(anyFloat());

        // mock static classes
        try (MockedStatic<Gdx> mGdx = mockStatic(Gdx.class);
             MockedStatic<GameSetup> mGameSetup = mockStatic(GameSetup.class)
        ) {
            Gdx.gl = mGL;
            GameSetup.batch = mSpriteBatch;
            mMainController.render(1.0F);
        }

        verify(mMainController, times(0)).firstFrame();
        verify(mGL).glClearColor(0, 0, 0, 1);
        verify(mGL).glClear(GL_COLOR_BUFFER_BIT);
        verify(mSpriteBatch).begin();
        verify(mSpriteBatch).end();
        verify(mSpriteBatch).setProjectionMatrix(mDungeonCamera.combined);
        verify(mLevelController).update();
        verify(mEntityController).update();
        verify(mHUD).draw();
        verify(mTextStage).draw();
    }

    // ID=17.1
    @Test
    public void testSetupWorldController() {
        assertEquals(mLevelController, mainController.levelController);
        mainController.setupWorldController();
        assertNotEquals(mLevelController, mainController.levelController);
    }

    // ID=18.1
    @Test(expected = UnsatisfiedLinkError.class)
    public void testSetupCamera() {
        assertEquals(mDungeonCamera, mainController.camera);
        mainController.setupCamera();
        assertNotEquals(mDungeonCamera, mainController.camera);
    }
}
