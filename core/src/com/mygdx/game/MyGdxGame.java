package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
public class MyGdxGame extends ApplicationAdapter{
	BitmapFont font;

	SpriteBatch batch;
	Texture img;
	Sprite img2;
	Sprite img3;
	ArrayList<Building> img4 = new ArrayList<Building>();

	Texture jianzaolan;
	Unit BadTest = new Unit();
	///mouse stuff
	Rectangle mouseRect;
	int xPos,yPos;
	Building selectedBuilding;

	Player Tester = new Player();

	Weapon testWeapon = new Weapon();
	@Override
	public void create () {
		font = new BitmapFont();
		font.getData().setScale(2f);

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 =new Sprite( new Texture("badlogic.jpg"));
		img3 =new Sprite( new Texture("badlogic.jpg"));

		Sprite Test = new Sprite(img);
		jianzaolan = new Texture("construction.png");
		batch.getProjectionMatrix().setToOrtho2D(0,0,1920,1080);
		BadTest.loadUnits(50,"LIGHT",Test,"NOWEAPON");


		xPos = Gdx.graphics.getWidth() / 2;
		yPos = Gdx.graphics.getHeight() / 2;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		xPos = Gdx.input.getX();
		yPos =  Gdx.input.getY();

		img2.setSize(1760-1594,598-495);
		img3.setSize(1919-1760,600-498);

		if (xPos > 800 && yPos > 800 &&xPos<1596) {
			for(int i = 0;i<img4.size();i++){
				img4.get(i).setPosition(img4.get(i).getSprite().getX()-5,img4.get(i).getSprite().getX()+5);
			}
			BadTest.setPosition(BadTest.getSprite().getX() -5, BadTest.getSprite().getY() +5 );
		}else if(xPos<100&&yPos<100){
			for(int i = 0;i<img4.size();i++){
				img4.get(i).setPosition(img4.get(i).getSprite().getX()+5,img4.get(i).getSprite().getX()-5);
			}
			BadTest.setPosition(BadTest.getSprite().getX() +5, BadTest.getSprite().getY() -5);
		}else if(xPos<100&&yPos>800){
			for(int i = 0;i<img4.size();i++){
				img4.get(i).setPosition(img4.get(i).getSprite().getX()+5,img4.get(i).getSprite().getX()+5);
			}
			BadTest.setPosition(BadTest.getSprite().getX() +5, BadTest.getSprite().getY() +5);
		}else if(xPos>800&&yPos<100&&xPos<1596){
			for(int i = 0;i<img4.size();i++){
				img4.get(i).setPosition(img4.get(i).getSprite().getX()-5,img4.get(i).getSprite().getX()-5);
			}
			BadTest.setPosition(BadTest.getSprite().getX() -5, BadTest.getSprite().getY() -5);
		}

		//System.out.print(xPos+"  ");
		//System.out.println(yPos);
		img2.setPosition(1594,495);
		img3.setPosition(1760,498);



		batch.begin();
		mouseRect = new Rectangle(xPos,yPos,1,1);
		batch.draw(jianzaolan,1596,0);
		if(BadTest.getInScreen()) {
			BadTest.showUnit(batch);
		}
		img2.draw(batch);
		img3.draw(batch);
		if(mouseRect.overlaps(new Rectangle(img2.getBoundingRectangle()))&&Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			System.out.println("ture");
			selectedBuilding = new Building(100,"Test",0,0,"null",img2,testWeapon);

		}
		if(mouseRect.overlaps(new Rectangle(img3.getBoundingRectangle()))&&Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			selectedBuilding = new Building(100,"Test",0,0,"null",img3,testWeapon);
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&xPos>0&&xPos<1594&&yPos>0&&yPos<1080){
			selectedBuilding.setPosition(xPos,Math.abs(yPos-1080));
			Tester.addBuilding(selectedBuilding);
			selectedBuilding = null;
		}
		//BadTest.moveTo(50,50,batch);



		font.draw(batch, ""+xPos+", "+yPos, 0, 1080);
		//font.draw(batch, ""+(selectedBuilding.equals(img2)), 0, 1050);
		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
