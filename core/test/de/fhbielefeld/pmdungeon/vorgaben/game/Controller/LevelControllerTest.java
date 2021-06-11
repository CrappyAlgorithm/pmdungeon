package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;

import com.sun.tools.javac.Main;
import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.DungeonWorld;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.Mockito.*;


public class LevelControllerTest {

    @Test
    public void testLoadDungeon() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MainController mc =mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);

        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc,null);

        lc.loadDungeon(mdung);
        verify(mc,times(1)).onLevelLoad();
        verify(mdung,times(1)).makeConnections();

    }
}
