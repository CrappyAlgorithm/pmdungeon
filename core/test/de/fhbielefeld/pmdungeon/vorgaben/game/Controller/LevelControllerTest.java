package de.fhbielefeld.pmdungeon.vorgaben.game.Controller;

import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.DungeonWorld;
import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.dungeonconverter.DungeonConverter;
import de.fhbielefeld.pmdungeon.vorgaben.dungeonCreator.tiles.Tile;
import de.fhbielefeld.pmdungeon.vorgaben.game.GameSetup;
import de.fhbielefeld.pmdungeon.vorgaben.testutil.ObjectManipulator;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Constants;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
public class LevelControllerTest {

    // ID 7.1
    @Test
    public void testConstructor() throws NoSuchMethodException {
        MainController mc = mock(MainController.class);
        Method onLevelLoad = mc.getClass().getMethod("onLevelLoad");
        Object[] args = {};
        LevelController lc = new LevelController(onLevelLoad, mc, args);
        assertEquals(mc, lc.klass);
        assertEquals(onLevelLoad, lc.onLevelLoad);
        assertEquals(args, lc.args);
    }

    // ID 8.1
    @Test
    public void testLoadDungeon() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);

        lc.loadDungeon(mdung);
        verify(mc, times(1)).onLevelLoad();
        verify(mdung, times(1)).makeConnections();
    }

    // ID 8.2
    @Test(expected = NullPointerException.class)
    public void testLoadDungeonDungeonNull() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        MainController mc = mock(MainController.class);
        Object[] args = {};
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);

        lc.loadDungeon(null);
    }

    // ID 8.3
    @Test(expected = NullPointerException.class)
    public void testLoadDungeonKlassIsNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), null, args);

        lc.loadDungeon(mdung);
        verify(mc, times(1)).onLevelLoad();
        verify(mdung, times(1)).makeConnections();
    }

    // ID 8.4
    @Test
    public void testLoadDungeonArgsIsNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);

        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, null);

        lc.loadDungeon(mdung);
        verify(mc, times(1)).onLevelLoad();
        verify(mdung, times(1)).makeConnections();
    }

    // ID 8.5
    @Test(expected = IllegalArgumentException.class)
    public void testLoadDungeonKlassIsNotMainController() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), new Object(), args);

        lc.loadDungeon(mdung);
        verify(mc, times(1)).onLevelLoad();
        verify(mdung, times(1)).makeConnections();
    }

    // ID 8.6
    @Test(expected = NullPointerException.class)
    public void testLoadDungeonMethodNull() throws InvocationTargetException, IllegalAccessException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        LevelController lc = new LevelController(null, mc, args);

        lc.loadDungeon(mdung);
    }

    // ID 9.1
    @Test
    public void testUpdate() throws InvocationTargetException, IllegalAccessException {
        LevelController mlc = mock(LevelController.class);
        mlc.nextLevelTriggered = true;
        doCallRealMethod().when(mlc).update();

        mlc.update();
        verify(mlc).nextStage();
        verify(mlc).draw();
    }

    // ID 9.2
    @Test
    public void testUpdateNextLevelTriggeredFalse() throws InvocationTargetException, IllegalAccessException {
        LevelController mlc = mock(LevelController.class);
        mlc.nextLevelTriggered = false;
        doCallRealMethod().when(mlc).update();

        mlc.update();
        verify(mlc, times(0)).nextStage();
        verify(mlc).draw();
    }

    // ID 10.1
    @Test
    public void testCheckForTrigger() throws NoSuchMethodException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        Point p = new Point(2, 5);
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);

        when(mdung.getNextLevelTrigger()).thenReturn(new Tile(Tile.Type.EMPTY, (int) p.x, (int) p.y));
        lc.dungeonWorld = mdung;

        assertEquals(true, lc.checkForTrigger(p));
    }

    // ID 10.2
    @Test
    public void testCheckForTriggerXNotSame() throws NoSuchMethodException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        Point p = new Point(2, 5);
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);

        when(mdung.getNextLevelTrigger()).thenReturn(new Tile(Tile.Type.EMPTY, 4, (int) p.y));
        lc.dungeonWorld = mdung;

        assertEquals(false, lc.checkForTrigger(p));
    }

    // ID 10.3
    @Test
    public void testCheckForTriggerYNotSame() throws NoSuchMethodException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        Point p = new Point(2, 5);
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);

        when(mdung.getNextLevelTrigger()).thenReturn(new Tile(Tile.Type.EMPTY, (int) p.x, 10));
        lc.dungeonWorld = mdung;

        assertEquals(false, lc.checkForTrigger(p));
    }

    // ID 11.1
    @Test
    public void testTriggerNextStage() throws NoSuchMethodException {
        MainController mc = mock(MainController.class);
        Object[] args = {};
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);
        assertEquals(false, lc.nextLevelTriggered);
        lc.triggerNextStage();
        assertEquals(true, lc.nextLevelTriggered);
    }

    // ID 12.1
    @Test
    public void testDraw() throws NoSuchMethodException {
        MainController mc = mock(MainController.class);
        DungeonWorld mdung = mock(DungeonWorld.class);
        Object[] args = {};
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);
        lc.dungeonWorld = mdung;
        when(mdung.getHeight()).thenReturn(1);

        lc.draw();
        verify(mdung).renderFloor(GameSetup.batch);
        verify(mdung).renderWalls(0, 0, GameSetup.batch);
    }

    // ID 12.2
    @Test
    public void testDrawDungeonWorldIsNull() throws NoSuchMethodException {
        MainController mc = mock(MainController.class);
        Object[] args = {};
        LevelController lc = new LevelController(mc.getClass().getMethod("onLevelLoad"), mc, args);
        lc.dungeonWorld = null;
        lc.draw();
    }

    // ID 13.1
    @Test
    public void testNextStageAToB() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        LevelController mlc = mock(LevelController.class);
        DungeonConverter dungeonConverter = mock(DungeonConverter.class);
        ObjectManipulator.overrrideFinalAttribute(mlc, "dungeonConverter", dungeonConverter);
        doCallRealMethod().when(mlc).nextStage();
        mlc.nextStage = LevelController.Stage.A;
        mlc.nextStage();
        assertEquals(LevelController.Stage.B, mlc.nextStage);
        verify(dungeonConverter).dungeonFromJson(Constants.PATHTOLEVEL + "small_dungeon.json");
        verify(mlc).loadDungeon(any());
    }

    // ID 13.2
    @Test
    public void testNextStageBToC() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        LevelController mlc = mock(LevelController.class);
        DungeonConverter dungeonConverter = mock(DungeonConverter.class);
        ObjectManipulator.overrrideFinalAttribute(mlc, "dungeonConverter", dungeonConverter);
        doCallRealMethod().when(mlc).nextStage();
        mlc.nextStage = LevelController.Stage.B;
        mlc.nextStage();
        assertEquals(LevelController.Stage.C, mlc.nextStage);
        verify(dungeonConverter).dungeonFromJson(Constants.PATHTOLEVEL + "simple_dungeon_2.json");
        verify(mlc).loadDungeon(any());
    }

    // ID 13.3
    @Test
    public void testNextStageCToD() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        LevelController mlc = mock(LevelController.class);
        DungeonConverter dungeonConverter = mock(DungeonConverter.class);
        ObjectManipulator.overrrideFinalAttribute(mlc, "dungeonConverter", dungeonConverter);
        doCallRealMethod().when(mlc).nextStage();
        mlc.nextStage = LevelController.Stage.C;
        mlc.nextStage();
        assertEquals(LevelController.Stage.D, mlc.nextStage);
        verify(dungeonConverter).dungeonFromJson(Constants.PATHTOLEVEL + "simple_dungeon.json");
        verify(mlc).loadDungeon(any());
    }

    // ID 13.4
    @Test
    public void testNextStageDToA() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        LevelController mlc = mock(LevelController.class);
        DungeonConverter dungeonConverter = mock(DungeonConverter.class);
        ObjectManipulator.overrrideFinalAttribute(mlc, "dungeonConverter", dungeonConverter);
        doCallRealMethod().when(mlc).nextStage();
        mlc.nextStage = LevelController.Stage.D;
        mlc.nextStage();
        assertEquals(LevelController.Stage.A, mlc.nextStage);
        verify(dungeonConverter).dungeonFromJson(Constants.PATHTOLEVEL + "boss_dungeon.json");
        verify(mlc).loadDungeon(any());
    }
}
