//ID:318948809
package game;

import background.PauseScreen;
import biuoop.KeyboardSensor;
import hitlistener.BlockRemover;
import hitlistener.BallRemover;
import geometry.Point;
import geometry.Rectangle;
import geometry.SpriteCollection;
import geometry.Velocity;
import hitlistener.Counter;
import hitlistener.ScoreTrackingListener;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.ScoreIndicator;
import sprites.Block;
import sprites.Ball;
import sprites.Paddle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Game.Game.
 */
public class GameLevel implements Animation {
    /**
     * The Sprites.Block width.
     */
    static final int BLOCK_WIDTH = 30;
    /**
     * The Sprites.Block height.
     */
    static final int BLOCK_HEIGHT = 20;
    /**
     * The Screen width.
     */
    static final int SCREEN_WIDTH = 800;
    /**
     * The Screen height.
     */
    static final int SCREEN_HEIGHT = 600;
    /**
     * The Start x.
     */
    static final int START_X = 0;
    /**
     * The Start y.
     */
    static final int START_Y = 0;
    /**
     * The Keyboard.
     */
    private biuoop.KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter counter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation l;

    /**
     * Instantiates a new Game.Game.
     *
     * @param levelInfo       the level info
     * @param kbs             the kbs
     * @param animationRunner the a r
     * @param score           the score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor kbs, AnimationRunner animationRunner, Counter score) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        counter = new Counter();
        ballCounter = new Counter();
        this.score = score;
        runner = animationRunner;
        keyboard = kbs;
        l = levelInfo;
        paddle = new Paddle(this.keyboard, l.paddleSpeed(), l.paddleWidth());

    }


    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize.
     * Initialize a new game: create the Blocks and Sprites.Ball and Sprites.Paddle
     * and add them to the game.
     */
    public void initialize() {
        sprites.addSprite(l.getBackground());
        BlockRemover blockRemover = new BlockRemover(this, this.counter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, this);
        scoreIndicator.addToGame(this);
        for (Block b : l.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }
        this.counter.increase(l.numberOfBlocksToRemove());
        for (Velocity v : l.initialBallVelocities()) {
            Ball ball = new Ball(400, 550, 5, Color.WHITE);
            ball.addToGame(this);
            ball.setGame(environment);
            ball.setVelocity(v);
            ballCounter.increase(1);
        }
        Rectangle r2 = new Rectangle(new Point(BLOCK_WIDTH, SCREEN_HEIGHT + 10),
                SCREEN_WIDTH - 2 * BLOCK_WIDTH, BLOCK_HEIGHT);
        Block blockDie = new Block(r2, Color.GRAY);
        blockDie.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        blockDie.addHitListener(ballRemover);
        Rectangle r1 = new Rectangle(new Point(START_X, BLOCK_HEIGHT), BLOCK_WIDTH, SCREEN_HEIGHT);
        Block b1 = new Block(r1, Color.GRAY);
        b1.addToGame(this);
        Rectangle r3 = new Rectangle(new Point(SCREEN_WIDTH - BLOCK_WIDTH, BLOCK_HEIGHT), BLOCK_WIDTH,
                SCREEN_HEIGHT - BLOCK_HEIGHT);
        Block b3 = new Block(r3, Color.GRAY);
        b3.addToGame(this);
        Rectangle r4 = new Rectangle(new Point(START_X, START_Y + 20), SCREEN_WIDTH, BLOCK_HEIGHT);
        Block b4 = new Block(r4, Color.GRAY);
        b4.addToGame(this);
        paddle.movePaddleToTheMiddle();
        paddle.addToGame(this);
    }

    /**
     * Gets color.
     *
     * @param c the c
     * @return the color
     */
    public Color getColor(int c) {
        switch (c) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.PINK;
            default:
                return null;
        }
    }
    /**
     * createBallsOnTopOfPaddle.
     *the function create the balls of this level.
     */
    private void createBallsOnTopOfPaddle() {
        for (Velocity v : l.initialBallVelocities()) {
            Ball ball = new Ball(400, 550, 5, Color.WHITE);
            ball.addToGame(this);
            ball.setGame(environment);
            ball.setVelocity(v);
            ballCounter.increase(1);
        }
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public LevelInformation getLevel() {
        return l;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return l.getBackground();
    }

    /**
     * Gets blocks count.
     *
     * @return the blocks count
     */
    public int getBlocksCount() {
        return counter.getValue();
    }

    /**
     * Gets balls count.
     *
     * @return the balls count
     */
    public int getBallsCount() {
        return ballCounter.getValue();
    }

    /**
     * Run.
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts.
        this.running = true;
        //use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * Remove collidable.
     * the function remove this collidable
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     * the function remove this sprite
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.counter.getValue() <= 0 || ballCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
    }


    @Override
    public boolean shouldStop() {
        return !this.running;
    }


}