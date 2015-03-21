import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import SimpleOpenNI.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class kinect_mini_distance extends PApplet {


SimpleOpenNI kinect;

int closeetValue;
int clossetX;
int clossetY;

public void setup(){
	size(640,480);
	kinect = new SimpleOpenNI(this);
	kinect.enableDepth();
}

public void draw(){
	closeetValue = 8000;
	kinect.update();

	int[] depthValues = kinect.depthMap();

	for(int y = 0; y < 480; y++){
		for(int x = 0; x < 640; x++){
			int i = x + y * 640;
			int currrentDepthValue = depthValues[i];

			if(currrentDepthValue > 0 && currrentDepthValue < closeetValue){
				closeetValue = currrentDepthValue;
				clossetX = x;
				clossetY = y;
			}
		}
	}

image(kinect.depthImage(),0,0);


fill(255,0,0);
ellipse(clossetX, clossetY, 25, 25);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "kinect_mini_distance" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
