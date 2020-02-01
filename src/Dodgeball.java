import java.awt.Color;
import java.awt.event.KeyEvent;

/* This is the dodgeball game *
 * I got help from JinYan for the direction calculation */

public class Dodgeball {
	static EZSound ohno;		//initialize hit sound
	static EZSound bounce;		//initialize pokeball bounce
	static EZSound bounce1;		//initialize greatball bounce
	static EZSound bounce2;		//initialize ultraball bounce
	static EZSound bounce3;		//initialize masterball bounce
	static EZSound theme;		//initialize opening song
	static EZSound ending;		//initialize ending song

	public static void main(String[] args) {
		EZ.initialize(1000,700);							//initialize the screen 1000 by 700
		boolean y = true;									//initialize variable y to true
		y = welcome(y);				//set y to equal the return state from welcome
		if(y = true) {										//if the state of welcome is true
			//System.out.println("TEST");
			play();					//start the game
			//System.out.print("IM HERE NOW");
		}
	}

	//welcome function
	public static boolean welcome(boolean x) {
		EZImage welcome = EZ.addImage("welcome.jpg", 500,350);		//initialize the welcome screen 
		EZText wel = EZ.addText(512, 50, "WELCOME", Color.YELLOW, 75);	//initialize welcome title
		EZText controls = EZ.addText(875, 200, "controls", Color.BLACK, 28);	//initialize controls
		EZText w = EZ.addText(875, 225, "w for up", Color.BLACK, 28);			//w is up
		EZText a = EZ.addText(875, 250, "a for left", Color.BLACK, 28);			//a is left
		EZText s = EZ.addText(875, 275, "s for down", Color.BLACK, 28);			//s is down
		EZText d = EZ.addText(875, 300, "d for right", Color.BLACK, 28);		//d is left
		EZText start = EZ.addText(512, 625, "space to start", Color.BLACK, 50);	//press space to start
		wel.setFont("Pokemon Solid.TTF");										//font used for title
		theme = EZ.addSound("theme.wav");										//play opening theme
		theme.play();
		while(x == true) {								
			if(EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {		//if the space key is pressed
				//System.out.println("NO");
				theme.stop();										//stop playing the theme
				x = false;											//state is now false
			}
			else {													//else wait till space is pressed
				System.out.println("TRUE");							
				x = true; 
			}
		}
		//System.out.print("truth");
		return true;
	}

	//end function
	public static void end() {
		EZImage end = EZ.addImage("end.jpg", 500,350);				//initialize the end screen
		EZText next = EZ.addText(512, 50, "better luck next time!", Color.BLACK, 70);	//blnt
		ending = EZ.addSound("ending.wav");							//play ending theme
		ending.play();
	}

	//gameplay function
	public static void play() {
		EZ.refreshScreen();
		//background and psyduck
		EZImage background = EZ.addImage("bg.png", 450,350);		//loads the background
		EZImage psyduck = EZ.addImage("psyduck.png", 512,352); 		//loads psyduck

		//pokeballs 
		EZImage poke = EZ.addImage("poke.png",0,0); 				//shows pokeball
		EZImage great = EZ.addImage("great.png",700,0);				//shows greatball
		EZImage ultra = EZ.addImage("ultra.png",900,800);			//shows ultraball
		EZImage master = EZ.addImage("master.png",1000,700);		//shows masterball

		//use distance formula function getD for balls
		Directions pokeinitialDirection = getD(psyduck, poke);		//gets pokeball direction
		Directions greatinitialDirection = getD(psyduck, great);	//gets greatball direction
		Directions ultrainitialDirection = getD(psyduck, ultra);	//gets ultraball direction
		Directions masterinitialDirection = getD(psyduck, master);	//gets masterball direction

		//get center of balls and get initial direction
		double pokeX = poke.getXCenter(); 							//locates pokeball x coordinates
		double pokeY = poke.getYCenter();							//locates pokeball y coordinates
		double dXpoke = pokeinitialDirection.xDirection;			
		double dYpoke = pokeinitialDirection.yDirection;		

		double greatX = great.getXCenter();							//locates greatball x coordinates
		double greatY = great.getYCenter();							//locates greatball y coordinates
		double dXgreat = greatinitialDirection.xDirection;
		double dYgreat = greatinitialDirection.yDirection;	

		double ultraX = ultra.getXCenter();							//locates ultraball x coordinates
		double ultraY = ultra.getYCenter();							//locates ultraball y coordinates
		double dXultra = ultrainitialDirection.xDirection;
		double dYultra = ultrainitialDirection.yDirection;	

		double masterX = master.getXCenter();							//locates masterball x coordinates
		double masterY = master.getYCenter();							//locates masterball y coordinates
		double dXmaster = masterinitialDirection.xDirection;
		double dYmaster = masterinitialDirection.yDirection;	

		//sounds
		ohno = EZ.addSound("psyduck1.wav"); 						//loads psyduck hit sound
		bounce = EZ.addSound("bounce.wav");							//loads pokeball bounce sound
		bounce1 = EZ.addSound("bounce1.wav");						//loads greatball bounce sound
		bounce2 = EZ.addSound("bounce2.wav");						//loads ultraball bounce sound
		bounce3 = EZ.addSound("bounce3.wav");						//loads masterball bounce sound

		//lives
		EZImage psyhead = EZ.addImage("psyhead.png",50,50);			//shows psyduck life 1
		EZImage psyhead1 = EZ.addImage("psyhead.png",125,50);		//shows psyduck life 2
		EZImage psyhead2 = EZ.addImage("psyhead.png", 200,50);		//shows psyduck life 3

		int lives = 3;												//initialize psyduck lives as 3
		while(lives > 0) {											//continue as there are still lives left
			pokeX = poke.getXCenter();								//get pokeball x coordinates
			pokeY = poke.getYCenter();								//get pokeball y coordinates
			greatX = great.getXCenter();							//get greatball x coordinates
			greatY = great.getYCenter();							//get greatball y coordinates
			ultraX = ultra.getXCenter();							//get ultraball x coordinates
			ultraY = ultra.getYCenter();							//get uultraball y coordinates
			masterX = master.getXCenter();							//get masterball x coordinates
			masterY = master.getYCenter();							//get masterball y coordinates
			//controls
			if (EZInteraction.isKeyDown('w')) {						//if player presses w go up
				if (psyduck.yCenter > 0) {
					psyduck.yCenter-=10;
				}
			} else if (EZInteraction.isKeyDown('s')) { 			//if player presses s go down
				if (psyduck.yCenter < 700 ) {
					psyduck.yCenter+=10;
				}
			} else if (EZInteraction.isKeyDown('d')) {			//if player pressed d go right
				if (psyduck.xCenter < 1000) {
					psyduck.xCenter+=10;
				}
			}else if (EZInteraction.isKeyDown('a')) {			//if player presses a go left
				if (psyduck.xCenter > 0) {
					psyduck.xCenter-=10;
				}
			}


			//directs ball to psyduck after hitting edges
			pokeX = pokeX + dXpoke*5; 											//calculate pokeball y coordinate
			pokeY = pokeY + dYpoke*5; 											//calculate pokeball x coordinate
			poke.translateTo(pokeX,pokeY);										//move the pokeball
			if (pokeX >= 1000 || pokeX <= 0 || pokeY >= 700 || pokeY <= 0) {
				Directions newDirections = getD(psyduck, poke);					//find new direction of psyduck
				dXpoke = newDirections.xDirection;								//find x direction
				dYpoke = newDirections.yDirection;								//find y direction
				bounce.play();													//play sound pokeball hits edge
			}

			greatX = greatX + dXgreat*5; 										//calculate greatball x coordinate
			greatY = greatY + dYgreat*5;  										//calculate greatball y coordinate
			great.translateTo(greatX,greatY);									//move the greatball
			if (greatX >= 1000 || greatX <= 0 || greatY >= 700 || greatY <= 0) {
				Directions newDirections = getD(psyduck, great);				//find new direction of psyduck
				dXgreat = newDirections.xDirection;								//find x direction
				dYgreat = newDirections.yDirection;								//find y direction
				bounce1.play();													//play sound greatball hits edge
			}

			ultraX = ultraX + dXultra*5; 										//calculate ultraball x coordinate
			ultraY = ultraY + dYultra*5; 										//calculate ultraball y coordinate
			ultra.translateTo(ultraX,ultraY);									//move the ultraball
			if (ultraX >= 1000 || ultraX <= 0 || ultraY >= 700 || ultraY <= 0) {
				Directions newDirections = getD(psyduck, ultra);				//find new direction of psyduck
				dXultra = newDirections.xDirection;								//find x direction
				dYultra = newDirections.yDirection;								//find y direction
				bounce2.play();													//play sound ultraball hits edge
			}

			masterX = masterX + dXmaster*5; 									//calculate masterball x coordinate
			masterY = masterY + dYmaster*5; 									//calculate masterball x coordinate
			master.translateTo(masterX,masterY);								//move the masterball
			if (masterX >= 1000 || masterX <= 0 || masterY >= 700 || masterY <= 0) {
				Directions newDirections = getD(psyduck, master);				//find new direction of psyduck
				dXmaster = newDirections.xDirection;							//find x direction
				dYmaster = newDirections.yDirection;							//find y direction
				bounce3.play();													//play sound masterball hits edge
			}

			if (uhoh(psyduck,poke)) {				//if hit
				ohno.play();						//play hit sound
				lives--;							//decrement life
				poke.translateTo(0,0);				//reset pokeball 
			}
			if (uhoh(psyduck,great)) {				//if hit
				ohno.play();						//play hit sound
				lives--;							//decrement life
				great.translateTo(0, 700);			//reset greatball
			}
			if (uhoh(psyduck,ultra)) {				//if hit
				ohno.play();						//play hit sound
				lives--;							//decrement life
				ultra.translateTo(1000, 0);			//reset ultraball
			}
			if (uhoh(psyduck,master)) {				//if hit
				ohno.play();						//play hit sound
				lives--;							//decrement life
				master.translateTo(1000, 700);		//reset masterball
			}

			//decrement life if hit
			if(lives == 2) {				//if hit delete a psyhead
				psyhead.hide();
			}
			if(lives == 1) {				//if hit again delete another psyhead
				psyhead1.hide();
			}
			if(lives == 0) {				//if hit again end game
				psyhead2.hide();
				end();

			}
			EZ.refreshScreen();
		}	
	}

	/* This function is where JinYan helped me */
	public static Directions getD(EZImage psyduck, EZImage ball) {
		double psyduckX = psyduck.getXCenter();		//psyduck x coordinates
		double psyduckY = psyduck.getYCenter();		//psyduck y coordinates

		double ballX = ball.getXCenter(); 			//get ball x coordinates
		double ballY = ball.getYCenter();			//get ball y coordinates

		double width = psyduckX - ballX;			//find the x distance of psyduck to ball
		double height = psyduckY - ballY;			//find the y distance of psyduck to ball
		double distance = Math.sqrt((width * width) + (height * height)); 	//calculate the distance
		double dXb = width/distance;				//calculate the x distance
		double dYb = height/distance;				//calculate the y distance

		return new Directions(dXb, dYb);			//find new direction for the ball
	}

	//hit function
	/* This function is where JinYan helped me also*/
	public static boolean uhoh(EZImage psyduck, EZImage ball) {
		int w = (ball.getWidth())/2;			//calculate the x 
		int h = (ball.getHeight())/2;			//calculate the y 
		int bXC = ball.getXCenter();			//find the ball x coordinate
		int bYC = ball.getYCenter();			//find the ball y coordinate
		// top left
		int tlx = bXC - w;
		int tly = bYC - h;
		// top right
		int trx = bXC + w;
		int trY = bYC - h;
		// bottom left
		int blx = bXC - w;
		int bly = bYC + h;
		// bottom right
		int brx = bXC + w;
		int bry = bYC + h;

		if(psyduck.isPointInElement(blx, bly) ||
				psyduck.isPointInElement(brx, bry) || 
				psyduck.isPointInElement(tlx, tly) || 
				psyduck.isPointInElement(trx, trY)) {
			return true;
		}
		return false;
	}
}

/* This function is where JinYan helped me also*/
class Directions {
	public double xDirection;
	public double yDirection;

	public Directions(double x, double y) {
		xDirection = x;
		yDirection = y;
	}
}