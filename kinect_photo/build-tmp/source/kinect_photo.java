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

public class kinect_photo extends PApplet {

//\u6700\u3082\u8fd1\u3044\u30d4\u30af\u30bb\u30eb\u306b\u4e38\u3092\u8868\u793a\u3059\u308b\u30d7\u30ed\u30b0\u30e9\u30e0


SimpleOpenNI kinect;

int closeetValue;//\u9577\u3055\u78ba\u8a8d\u7528\u5909\u6570
int clossetX;//\u6700\u3082\u8ddd\u96e2\u304c\u77ed\u3044\u3068\u304d\u306eX\u5ea7\u6a19\u3092\u683c\u7d0d\u3059\u308b
int clossetY;//\u6700\u3082\u8ddd\u96e2\u304c\u77ed\u3044\u3068\u304d\u306eY\u5ea7\u6a19\u3092\u683c\u7d0d\u3059\u308b
//int previousX;
//int previousY;
float lastX;
float lastY;

float image1X;
float image1Y;

boolean image_Moving = false;
PImage image1;


public void setup(){
	size(640,480);
	kinect = new SimpleOpenNI(this);//kinect \u3092\u30a4\u30f3\u30b9\u30bf\u30f3\u30b9\u5316
	kinect.enableDepth();
	//kinect.enableRGB();
	//kinect.setMirror(true);//\u93e1\u753b\u50cf\u306b\u3059\u308b
	//smooth();
	image_Moving = true;
	image1 = loadImage("kasa3.jpg");

	background(0);//\u6700\u521d\u306f\u9ed2\u306e\u80cc\u666f\u3092\u4f7f\u3046
}

public void draw(){
	closeetValue = 8000;//\u30eb\u30fc\u30d7\u306b\u5165\u308b\u524d\u306b\u524d\u56de\u306e\u5024\u3092\u6368\u3066\u308b\u305f\u30818000\u3092\u5165\u308c\u308b\u3002
	kinect.update();//kinect\u304b\u3089\u65b0\u3057\u3044\u30c7\u30fc\u30bf\u3092\u53d6\u308a\u51fa\u3059
	int[] depthValues = kinect.depthMap();//\u8ddd\u96e2\u753b\u50cf\u914d\u5217\u306b\u30a2\u30af\u30bb\u30b9\u3059\u308b

//\u8ddd\u96e2\u753b\u50cf\u306e\u5217\u3054\u3068\u306b
	for(int y = 0; y < 480; y++){
		//\u5217\u306e\u4e2d\u306e\u30d4\u30af\u30bb\u30eb\u30921\u3064\u305a\u3064\u898b\u308b
		for(int x = 0; x < 640; x++){
			//\u753b\u50cf\u3092\u53f3\u304b\u3089\u59cb\u3081\u308b\u304b\u3089X\u8ef8\u3092\u53cd\u8ee2\u3055\u305b\u308b\u3002
			int reversedX = 640-x-1;
			//\u914d\u5217reversedX\u3092\u4f7f\u3063\u3066\u914d\u5217\u306e\u30a4\u30f3\u30c7\u30c3\u30af\u30b9\u3092\u6c42\u3081\u308b\u3002
			int i = reversedX + y * 640;
			int currrentDepthValue = depthValues[i];
//0\u3088\u308a\u5c0f\u3055\u3044\u5024\u30688000\u4ee5\u4e0a\u306e\u30c7\u30fc\u30bf\u306f\u4ee5\u4e0b\u306eif\u6587\u3067\u53d6\u308a\u9664\u304f
//610mm(2\u30d5\u30a3\u30fc\u3068)\u3088\u308a\u3082\u5927\u304d\u304f1525(5\u30d5\u30a3\u30fc\u3068)\u3088\u308a\u5c0f\u3055\u3044\u7bc4\u56f2\u306b\u7d5e\u308b
			if(currrentDepthValue > 610 && currrentDepthValue < 1525 && currrentDepthValue < closeetValue){
				//\u3053\u3053\u30678000\u3088\u308a\u5c0f\u3055\u3044\u5024\u3092\u683c\u7d0d\u3057\u3066\u3044\u304f
				closeetValue = currrentDepthValue;
				//\u5c0f\u3055\u304b\u3063\u305f\u3068\u304d\u306ex,y\u5ea7\u6a19\u3092\u4fdd\u5b58\u3059\u308b
				clossetX = x;
				clossetY = y;
			}
		}
	}

//image(kinect.rgbImage(),0,0);
//\u3053\u3053\u3067\u7dda\u5f62\u88dc\u5b8c\u3092\u884c\u3046
float interpolatedX = lerp(lastX, clossetX, 0.3f);
float interpolatedY = lerp(lastY, clossetY, 0.3f);

//\u80cc\u666f\u3092\u6d88\u53bb
background(0);
/*
stroke(255,0,0);
strokeWeight(3);
line(lastX,lastY,interpolatedX,interpolatedY);
*/
if(image_Moving){

image1X = interpolatedX;
image1Y = interpolatedY;
}

image(image1,image1X,image1Y);

lastX = interpolatedX;
lastY = interpolatedY;


}
public void mousePressed()
{
	//save("drawing.png");
	//background(0);
	image_Moving = !image_Moving;


}



  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "kinect_photo" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
