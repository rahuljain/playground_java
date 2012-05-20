/**
 * 
 */
package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rahul
 *
 */
public class Question2 {
	//static final double EARTH_RADIUS = 6371.0; //radius of earth in km.
	private static final double EARTH_CIRC_METERS = 40030218; // Circumference in meters.
	private static final String fileName = "ITAFC_Final_Challenge_Part2_data.txt";

	/**
	 * @param lat1 - Latitude of origin point in decimal degrees
     * @param lon1 - longitude of origin point in decimal degrees
     * @param lat2 - latitude of destination point in decimal degrees
     * @param lon2 - longitude of destination point in decimal degrees
     * 
     * @return metricDistance - great circle distance in meters
     */
	private double  GreatCircleDistance(double lat1, double lon1, double lat2, double lon2){
        double radLat1 = Math.toRadians( lat1 );
        double radLon1 = Math.toRadians( lon1 );
        double radLat2 = Math.toRadians( lat2 );
        double radLon2 = Math.toRadians( lon2 );
        double d = Math.acos( ( Math.cos( radLat1 ) * Math.cos( radLat2 ) ) + ( Math.sin( radLat1 ) * Math.sin( radLat2 ) ) * ( Math.cos( radLon1 - radLon2 ) ) );
        return ( d * EARTH_CIRC_METERS );
    }

	private ArrayList<String> readTextFile(String fileName) {
		if ((fileName == null) || (fileName == ""))
            throw new IllegalArgumentException();
        
        String line;
        ArrayList<String> file = new ArrayList<String>();
        try
        {    
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            if (!in.ready())
                throw new IOException();
            while ((line = in.readLine()) != null)
                file.add(line);
            in.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            return null;
        }
        return file;
	}
	
	public int getIdOfClosestRestaurant(double dinerLongitude, double dinerLatitude) {
		int closestRestaurantID = -1;
		double closestDistance = -1.00;
		ArrayList<String> file = readTextFile(fileName);
		String delims = "[,]"; 
		for(String s : file) {
			String[] tokens = s.split(delims);
			double distance = GreatCircleDistance(dinerLatitude, dinerLongitude, Double.parseDouble(tokens[3]), Double.parseDouble(tokens[2]));
			if(distance == -1.00) {
				closestDistance = distance;
				closestRestaurantID = Integer.parseInt(tokens[0]);
			}
			else if(distance < closestDistance) {
				closestDistance = distance;
				closestRestaurantID = Integer.parseInt(tokens[0]);
			}
		}
		return closestRestaurantID;
	}
}
