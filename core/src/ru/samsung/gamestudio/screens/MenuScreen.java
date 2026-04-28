package ru.samsung.gamestudio.screens;

import ru.samsung.gamestudio.*;

public class MenuScreen extends ScreenAdapter  {
    MyGdxGame myGdxGame;
    TextView titleView;
    ButtonView startButtonView;
    ButtonView settingsButtonView;
    ButtonView exitButtonView;
    MovingBackgroundView backgroundView;
    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        titleView = new TextView(myGdxGame. commonWhiteFont, 180, 960, "Space Cleaner");
    }
    public void render(float delta) {
        backgroundView.draw(myGdxGame.batch);
    }
}
