import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.text.SimpleDateFormat; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class KennedyParseJson extends PApplet {



SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

JSONArray jsonArray;


public void setup() {

	size(800,800);

	smooth();

	rectMode(LEFT);

	chooseFileForProcessing();

}

public void draw() {

	background(255);
	translate(width/2,height/2);

	if (jsonArray != null) {

		float a = 0;
    	int distance = 300;
    	float amount = jsonArray.size();
    	float inc = (TWO_PI)/amount;
    	float rad;
    	float x;
    	float y;

			for (int i = 0; i < jsonArray.size(); i++) {

				a+=inc;
     			x = sin(a)*distance;
     			y = cos(a)*distance;
     			rad = atan2((0-y), (0-x));
			
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				pushMatrix();
    				rotate(rad);
    				fill(10);
    				textSize(11);
    				text(jsonObject.getString("city"),distance,0);
    			popMatrix();
				
				
				
			}
	}
	
}

public void chooseFileForProcessing() {

	selectInput("Select a JSON file to process:", "fileSelected");

}

public void fileSelected(File selection) {
  if (selection == null) {
    exit();
  } else {
    parseJSONFile(selection.getAbsolutePath());
  }
}

public void parseJSONFile(String path) {

	JSONObject json = loadJSONObject(path);

	jsonArray = json.getJSONArray("kennedy");

}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "KennedyParseJson" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
