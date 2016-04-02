package Routru;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nisargap on 3/21/16.
 */
public class Places {

    // Member variables for places
    private String location;
    private String type;
    private String query;
    private List<Place> placesList;

    Places(String location, String type, String query){

        this.location = location;
        this.type = type;
        this.query = query;
        this.placesList = new ArrayList<>();

    }

    public void addPlace(Place somePlace){

        this.placesList.add(somePlace);

    }
    public List<Place> getPlacesList(){

        return placesList;

    }

    public String getLocation(){

        return this.location;

    }
    public String getType(){

        return this.type;

    }
    public String getQuery(){

        return this.query;

    }

    public void setPlacesList(List<Place> placesList){
        this.placesList = placesList;
    }
}
