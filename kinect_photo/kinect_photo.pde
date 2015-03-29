//最も近いピクセルに丸を表示するプログラム

import SimpleOpenNI.*;
SimpleOpenNI kinect;

int closeetValue;//長さ確認用変数
int clossetX;//最も距離が短いときのX座標を格納する
int clossetY;//最も距離が短いときのY座標を格納する
//int previousX;
//int previousY;
float lastX;
float lastY;

float image1X;
float image1Y;

boolean image_Moving = false;
PImage image1;


void setup(){
	size(640,480);
	kinect = new SimpleOpenNI(this);//kinect をインスタンス化
	kinect.enableDepth();
	//kinect.enableRGB();
	//kinect.setMirror(true);//鏡画像にする
	//smooth();
	image_Moving = true;
	image1 = loadImage("kasa3.jpg");

	background(0);//最初は黒の背景を使う
}

void draw(){
	closeetValue = 8000;//ループに入る前に前回の値を捨てるため8000を入れる。
	kinect.update();//kinectから新しいデータを取り出す
	int[] depthValues = kinect.depthMap();//距離画像配列にアクセスする

//距離画像の列ごとに
	for(int y = 0; y < 480; y++){
		//列の中のピクセルを1つずつ見る
		for(int x = 0; x < 640; x++){
			//画像を右から始めるからX軸を反転させる。
			int reversedX = 640-x-1;
			//配列reversedXを使って配列のインデックスを求める。
			int i = reversedX + y * 640;
			int currrentDepthValue = depthValues[i];
//0より小さい値と8000以上のデータは以下のif文で取り除く
//610mm(2フィーと)よりも大きく1525(5フィーと)より小さい範囲に絞る
			if(currrentDepthValue > 610 && currrentDepthValue < 1525 && currrentDepthValue < closeetValue){
				//ここで8000より小さい値を格納していく
				closeetValue = currrentDepthValue;
				//小さかったときのx,y座標を保存する
				clossetX = x;
				clossetY = y;
			}
		}
	}

//image(kinect.rgbImage(),0,0);
//ここで線形補完を行う
float interpolatedX = lerp(lastX, clossetX, 0.3f);
float interpolatedY = lerp(lastY, clossetY, 0.3f);

//背景を消去
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
void mousePressed()
{
	//save("drawing.png");
	//background(0);
	image_Moving = !image_Moving;


}



