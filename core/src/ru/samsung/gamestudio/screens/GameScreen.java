package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.samsung.gamestudio.objects.BulletObject;
import ru.samsung.gamestudio.objects.ShipObject;
import ru.samsung.gamestudio.objects.TrashObject;
import ru.samsung.gamestudio.GameResources;
import ru.samsung.gamestudio.GameSession;
import ru.samsung.gamestudio.GameSettings;
import ru.samsung.gamestudio.MyGdxGame;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable.draw;

public class GameScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;
    ShipObject shipObject;
    GameSession gameSession;
    ArrayList<TrashObject> trashArray;
    ArrayList<BulletObject> bulletArray;


    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        shipObject = new ShipObject(
                GameSettings.SCREEN_WIDTH / 2, 150,
                GameSettings.SHIP_WIDTH, GameSettings.SHIP_HEIGHT,
                GameResources.SHIP_IMG_PATH,
                myGdxGame.world
        );
        gameSession = new GameSession();
        trashArray = new ArrayList<>();
        bulletArray = new ArrayList<>();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
    }

    @Override
    public void render(float delta) {
        myGdxGame.stepWorld();
        handleInput();
        if (gameSession.shouldSpawnTrash()) {
            TrashObject trashObject = new TrashObject(
                    GameResources.TRASH_IMG_PATH,
                    (int) GameSettings.TRASH_WIDTH, (int) GameSettings.TRASH_HEIGHT,
                    myGdxGame.world
            );
            trashArray.add(trashObject);
        }

        updateBullets();
        updateTrash();



        if (shipObject.needToShoot()) {
            BulletObject laserBullet = new BulletObject(
                    shipObject.getX(), shipObject.getY() + shipObject.height / 2,
                    GameSettings.BULLET_WIDTH, GameSettings.BULLET_HEIGHT,
                    GameResources.BULLET_IMG_PATH,
                    myGdxGame.world
            );
            bulletArray.add(laserBullet);
        }
        draw();
    }

    private void draw() {
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        myGdxGame.batch.begin();
        for (TrashObject trash : trashArray) trash.draw(myGdxGame.batch);
        for (BulletObject b : bulletArray) b.draw(myGdxGame.batch);
        shipObject.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }


    private void updateTrash() {
        for (int i = 0; i < trashArray.size(); i++) {
            if (!trashArray.get(i).isInFrame()) {
                myGdxGame.world.destroyBody(trashArray.get(i).body);
                trashArray.remove(i--);
            }
        }
    }

    private void updateBullets() {
        for (int i = 0; i < bulletArray.size(); i++) {
            if (bulletArray.get(i).hasToBeDestroyed()) {
                myGdxGame.world.destroyBody(bulletArray.get(i).body);
                bulletArray.remove(i--);
            }
        }
    }
}