package de.fhbielefeld.pmdungeon.vorgaben.graphic;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author gysar.flegel@fh-bielefeld.de
 * @author sebastian.steinmeyer@fh-bielefeld.de
 */
@RunWith(Parameterized.class)
public class TextStageDrawTextTest {

    private final static double DELTA = 0.00000001;

    private TextStage textStage;

    private String text;
    private String fontPath;
    private Color color;
    private int size;
    private int width;
    private int height;
    private int x;
    private int y;

    public TextStageDrawTextTest(String text, String fontPath, Color color, int size, int width, int height, int x, int y) {
        this.text = text;
        this.fontPath = fontPath;
        this.color = color;
        this.size = size;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.textStage = mock(TextStage.class);
        doCallRealMethod().when(this.textStage).drawText(text, fontPath, color, size, width, height, x, y);
    }

    @Parameterized.Parameters
    public static Collection cords() {
        String path = "../assets/Asdonuts.ttf"; //BRAUCHT SINNVOLLEN PFAD
        return Arrays.asList(new Object[][]{
                {"text", path, Color.GOLD, 10, 10, 10, 10, 10},                   // ID=20.1
                //{ "text", "not existing path", Color.GOLD, 10,10,10,10,10 },    // ID=20.2
                //{ null, path, Color.GOLD, 10,10,10,10,10 },
                //{ "text", null, Color.GOLD, 10,10,10,10,10 },
                //{ "text", path, null, 10,10,10,10,10 },
                //{ "text", path, Color.GOLD, -1,10,10,10,10 },
                //{ "text", path, Color.GOLD, 10,-1,10,10,10 },
                //{ "text", path, Color.GOLD, 10,10,-1,10,10 },
                //{ "text", path, Color.GOLD, 10,10,10,-1,10 },
                //{ "text", path, Color.GOLD, 10,10,10,10,-1 }
        });
    }

    //ID=20
//    @Test
    public void testDraw() {
        Files mFiles = mock(Files.class);
        FileHandle fileHandle = new FileHandle(fontPath);
        when(mFiles.internal(fontPath)).thenReturn(fileHandle);
        Gdx.files = mFiles;

        Label label = textStage.drawText(text, fontPath, color, size, width, height, x, y);
        assertEquals(text, label.getText());
        assertEquals(width, label.getWidth(), DELTA);
        assertEquals(height, label.getHeight(), DELTA);
    }

    @Test
    public void placeholder() {
    }
}
