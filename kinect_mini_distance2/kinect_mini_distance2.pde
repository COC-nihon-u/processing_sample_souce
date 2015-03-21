import SimpleOpenNI.*;
SimpleOpenNI  kinect;

int closestX;
int closestY;

void setup()
{
  size(640, 480);
  kinect = new SimpleOpenNI(this);
  kinect.enableDepth();
}

void draw()
{
  //変数は初期化しないとエラー起きる
  int closestValue = 8000;
  int currentX = 0;
  int currentY= 0;
  kinect.update();//kinectから新しいデータを読み出す
  int[] depthValues = kinect.depthMap();//距離配列をkinectから読み取る
//距離画像の列ごとに
    for(int y = 0; y < 480; y++){
      //列の中のピクセルを一つずつ
      for(int x = 0; x < 640; x++){

        int i = x + y * 640;//2次元座標を1次元配列に変換する
        int currentDepthValue = depthValues[i];//ここで配列の値を読む
        //配列の中で、0より大きく8000より小さいものを保存していく

        if(currentDepthValue > 0 && currentDepthValue < closestValue){
          closestValue = currentDepthValue;
          currentX = x;
          currentY = y;
        }
      }
    }

//以下の処理を追加することで点が滑らかに表示される。
//と言っても一瞬画面に点が大量に残るドラゴン(バグ)が発生する
  closestX = (closestX + currentX) / 2;//ここで1つ前のループとの平均を出している
  closestY = (closestY + currentY) / 2;//ここで1つ前のループとの平均を出している
  image(kinect.depthImage(),0,0);
  fill(0,0,240);//色は青色にする
  ellipse(closestX, closestY, 25, 25);//円の表示場所と大きさを指定する。
}

