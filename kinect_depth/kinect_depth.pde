//ライブラリをimportするドン
import SimpleOpenNI.*;
SimpleOpenNI kinect;//ライブラリを使ったインスタンス名を決めるドン

void setup()
{
	size(640,480);//画面のサイズを指定するドン、kinectは通常640*480が普通だどん
	kinect = new SimpleOpenNI(this);//kinectをインスタンス化する
	kinect.enableDepth();//深さを図るライブラリにアクセスするドン
	//kinect.enableRGB();//色情報はkinectが扱うのが苦手なのでやめるドン

}

void draw()
{
	kinect.update();//新しいデータをkinectから取得し、事前に宣言したライブラリのデータを取得する
	PImage depthImage = kinect.depthImage();
	//PImage rgbImage = kinect.rgbImage();

	image(depthImage,0,0);
	//image(rgbImage, 640,0);
}

void mousePressed()
{

	int [] depthValuew = kinect.depthMap();//depthの1次元の配列にアクセスして値を格納する
	int clickPosition = mouseX + (mouseY * 640);//1次元の配列から、マウスの位置の値を計算する
	int clickDepth = depthValuew[clickPosition];//クリックしたポジションの配列の数を取り出す。mmで出てきている
	float inches = clickDepth /25.4;//インチに直すため、25.4で割る

	println("inches: "+inches + "   millimeter: " + clickDepth);


}
