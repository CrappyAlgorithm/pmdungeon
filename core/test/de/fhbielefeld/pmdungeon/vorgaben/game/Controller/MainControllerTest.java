package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.game.GameSetup;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.HUD;
import de.fhbielefeld.pmdungeon.vorgaben.graphic.TextStage;
import de.fhbielefeld.pmdungeon.vorgaben.tools.DungeonCamera;
import org.junit.Test;
import org.mockito.MockedStatic;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static org.mockito.Mockito.*;


public class MainControllerTest {


    @Test
    public void testRender() {
        MainController mMainController = mock(MainController.class);
        LevelController mLevelController = mock(LevelController.class);
        EntityController mEntityController = mock(EntityController.class);
        DungeonCamera mDungeonCamera = mock(DungeonCamera.class);
        HUD mHUD = mock(HUD.class);
        TextStage mTextStage = mock(TextStage.class);

        mMainController.levelController = mLevelController;
        mMainController.entityController = mEntityController;
        mMainController.camera = mDungeonCamera;
        mMainController.hud = mHUD;
        mMainController.textHUD = mTextStage;
        mMainController.firstFrame = false;
        doCallRealMethod().when(mMainController).render(anyFloat());

        GL20 mGL = mock(GL20.class);
        SpriteBatch mSpriteBatch = mock(SpriteBatch.class);

        try (MockedStatic<Gdx> mGdx = mockStatic(Gdx.class);
             MockedStatic<GameSetup> mGameSetup = mockStatic(GameSetup.class)
        ) {
            Gdx.gl = mGL;
            GameSetup.batch = mSpriteBatch;
            mMainController.render(1.0F);
        }

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
}
