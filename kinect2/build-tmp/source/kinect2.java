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

public class kinect2 extends PApplet {

//\u30e9\u30a4\u30d6\u30e9\u30ea\u3092import\u3059\u308b\u30c9\u30f3

SimpleOpenNI kinect;

public void setup()
{
	size(640,480);
	kinect = new SimpleOpenNI(this);

	kinect.enableDepth();
	kinect.enableRGB();

}

public void draw()
{
	kinect.update();
	PImage depthImage = kinect.depthImage();
	PImage rgbImage = kinect.rgbImage();

	image(depthImage,0,0);
	image(rgbImage, 640,0);
}

public void mousePressed()
{
	int c = get(mouseX, mouseY);
	println("r:" + red(c) + "g" + green(c) + "b" + blue(c));
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "kinect2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
