import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

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

public class kinect_vector extends PApplet {

//\u6700\u3082\u8fd1\u3044\u30d4\u30af\u30bb\u30eb\u306b\u4e38\u3092\u8868\u793a\u3059\u308b\u30d7\u30ed\u30b0\u30e9\u30e0


SimpleOpenNI kinect;

public void setup(){
	size(640,480,OPENGL);
	kinect = new SimpleOpenNI(this);//kinect \u3092\u30a4\u30f3\u30b9\u30bf\u30f3\u30b9\u5316
	kinect.enableDepth();
}

public void draw(){
	background(0);
	kinect.update();//kinect\u304b\u3089\u65b0\u3057\u3044\u30c7\u30fc\u30bf\u3092\u53d6\u308a\u51fa\u3059
	//X\u8ef8\u3068Y\u8ef8\u3092\u4e2d\u592e\u306b\u3001Z\u8ef8\u306f1000\u30d4\u30af\u30bb\u30eb
	translate(width/2, height/2, -1000);
	//y\u8ef8\u3092\u53cd\u8ee2\u3055\u305b\u308b
	rotateX(radians(180));
	stroke(255);
	PVector[] depthPoints = kinect.depthMapRealWorld();
	for(int i = 0; i < depthPoints.length; i++){
		PVector currentpoint = depthPoints[i];
		point(currentpoint.x,currentpoint.y,currentpoint.z);
	}
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "kinect_vector" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
