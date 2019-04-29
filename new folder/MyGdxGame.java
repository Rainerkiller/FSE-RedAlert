/*
This is a really special space invader, A is moving ,S is firing and D is moving.
no blocks, dont lose.
And i cant find the free download sound for this project ;-(
 */
package com.mygdx.game.desktop;
/*
I dont know what are these classes
 */
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch; // background
	BitmapFont font;	// annoying letters
	Music bgm;
	Texture img,	invader,bullet, load1,   load2,     bossT,  bossC;
	//	   monster   ship   bullet  start    game over	 big     small
	//                              page     page		 boss    boss
	Sprite ship,start;
 	//     GG   start page
	//new ship
	private Boss Boss = new Boss(); // boss class
	private Ship shipx = new Ship(); // ship
	private List<Bult> bullyts = new ArrayList<Bult>();//the list of bullets
	private List<Monster> monsters = new ArrayList<Monster>(); // list of monsters
	private List<MonsterBul> Mbult = new ArrayList<MonsterBul>(); // list of monster's bullets
	//fire timer
	// pause
	private String Status = "loading"; // which level you are in
	//score
	private int score = 0; // score, this is a really bad thing trust me;
	// movement timer
	private int bling;  // time to change pic for monsters
	//monsterr move
	private int timer;
	private boolean MosnterRight = true,prevent=true; // monster sometimes will not move because
														// their x is 0 or 1000, i use prevent to stop this;
	//Monster shoting;
	private int Charge=500;// how fast monster shot
	//boss battle staff
	private int timing; // boss' ability ( tp and rage)
	private boolean MOVE; // should boss Move right ? (the direction that it should move)
	public boolean END = false; // not only 1 level for boss battle

	public void createM(){ // creat monsters
		img = new Texture("imgs/monster.png"); // load it first
		invader = new Texture("imgs/invader.jpg");// waste of ram
		int x = 0;
		int y = 900;
		for(int i = 0; i<8 ; i++){
			for(int k = 0; k < 11 ; k++){ // lined up
				Monster a = new Monster();
				a.Set(x,y);
				a.SetImg(img,invader);
				x+=60;
				if(x==660){// a line is finished, start from 0
					x=0;
				}
				monsters.add(a);
			}
			y-=60; // next line
		}
	}
	@Override
	public void create () { // load and get things ready
		createM();
		Gdx.graphics.setWindowedMode(1200, 1000);
		batch = new SpriteBatch();
		// finding pic
		img = new Texture("imgs/monster.png");
		invader = new Texture("imgs/invader.jpg");
		bullet = new Texture("imgs/bullet.jpg");
		load1 = new Texture("imgs/StartPage.png");
		load2 = new Texture("imgs/lose.png");//GG
		bossT = new Texture("imgs/boss.jpg");
		bossC = new Texture("imgs/bossC.jpg");
		bgm = Gdx.audio.newMusic(Gdx.files.internal("sound.mp3"));
		// this is the font
		font = new BitmapFont();
		font.getData().setScale(2f);
		// loading pic
		shipx.LoadImg(invader);
		Boss.LoadImg(bossT);
		ship = new Sprite(load2);
		start = new Sprite(load1);
	}

	@Override
	public void render () {

		//loading
		if(Status.equals("loading")){ // load up things
			bgm.play();
			start.setPosition(260,240);
			batch.begin();
			start.draw(batch);
			batch.end();

			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){// space to start
				Status = "game";
			}
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){//left click is fine
				Status = "game";
			}
		}
		if(Status.equals("game")) {

			Charge+=score;// this is a bad thing

			if(Charge>1000){// when a ,monster can shot
				int k = (int)(Math.random()*monsters.size()); // which monster?
				MonsterBul a = new MonsterBul(); // a bullet
				a.SetGift(bullet);//set it up
				a.LoadImg();
				a.Set(monsters.toArray(new Monster[monsters.size()])[k].Getx(),monsters.toArray(new Monster[monsters.size()])[k].Gety());
				Mbult.add(a);//add it in
				Charge = 0;// recharge
			}
			for(int i = 0; i<Mbult.size();i++){
				Mbult.toArray(new MonsterBul[Mbult.size()])[i].Moving();// let them move , all of them
			}
			//collied?
			for(int i =0;i<bullyts.size();i++){ // try out all of the overlaps, i really thanks rectangle class in libgd
				Rectangle bul = bullyts.toArray(new Bult[bullyts.size()])[i].Getimg().getBoundingRectangle();//bounding
				for(int k = 0;k<monsters.size();k++){
					Rectangle monsteR = monsters.toArray(new Monster[monsters.size()])[k].Getimg()
							.getBoundingRectangle();
					if(monsteR.overlaps(bul)){ // is there is an overlaps
						monsters.remove(k);// remove these two things
						bullyts.remove(i);
						score+=10; // u killed a monster, +10 monster attacks speed
					}
				}
			}
			for(int i =0;i<Mbult.size();i++){ // same as top, bullet can destory bullet;
				Rectangle monsteR = Mbult.toArray(new MonsterBul[Mbult.size()])[i].Getimg().getBoundingRectangle();
				for(int k = 0;k<bullyts.size();k++){
					Rectangle bul = bullyts.toArray(new Bult[bullyts.size()])[k].Getimg().getBoundingRectangle();

					if(monsteR.overlaps(bul)){
						Mbult.remove(i);
						bullyts.remove(k);
						score+=5;
					}
				}
			}
			for(int i = 0;i<Mbult.size();i++){ // bullet can hurt you same as top
				Rectangle bul = Mbult.toArray(new MonsterBul[Mbult.size()])[i].Getimg().getBoundingRectangle();
				Rectangle shipRect = shipx.Getimg().getBoundingRectangle();
				if(shipRect.overlaps(bul)){
					shipx.hurt();// hp-1
					Mbult.remove(i);
				}
			}
			// monster moves
			for(int i =0;i<monsters.size();i++){// find out weather is touched or not
				if(monsters.toArray(new Monster[monsters.size()])[i].Touch()&&!prevent){
					// prevent they lied on 0 or 1000, this is really annoying
					MosnterRight = false;
					break;
				}
			}
			if(MosnterRight&&timer >= 5000){ // move one step, really long move
				for(int i =0;i<monsters.size();i++){
					monsters.toArray(new Monster[monsters.size()])[i].Move();
					prevent = false;
				}
				timer =0;
			}
			else if(!MosnterRight &&!prevent&&timer >= 5000){// move down, it cant move down and right as same time
				for(int i =0;i<monsters.size();i++){
					monsters.toArray(new Monster[monsters.size()])[i].MoveDown();
					monsters.toArray(new Monster[monsters.size()])[i].OtherWay();
					MosnterRight = true;
					prevent= true;// be care full about lied on the 0 and 1000
				}
				timer =0;
			}
			timer+=50;// moving
			// key press
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {// gdx press and move
				if(shipx.GetX()>0) {//check out
					shipx.MoveLeft();
				}
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {//samethig
				if(shipx.GetX()<1000) {//dont rush over
					shipx.MoveRight();
				}
			}
			if(Gdx.input.isKeyJustPressed(Input.Keys.S)){// each time you press it , you will shot
				if(shipx.shot()) {// you have have enough charge on your weapon
					Bult e = new Bult(); // a bullet
					e.Set(shipx.GetX()+30,shipx.GetY());// set up
					e.SetGift(bullet);
					e.LoadImg();
					bullyts.add(e);//add into list
					shipx.ReSet(); // clear energy charge
				}
			}
			if(bling > 10){ // shiny green monster
				for(int i  = 0;i<monsters.size();i++){
					monsters.toArray(new Monster[monsters.size()])[i].Bling();
				}
				bling = 0; // this needs a CD
			}
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// clear background
			// move bullet
			for(int i = 0;i<bullyts.size();i++){ // bullets are flying
				bullyts.toArray(new Bult[bullyts.size()])[i].Moving();
			}
			//draw everything
			batch.begin();
			for(int i = 0;i<bullyts.size();i++){//draw all bullet
				bullyts.toArray(new Bult[bullyts.size()])[i].ShowBullt(batch);
			}
			for(int i =0;i<Mbult.size();i++){//draw all monsters' bullets
				Mbult.toArray(new MonsterBul[Mbult.size()])[i].ShowBullt(batch);
			}
			for(int i  = 0;i<monsters.size();i++){// draw all monsters
				monsters.toArray(new Monster[monsters.size()])[i].ShowMonster(batch);
			}
			font.draw(batch, "your Score: "+score, 0, 970); // their attack speed
			font.draw(batch,"your life: " + shipx.GetL(),500,970);// you still have chance,or hopeless
			shipx.ShowShip(batch);//draw ship
			batch.end();
			if(monsters.size() == 0){// next stage
				Status = "winX";
			}
			for(int i = 0; i <monsters.size();i++){//lose when touchdown
				if(monsters.toArray(new Monster[monsters.size()])[i].Gety()<-10){
					Status = "lose";
				}
			}
			if(!shipx.live()){// lost when you die
				Status = "lose";
			}
			bling++;// more shiny
			shipx.Charge();// recharge weapon
			for(int i = 0; i<bullyts.size();i++){
				if(bullyts.toArray(new Bult[bullyts.size()])[i].GetY()>1200){
					bullyts.remove(i);
				}
			}// clean up off screen bullets
			for(int i = 0; i<Mbult.size();i++){
				if(Mbult.toArray(new MonsterBul[bullyts.size()])[i].GetY()<-10){
					Mbult.remove(i);
				}
			}// clean up off screen bullets
		}
		if(Status.equals("win")){ // boss battle, i can say its 90% same as game stage
			if(Boss.GetX()>=946){ // boss's move right
				MOVE = false;
			}
			if(Boss.GetX()<=10){ // boss's move left
				MOVE = true;
			}
			if(MOVE){ // right
				Boss.MoveRight();
			}
			if(!MOVE){//left
				Boss.MoveLeft();
			}
			//collide
			for(int i =0;i<bullyts.size();i++){// same collide;
				Rectangle bul = bullyts.toArray(new Bult[bullyts.size()])[i].Getimg().getBoundingRectangle();
				for(int k = 0;k<Mbult.size();k++){
					Rectangle monsteR = Mbult.toArray(new MonsterBul[Mbult.size()])[k].Getimg().getBoundingRectangle();
					if(monsteR.overlaps(bul)){
						Mbult.remove(k);
						bullyts.remove(i);
						score+=5;
					}
				}
			}
			for(int i = 0;i<bullyts.size();i++){ // same *2
				Rectangle bul = bullyts.toArray(new Bult[bullyts.size()])[i].Getimg().getBoundingRectangle();
				Rectangle shipRect = Boss.Getimg().getBoundingRectangle();
				if(shipRect.overlaps(bul)){
					Boss.hurt();
					bullyts.remove(i);

				}
			}
			for(int i = 0;i<Mbult.size();i++){ //same *3
				Rectangle bul = Mbult.toArray(new MonsterBul[Mbult.size()])[i].Getimg().getBoundingRectangle();
				Rectangle shipRect = shipx.Getimg().getBoundingRectangle();
				if(shipRect.overlaps(bul)){
					shipx.hurt();
					Mbult.remove(i);

				}
			}
			// key press
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {// same moving
				if(shipx.GetX()>0) {
					shipx.MoveLeft();
				}
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				if(shipx.GetX()<1150) {
					shipx.MoveRight();
				}
			}
			if(Gdx.input.isKeyPressed(Input.Keys.S)){
				if(shipx.shot()) {
					Bult e = new Bult();
					e.Set(shipx.GetX()+30,shipx.GetY());
					e.SetGift(bullet);
					e.LoadImg();
					bullyts.add(e);
					shipx.ReSet();
				}
			}
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//same clean bg
			if(Boss.shot()) {// boss shoting
				MonsterBul BossBul = new MonsterBul();// its a monster's bullet
				BossBul.Set(Boss.GetX()+(int)(Math.random()*694), Boss.GetY()+(int)(Math.random()*694));
				BossBul.SetGift(bullet);
				BossBul.LoadImg();//set
				Mbult.add(BossBul);//add
				Boss.ReSet();// re charge like normal ship
			}
			for(int i = 0;i <Mbult.size();i++){//sasme moving
				Mbult.toArray(new MonsterBul[Mbult.size()])[i].Moving();
			}

			if(timing>500){ // ability time . tp or rage shot(really really fast)
				int k = (int)(Math.random()*10);
				if(k == 0){
					Boss.tp();
				}
				if(k == 1){
					Boss.Rage();
				}
				timing = 0;
			}
			for(int i = 0;i<bullyts.size();i++){// move bullet
				bullyts.toArray(new Bult[bullyts.size()])[i].Moving();
			}
			// draw everything
			batch.begin();
			shipx.ShowShip(batch);
			Boss.ShowShip(batch);
			for(int i = 0;i <Mbult.size();i++){
				Mbult.toArray(new MonsterBul[Mbult.size()])[i].ShowBullt(batch);
			}
			for(int i = 0;i<bullyts.size();i++){
				bullyts.toArray(new Bult[bullyts.size()])[i].ShowBullt(batch);
			}
			font.draw(batch,"your life: " + shipx.GetL(),500,970);
			font.draw(batch,"Boss's life: " + Boss.GetL(),0,970);
			batch.end();
			shipx.Charge(); // war of 2 brothers
			Boss.Charge();
			timing++;// ability timer
			for(int i = 0; i<bullyts.size();i++){// clean up bullets
				if(bullyts.toArray(new Bult[bullyts.size()])[i].GetY()>1200){
					bullyts.remove(i);
				}
			}
			for(int i = 0; i<Mbult.size();i++){// same clean thing
				if(Mbult.toArray(new MonsterBul[bullyts.size()])[i].GetY()<-10){
					Mbult.remove(i);
				}
			}

			if(!shipx.live()){// lost
				Status ="lose";
			}
			if(!Boss.live()){//you will never win hhahahaha
				Status = "winY";
				END = true;
			}
			if(!Boss.live()&&END){//never win
				Status = "game";
				END =!END;
			}
		}// win?
		if(Status.equals("lose")){// you lost
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			ship.setPosition(170,160);
			batch.begin();
			ship.draw(batch);//lost page
			batch.end();
		}
		if(Status.equals("winX")){// set up data for boss battle
			Mbult.clear(); //clear bullets remains on battle field
			bullyts.clear();
			Status = "win";
			shipx.SetHealth(1);

		}
		if(Status.equals("winY")){ // set up data for boss battle
			Boss.LoadImg(bossC);// a smaller boss lol
			Boss.SetHealth(50);
			Boss.setY(916);
			Mbult.clear();// clean up
			bullyts.clear();
			Status = "win";// boss battle start again

		}
	}

}
/*these classes are special, which all of them has a same basic structure. (Bult is really
similar to monsterBul. but i want to seperate them.)
*/
class Ship{
	private int Posx, Posy,speed = 5,charging = 5; // points for the location
													// speed is moving speed
													// charge is how fast can u shot
	private int ShotTime;// make sure u have a CD on shoting.
	private int Lives=100; // all of the blocks was changed to health
	private Sprite Img;	// picture of ship
	public void SetHealth(int a ){ // cheating or trolling, set health for ur ship
		Lives = a;
	}
	public void hurt(){ // you were attacked and you lose 1 hp (may be more)
		Lives--;
	}
	public int GetL(){  // check how many lives do u have
		return Lives;
	}
	public boolean live(){
		return Lives>0; // die or not
	}
	public Sprite Getimg(){ // for checking rect boudingRectangle
		return Img;
	}
	public void LoadImg(Texture k){ // set imgs
		Img = new Sprite(k);
	}
	public int GetX(){ // find X point
		return Posx;
	}
	public int GetY(){//find Y point
		return Posy;
	}

	public void MoveRight(){ // i really dont think i nned to explain this.
		Posx+=speed;
	}
	public void MoveLeft(){
		Posx-=speed;
	}
	public void ReSet(){// you have fired, you cannot keep shooting, time to cooldown
		ShotTime = 0;
	}
	public void Charge(){ // charge your weapon (cooldown)
		ShotTime +=charging;
	}
	public boolean shot(){// you can shoot now
		if(ShotTime > 100){
			return true;
		}
		else{
			return false;
		}
	}
	public void ShowShip(SpriteBatch batch){ // draw it ;
		Img.setPosition((float)(Posx),(float)(Posy));
		Img.draw(batch);
	}
}
class Bult{
	private int Posx,Posy,Speed = 25; // moves really fast 25 pixels/frame
	private Texture Text;//looks cool, a little bit waste of ram.
	private Sprite Img;
	public int GetX(){ // this might be useful
		return Posx;
	}
	public int GetY(){
		return Posy;
	}
	public Sprite Getimg(){ // for BoundingRectangle
		return Img;
	}
	public void Set(int x,int y){// set point;
		Posx =x;
		Posy =y;
	}
	public void SetGift(Texture A){ // set the picture for sprite
		Text = A;
	}
	public void LoadImg(){  // make it
		Img = new Sprite(Text);
	}
	public void Moving(){ // CHARGE
		Posy += Speed;
	}
	public void ShowBullt(SpriteBatch batch){// draw it
		Img.setPosition((float)(Posx),(float)(Posy));
		Img.draw(batch);
	}
}
class MonsterBul{
	private int Posx,Posy,Speed = -5;// monster's bullet same as last one, but looks cool
									// and more waste of ram;
	private Texture Text;
	private Sprite Img;
	public int GetX(){
		return Posx;
	}
	public int GetY(){
		return Posy;
	}
	public Sprite Getimg(){
		return Img;
	}
	public void Set(int x,int y){
		Posx =x;
		Posy =y;
	}
	public void SetGift(Texture A){
		Text = A;
	}
	public void LoadImg(){
		Img = new Sprite(Text);
	}
	public void Moving(){
		Posy += Speed;
	}
	public void ShowBullt(SpriteBatch batch){
		Img.setPosition((float)(Posx),(float)(Posy));
		Img.draw(batch);
	}
	//same thing
}
class Monster{
	private int Posx,Posy,Speedx=50,Speedy=50,num=1;// monster's x,y and moving speed and which pic are they using
								// the pokemon's project gave me a reallly big help
	private Texture Text,preText; // let it spark during the game;
								// this will hurt your eye, you knew this
	private Sprite Img;
	public Sprite Getimg(){ // BoundingRectangle
		return Img;
	}
	public void Set(int x,int y){ // set position for x and y
		Posx =x;
		Posy =y;
	}
	public void SetImg(Texture a,Texture b){ // set the current img and the img which will spark
		Text = a;
		preText = b;
		Img = new Sprite(Text);
	}

	public void Move(){ // move
		Posx+=Speedx;
	}
	public void OtherWay(){ // touch and go to other way
		Speedx = -Speedx;
	}
	public void MoveDown(){ // down
		Posy-=Speedy;
	}
	public boolean Touch(){// touch or not
		if(Posx < 0||Posx>1000){ // 0 is left side window, 1000 is the right side
								// i really hate math
			return true;
		}
		else {
			return false;
		}
	}
	public void Bling(){ // shiny pic
		if(num==1){
			Img = new Sprite(preText);
			num =2;
		}
		else{
			Img = new Sprite(Text);
			num = 1;
		}
	}
	public void ShowMonster(SpriteBatch batch){ // draw it
		Img.setPosition((float)(Posx),(float)(Posy));
		Img.draw(batch);
	}
	public int Getx(){ // find Y and X point
		return Posx;
	}
	public int Gety(){
		return Posy;
	}
}
class Boss{
	private int Posx, Posy=440,speed = 40,charging = 60; // this is a really strong ship;
	private int ShotTime;
	private int Lives=100;
	private Sprite Img;
	public void Rage(){ // hope it doesnt range or it will shot as fast as it can;
		charging = 101;
	}
	public void setY(int a ){ // set y point
		Posy=a;
	}
	public void tp(){ // telepoting a short range
		if(Posx + 200 <1000){
			Posx+=200;
		}
		else if(Posx-200>0){
			Posx -=200;
		}
		else if(Posx-200<0){
			Posx = 0;
		}
		else if(Posx+200>1000){
			Posx =200;
		}
	}
	public void SetHealth(int a ){ // set hp
		speed = a;
	}
	public void hurt(){ // you can hurt boss
		Lives--;
	}
	public int GetL(){ // EVERYTHING IS THE SAME;
		return Lives;
	}
	public boolean live(){
		return Lives>0;
	}
	public Sprite Getimg(){
		return Img;
	}
	public void LoadImg(Texture k){
		Img = new Sprite(k);
	}
	public int GetX(){
		return Posx;
	}
	public int GetY(){
		return Posy;
	}

	public void MoveRight(){
		Posx+=speed;
	}
	public void MoveLeft(){
		Posx-=speed;
	}
	public void ReSet(){
		ShotTime = 0;
	}
	public void Charge(){
		ShotTime +=charging;
	}
	public boolean shot(){
		if(ShotTime > 100){
			return true;
		}
		else{
			return false;
		}
	}
	public void ShowShip(SpriteBatch batch){
		Img.setPosition((float)(Posx),(float)(Posy));
		Img.draw(batch);
	}
}
// libgdx provides an method or funtion called "overlaps" this can check whether 2 rects are overlaped or not
// a rect has x, y (left bottom point),width and height( sprite's height and width