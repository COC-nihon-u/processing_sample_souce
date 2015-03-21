import SimpleOpenNI.*;
SimpleOpenNI kinect;

int closeetValue;
int clossetX;
int clossetY;

void setup(){
	size(640,480);
	kinect = new SimpleOpenNI(this);
	kinect.enableDepth();
}

void draw(){
	closeetValue = 8000;
	kinect.update();

	int[] depthValues = kinect.depthMap();

	for(int y = 0; y < 480; y++){
		for(int x = 0; x < 640; x++){
			int i = x + y * 640;
			int currrentDepthValue = depthValues[i];

			if(currrentDepthValue > 0 && currrentDepthValue < closeetValue){
				closeetValue = currrentDepthValue;
				clossetX = x;
				clossetY = y;
			}
		}
	}

image(kinect.depthImage(),0,0);


fill(255,0,0);
ellipse(clossetX, clossetY, 25, 25);
}