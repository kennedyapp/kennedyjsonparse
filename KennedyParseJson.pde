/*
Simple example to parse JSON data exported from Kennedy.

Displays cities around a circle
*/

import java.text.SimpleDateFormat;

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

JSONArray jsonArray;


void setup() {

	size(800,800);

	smooth();

	rectMode(LEFT);

	chooseFileForProcessing();

}

void draw() {

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

				a += inc;
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

void chooseFileForProcessing() {

	selectInput("Select a JSON file to process:", "fileSelected");

}

void fileSelected(File selection) {
  if (selection == null) {
    exit();
  } else {
    parseJSONFile(selection.getAbsolutePath());
  }
}

void parseJSONFile(String path) {

	JSONObject json = loadJSONObject(path);

	jsonArray = json.getJSONArray("kennedy");

}

