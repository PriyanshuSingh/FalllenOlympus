package com.tgz.fallenolympus.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tgz.fallenolympus.assets.Assets;
import com.tgz.fallenolympus.utils.Animation;
import com.tgz.fallenolympus.utils.BodyBuilder;
import com.tgz.fallenolympus.utils.Box2DConstants;

/**
 * Created by priyanshu on 27/12/14.
 */
public class Player extends AbstractGameObject{

    public static final String TAG = Player.class.getName();

    public enum AnimationType{
        RUNNING,
        MELEE,
        IDLE
    }


    private Animation running;
    private Animation preMelee;
    private Animation melee;
    private Animation idle;

    private Animation currentAnimation;

    public Player(World world) {
        super();
        running = new Animation(0.05f, Assets.instance.player.running, Animation.PlayMode.LOOP);
        preMelee = new Animation(0.15f, Assets.instance.player.preMelee, Animation.PlayMode.NORMAL);
        melee = new Animation(0.02f, Assets.instance.player.melee, Animation.PlayMode.LOOP);
        idle = new Animation(0.5f, Assets.instance.player.idle, Animation.PlayMode.LOOP_PINGPONG);

        scale.set(0.75f, 0.75f);

        float height = scale.y * (idle.getKeyFrames()[0].getRegionHeight() / 2.0f / Box2DConstants.PPM);
        float width = scale.x * (idle.getKeyFrames()[0].getRegionWidth() / 2.0f / Box2DConstants.PPM);
        float heightOffset = 5.0f / Box2DConstants.PPM;
        float widthOffset = 5.0f / Box2DConstants.PPM;
        height -= heightOffset;
        width -= widthOffset;
        BodyBuilder bodyBuilder = new BodyBuilder(world);
        body = bodyBuilder.fixture(
                    bodyBuilder.fixtureDefBuilder()
                    .boxShape(width, height)
                    .density(0.6f)
                    .friction(1.0f)
                    .categoryBits(Box2DConstants.PLAYER_BIT)
                    .maskBits(Box2DConstants.GROUND_BIT)
                    .restitution(0.0f)
                    .build()
                )
                .angle(0.0f)
                .fixedRotation()
                .position(200.0f / Box2DConstants.PPM, 300.0f / Box2DConstants.PPM)
                .type(BodyDef.BodyType.DynamicBody)
                .build();
        FixtureDef fixtureDef = new FixtureDef();



        bounds.set(width * 2.0f * Box2DConstants.PPM, height * 2.0f * Box2DConstants.PPM);
        setCurrentAnimation(AnimationType.IDLE);

    }

    public void setCurrentAnimation(AnimationType animationType) {
        switch (animationType) {
            case IDLE:
                currentAnimation = idle;
                break;
            case RUNNING:
                currentAnimation = running;
                break;
            case MELEE:
                currentAnimation = preMelee;
                break;
        }
        currentAnimation.reset();
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (currentAnimation != null) {
            if (currentAnimation == preMelee && currentAnimation.isAnimationFinished(deltaTime)) {
                currentAnimation = melee;
                currentAnimation.reset();
            }
            region = currentAnimation.getKeyFrame(deltaTime);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = region;
        float x = scale.x * reg.getRegionWidth() / 2.0f;
        float y = bounds.y / 2.0f;
        float offset = (float) Math.sqrt(x * x + y * y);
        float angle = (float) Math.atan(x / y) - rotation * MathUtils.degreesToRadians;
        float offsetX = offset * MathUtils.sin(angle);
        float offsetY = offset * MathUtils.cos(angle);
        if (reg != null) {
            batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            batch.begin();
            batch.draw(
                    reg,
                    position.x - offsetX, position.y - offsetY,
                    origin.x, origin.y,
                    reg.getRegionWidth(), reg.getRegionHeight(),
                    scale.x, scale.y,
                    rotation
            );
            batch.end();
            batch.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        }
    }
}
