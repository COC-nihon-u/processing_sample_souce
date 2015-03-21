//最も近いピクセルに丸を表示するプログラム

import SimpleOpenNI.*;
SimpleOpenNI kinect;

int closeetValue;//長さ確認用変数
int clossetX;//最も距離が短いときのX座標を格納する
int clossetY;//最も距離が短いときのY座標を格納する

int previousX;
int previousY;


void setup(){
	size(640,480);
	kinect = new SimpleOpenNI(this);//kinect をインスタンス化
	kinect.enableDepth();
}

void draw(){
	closeetValue = 8000;//ループに入る前に前回の値を捨てるため8000を入れる。
	kinect.update();//kinectから新しいデータを取り出す
	int[] depthValues = kinect.depthMap();//距離画像配列にアクセスする

//距離画像の列ごとに
	for(int y = 0; y < 480; y++){
		//列の中のピクセルを1つずつ見る
		for(int x = 0; x < 640; x++){
			//2次元の座標の値を1次元配列に変換する
			int i = x + y * 640;
			//ここで変換した値を元に、配列にアクセスして、値をもらう
			int currrentDepthValue = depthValues[i];
//0より小さい値と8000以上のデータは以下のif文で取り除く
			if(currrentDepthValue > 0 && currrentDepthValue < closeetValue){
				//ここで8000より小さい値を格納していく
				closeetValue = currrentDepthValue;
				//小さかったときのx,y座標を保存する
				clossetX = x;
				clossetY = y;
			}
		}
	}
//距離画像を画面に表示する
//image(kinect.depthImage(),0,0);
//描画色を赤にする
stroke(255, 0,0);

line(previousX,previousY,clossetX,clossetY);//ラインを描画する

//現在の最も近い点を新たな直前の点として保存する
previousX=clossetX;//ここに前回のpreviousを渡す
previousY=clossetY;

}