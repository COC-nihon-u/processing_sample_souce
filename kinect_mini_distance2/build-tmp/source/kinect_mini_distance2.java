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

public class kinect_mini_distance2 extends PApplet {


SimpleOpenNI  kinect;

int closestX;
int closestY;

public void setup()
{
  size(640, 480);
  kinect = new SimpleOpenNI(this);
  kinect.enableDepth();
}

public void draw()
{
  //\u5909\u6570\u306f\u521d\u671f\u5316\u3057\u306a\u3044\u3068\u30a8\u30e9\u30fc\u8d77\u304d\u308b
  int closestValue = 8000;
  int currentX = 0;
  int currentY= 0;
  kinect.update();//kinect\u304b\u3089\u65b0\u3057\u3044\u30c7\u30fc\u30bf\u3092\u8aad\u307f\u51fa\u3059
  int[] depthValues = kinect.depthMap();//\u8ddd\u96e2\u914d\u5217\u3092kinect\u304b\u3089\u8aad\u307f\u53d6\u308b
//\u8ddd\u96e2\u753b\u50cf\u306e\u5217\u3054\u3068\u306b
    for(int y = 0; y < 480; y++){
      //\u5217\u306e\u4e2d\u306e\u30d4\u30af\u30bb\u30eb\u3092\u4e00\u3064\u305a\u3064
      for(int x = 0; x < 640; x++){

        int i = x + y * 640;//2\u6b21\u5143\u5ea7\u6a19\u30921\u6b21\u5143\u914d\u5217\u306b\u5909\u63db\u3059\u308b
        int currentDepthValue = depthValues[i];//\u3053\u3053\u3067\u914d\u5217\u306e\u5024\u3092\u8aad\u3080
        //\u914d\u5217\u306e\u4e2d\u3067\u30010\u3088\u308a\u5927\u304d\u304f8000\u3088\u308a\u5c0f\u3055\u3044\u3082\u306e\u3092\u4fdd\u5b58\u3057\u3066\u3044\u304f

        if(currentDepthValue > 0 && currentDepthValue < closestValue){
          closestValue = currentDepthValue;
          currentX = x;
          currentY = y;
        }
      }
    }

//\u4ee5\u4e0b\u306e\u51e6\u7406\u3092\u8ffd\u52a0\u3059\u308b\u3053\u3068\u3067\u70b9\u304c\u6ed1\u3089\u304b\u306b\u8868\u793a\u3055\u308c\u308b\u3002
  closestX = (closestX + currentX) / 2;//\u3053\u3053\u30671\u3064\u524d\u306e\u30eb\u30fc\u30d7\u3068\u306e\u5e73\u5747\u3092\u51fa\u3057\u3066\u3044\u308b
  closestY = (closestY + currentY) / 2;//\u3053\u3053\u30671\u3064\u524d\u306e\u30eb\u30fc\u30d7\u3068\u306e\u5e73\u5747\u3092\u51fa\u3057\u3066\u3044\u308b

  image(kinect.depthImage(),0,0);


  fill(0,0,240);//\u8272\u306f\u9752\u8272\u306b\u3059\u308b
  ellipse(closestX, closestY, 25, 25);//\u5186\u306e\u8868\u793a\u5834\u6240\u3068\u5927\u304d\u3055\u3092\u6307\u5b9a\u3059\u308b\u3002
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "kinect_mini_distance2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
