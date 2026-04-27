package ru.samsung.gamestudio;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextView extends View {
    private BitmapFont font;
    private String text;

    public TextView(BitmapFont font, float x, float y) {
        super(x, y);
        this.font = font;
    }

    public TextView(BitmapFont font, float x, float y, String text) {
        this(font, x, y);
            GlyphLayout glyphLayout = new GlyphLayout(font, text);
            width = glyphLayout.width;
            height = glyphLayout.height;
        }

    public void setText(String s)  {
    }
}
