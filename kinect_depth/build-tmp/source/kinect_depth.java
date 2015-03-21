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

public class kinect_depth extends PApplet {

//\u30e9\u30a4\u30d6\u30e9\u30ea\u3092import\u3059\u308b\u30c9\u30f3

SimpleOpenNI kinect;//\u30e9\u30a4\u30d6\u30e9\u30ea\u3092\u4f7f\u3063\u305f\u30a4\u30f3\u30b9\u30bf\u30f3\u30b9\u540d\u3092\u6c7a\u3081\u308b\u30c9\u30f3

public void setup()
{
	size(640,480);//\u753b\u9762\u306e\u30b5\u30a4\u30ba\u3092\u6307\u5b9a\u3059\u308b\u30c9\u30f3\u3001kinect\u306f\u901a\u5e38640*480\u304c\u666e\u901a\u3060\u3069\u3093
	kinect = new SimpleOpenNI(this);//kinect\u3092\u30a4\u30f3\u30b9\u30bf\u30f3\u30b9\u5316\u3059\u308b
	kinect.enableDepth();//\u6df1\u3055\u3092\u56f3\u308b\u30e9\u30a4\u30d6\u30e9\u30ea\u306b\u30a2\u30af\u30bb\u30b9\u3059\u308b\u30c9\u30f3
	//kinect.enableRGB();//\u8272\u60c5\u5831\u306fkinect\u304c\u6271\u3046\u306e\u304c\u82e6\u624b\u306a\u306e\u3067\u3084\u3081\u308b\u30c9\u30f3

}

public void draw()
{
	kinect.update();//\u65b0\u3057\u3044\u30c7\u30fc\u30bf\u3092kinect\u304b\u3089\u53d6\u5f97\u3057\u3001\u4e8b\u524d\u306b\u5ba3\u8a00\u3057\u305f\u30e9\u30a4\u30d6\u30e9\u30ea\u306e\u30c7\u30fc\u30bf\u3092\u53d6\u5f97\u3059\u308b
	PImage depthImage = kinect.depthImage();
	//PImage rgbImage = kinect.rgbImage();

	image(depthImage,0,0);
	//image(rgbImage, 640,0);
}

public void mousePressed()
{

	int [] depthValuew = kinect.depthMap();//depth\u306e1\u6b21\u5143\u306e\u914d\u5217\u306b\u30a2\u30af\u30bb\u30b9\u3057\u3066\u5024\u3092\u683c\u7d0d\u3059\u308b
	int clickPosition = mouseX + (mouseY * 640);//1\u6b21\u5143\u306e\u914d\u5217\u304b\u3089\u3001\u30de\u30a6\u30b9\u306e\u4f4d\u7f6e\u306e\u5024\u3092\u8a08\u7b97\u3059\u308b
	int clickDepth = depthValuew[clickPosition];//\u30af\u30ea\u30c3\u30af\u3057\u305f\u30dd\u30b8\u30b7\u30e7\u30f3\u306e\u914d\u5217\u306e\u6570\u3092\u53d6\u308a\u51fa\u3059\u3002mm\u3067\u51fa\u3066\u304d\u3066\u308b\u30ab\u30ca\uff1f
	float inches = clickDepth /25.4f;//\u30a4\u30f3\u30c1\u306b\u76f4\u3059\u305f\u3081\u300125.4\u3067\u5272\u308b

	println("inches: "+inches + "   millimeter: " + clickDepth);


}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "kinect_depth" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
