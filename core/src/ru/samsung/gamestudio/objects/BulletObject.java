package ru.samsung.gamestudio.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.samsung.gamestudio.GameSettings;

import static ru.samsung.gamestudio.GameSettings.SCREEN_HEIGHT;

public class BulletObject extends GameObject {
    public BulletObject(int x, int y, int width, int height, String texturePath, World world) {
        super(texturePath, x, y, width, height, world);
        body.setLinearVelocity(new Vector2(0, 5 -GameSettings.BULLET_VELOCITY));
    }
    public boolean hasToBeDestroyed() {
        return getY() - height/2 > GameSettings.SCREEN_HEIGHT;
    }
}
