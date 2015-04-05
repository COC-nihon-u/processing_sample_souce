//最も近いピクセルに丸を表示するプログラム
import processing.opengl.*;
import SimpleOpenNI.*;
SimpleOpenNI kinect;

void setup(){
	size(640,480,OPENGL);
	kinect = new SimpleOpenNI(this);//kinect をインスタンス化
	kinect.enableDepth();
}

void draw(){
	background(0);
	kinect.update();//kinectから新しいデータを取り出す
	//X軸とY軸を中央に、Z軸は1000ピクセル
	translate(width/2, height/2, -1000);
	//y軸を反転させる
	rotateX(radians(180));
	stroke(255);
	PVector[] depthPoints = kinect.depthMapRealWorld();
	for(int i = 0; i < depthPoints.length; i++){
		PVector currentpoint = depthPoints[i];
		point(currentpoint.x,currentpoint.y,currentpoint.z);
	}
}

