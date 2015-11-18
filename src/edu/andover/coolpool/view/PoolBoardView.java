package edu.andover.coolpool.view;

import java.util.Observable;
import java.util.Observer;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.controller.CueBallController;
import edu.andover.coolpool.controller.CueStickController;
import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.model.CueStick;
import edu.andover.coolpool.model.Pocket;
import edu.andover.coolpool.model.PoolBoard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PoolBoardView implements Observer {
	private Pane view;
	double length;
	double width;
	
	// The Green
	private Rectangle rectangle;
	
	// The Rectangle that has the pockets.
	private Rectangle bigRectangle;
	
	// Invisible Rectangle that is used for the mouse hover handler.
	private Rectangle cueStickRectangle;
	
	// Invisible Rectangle that is used for the mouse hover handler for scratch
	// event.
	private Rectangle scratchRectangle;
	
	private PoolBoard poolBoard;
	private BallView[] ballViews;
	private PocketView[] pocketViews;
	private CueStickView cueStickView;
	
	public PoolBoardView(PoolBoard poolBoard) {
		this.poolBoard = poolBoard;
		view = new Pane();
		
		double xMargin = 50; // In Pixels.
		double yMargin = 50; // In Pixels.
		this.length = GameConstants.POOL_TABLE_LENGTH*GameConstants.IN_TO_PIXEL;
		this.width = GameConstants.POOL_TABLE_WIDTH*GameConstants.IN_TO_PIXEL;
		
		// Set up table
		Color brown = Color.web("0x3D362D");
		bigRectangle = new Rectangle(xMargin, yMargin, this.length, this.width);
		double x_scale_multiplier = 1.09;
		double y_scale_multiplier = 1.157;
		bigRectangle.setScaleX(x_scale_multiplier);
		bigRectangle.setScaleY(y_scale_multiplier);
		bigRectangle.setFill(brown);
		view.getChildren().add(bigRectangle);
		
		// Set up green
		Color green = Color.web("0x27AE60");
		rectangle = new Rectangle(xMargin, yMargin, this.length, this.width);
		rectangle.setFill(green);
		view.getChildren().add(rectangle);


		rectangle.setX(GameConstants.POOLBOARD_X);
		rectangle.setY(GameConstants.POOLBOARD_Y);

		bigRectangle.setX(GameConstants.POOLBOARD_X);
		bigRectangle.setY(GameConstants.POOLBOARD_Y);
		
		// Set up CuestickRectangle.
		double addLength = 2000;
		double addWidth = 2000;
		cueStickRectangle = new Rectangle(0, 0, this.length + addLength, 
				this.width + addWidth);
		cueStickRectangle.setFill(Color.TRANSPARENT);
		view.getChildren().add(cueStickRectangle);
		
		// Set up ScratchRectangle.
		double ballRadius = GameConstants.BALL_RADIUS 
				* GameConstants.IN_TO_PIXEL;
		scratchRectangle = new Rectangle(xMargin, 
				yMargin, this.length / 4.0, this.width - 2 * ballRadius);
		scratchRectangle.setX(GameConstants.POOLBOARD_X + ballRadius);
		scratchRectangle.setY(GameConstants.POOLBOARD_Y + ballRadius);
		scratchRectangle.setFill(Color.TRANSPARENT);
		view.getChildren().add(scratchRectangle);
		scratchRectangle.toBack();
		
		
		initElements();
		bringBallsToFront();
		
	}
	
	public Pane getPane() {
		return view;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public Rectangle getBigRectangle() {
		return bigRectangle;
	}
	
	public Rectangle getCueStickRectangle() {
		return cueStickRectangle;
	}
	
	public Rectangle getScratchRectangle() {
		return scratchRectangle;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == poolBoard) {
			if (poolBoard.bounced) {
				GameSounds.BALL_HIT_BALL.play();
			}
			if (poolBoard.resetCue) {
				view.getChildren().add(ballViews[15].getCircle());
				initCueBallController();
			}
		}
		
	}
	
	public void initElements() {
		// initialize balls
		Ball[] balls = poolBoard.getBalls();
		ballViews = new BallView[16];

		for (int i = 0; i < 16; i ++) {
			ballViews[i] = new BallView(balls[i]);
			balls[i].addObserver(ballViews[i]);
			this.getPane().getChildren().add(ballViews[i].getCircle());
		}
		
		// initialize pockets
		Pocket[] pockets = poolBoard.getPockets();
		pocketViews = new PocketView[6];
		for (int i = 0; i < 6; i ++) {
			pocketViews[i] = new PocketView(pockets[i]);
			this.getPane().getChildren().add(pocketViews[i].getCircle());
		}


		// initialize cueStick
		CueStick cueStick = poolBoard.getCueStick();
		cueStickView = new CueStickView(cueStick);
		setCueStickHandlers();
		cueStick.addObserver(cueStickView);
		this.getPane().getChildren().add(cueStickView.getLine());
	}
	
	public void initCueBallController() {
		// initialize scratchController
		CueBallController cueBallController = new CueBallController();
		cueBallController.addMouseHoverEventHandler(this, 
				poolBoard.getBalls()[15]);
		cueBallController.addMousePressedEventHandler(this, 
				poolBoard.getBalls()[15]);
	}
	
	public BallView[] getBallViews() {
		return ballViews;
	}
	
	public CueStickView getCueStickView() {
		return cueStickView;
	}
	
	public void bringBallsToFront() {
		for (BallView bv: ballViews) {
			bv.getCircle().toFront();
		}
		cueStickView.getLine().toFront();
	}
	
	public void setCueStickHandlers() {
		CueStickController cueStickController = 
				new CueStickController(cueStickView);
		cueStickController.addMouseHoverEH(this);
		cueStickController.addMouseDraggedEH(this);
		cueStickController.addMousePressedEH(this);
		cueStickController.addMouseReleasedEH(this);
	}
	
	public void removeCueStickHandlers() {
		getCueStickRectangle().setOnMouseMoved(null);
		getCueStickRectangle().setOnMouseDragged(null);
		cueStickView.getLine().setOnMouseDragged(null);
		cueStickView.getLine().setOnMouseClicked(null);
	}
	
	public PoolBoard getPoolBoard() {
		return poolBoard;
	}
}
