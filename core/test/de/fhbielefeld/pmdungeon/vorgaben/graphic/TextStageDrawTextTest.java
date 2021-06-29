package de.fhbielefeld.pmdungeon.vorgaben.graphic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.vorgaben.tools.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class TextStageDrawTextTest {

    private String text;
    private String fontPath;
    private Color color;
    private int size;
    private int width;
    private int height;
    private int x;
    private int y;

    public TextStageDrawTextTest(String text, String fontPath, Color color, int size, int width, int height, int x, int y) {
        this.text=text;
        this.fontPath=fontPath;
        this.color=color;
        this.size=size;
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
    }

    @Parameterized.Parameters
    public static Collection cords() {
        String path="bitmapfont/Amble-Regular-26.fnt"; //BRAUCHT SINNVOLLEN PFAD
        return Arrays.asList(new Object[][] {
                { "text", path, Color.GOLD, 10,10,10,10,10 },
                { "text", "not existing path", Color.GOLD, 10,10,10,10,10 },
                { null, path, Color.GOLD, 10,10,10,10,10 },
                { "text", null, Color.GOLD, 10,10,10,10,10 },
                { "text", path, null, 10,10,10,10,10 },
                { "text", path, Color.GOLD, -1,10,10,10,10 },
                { "text", path, Color.GOLD, 10,-1,10,10,10 },
                { "text", path, Color.GOLD, 10,10,-1,10,10 },
                { "text", path, Color.GOLD, 10,10,10,-1,10 },
                { "text", path, Color.GOLD, 10,10,10,10,-1 }

        });
    }

    //ID=20
    @Test(expected=NullPointerException.class)
    public void testDraw() {
        TextStage textStage=new  TextStage(mock(SpriteBatch.class));

    }
}
