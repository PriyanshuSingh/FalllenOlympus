package com.tgz.fallenolympus.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
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
        running = new Animation(0.02f, Assets.instance.player.running, Animation.PlayMode.LOOP);
        preMelee = new Animation(0.15f, Assets.instance.player.preMelee, Animation.PlayMode.NORMAL);
        melee = new Animation(0.02f, Assets.instance.player.melee, Animation.PlayMode.LOOP);
        idle = new Animation(0.5f, Assets.instance.player.idle, Animation.PlayMode.LOOP_PINGPONG);


        float height = idle.getKeyFrames()[0].getRegionHeight() / 2.0f / Box2DConstants.PPM;
        float width = idle.getKeyFrames()[0].getRegionWidth() / 2.0f / Box2DConstants.PPM;
        BodyBuilder bodyBuilder = new BodyBuilder(world);
        body = bodyBuilder.fixture(
                    bodyBuilder.fixtureDefBuilder()
                    .boxShape(width, height)
                    .density(0.6f)
                    .friction(0.5f)
                    .categoryBits((short) 0)
                    .maskBits((short) 0)
                    .restitution(1.0f)
                    .build()
                )
                .angle(0.0f)
                .position(100.0f / Box2DConstants.PPM, 100.0f / Box2DConstants.PPM)
                .type(BodyDef.BodyType.StaticBody)
                .build();

        setCurrentAnimation(AnimationType.MELEE);

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
        if (reg != null) {
            batch.setColor(1.0f, 0.0f, 0.0f, 1.0f);
            batch.begin();
            batch.draw(
                    reg,
                    position.x - reg.getRegionWidth() / 2.0f, position.y - reg.getRegionHeight() / 2.0f
            );
            batch.end();
            batch.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        }
    }
}
