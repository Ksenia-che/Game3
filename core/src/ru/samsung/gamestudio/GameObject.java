package ru.samsung.gamestudio;

import com.badlogic.gdx.physics.box2d.*;

public class GameObject {
    int width, height;
    int SCALE;
    private Body createBody(float x, float y, World world) {
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(width, height) * SCALE / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;
        body.createFixture(fixtureDef);
        circleShape.dispose();
        body.setTransform(x * SCALE, y * SCALE, 0);
        return body;
    }

}
