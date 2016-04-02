package Routru;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nisargap on 3/21/16.
 */
public class Place {

    // Member variables
    private int id;
    private net.sf.sprockets.google.Place.Id googlePlacesId;
    private String address;
    private String name;
    private String phone;
    private String description;
    private List<String> imagesURLs;
    private List<String> tags;
    private String creator;
    private String source;
    private String mapsURL;
    private float rating;
    private int costValue; // Constraint Between 1 - 5

    public Place(){

    }
    @SuppressWarnings("unchecked")  // Suppresses imagesURLs cast
    public Place(Map<String, Object> params){

        // TODO 1: Add validation here for the params Map maybe some assertions, perform some tests
        // TODO 2: Add Search Routra by place, i.e. the method will return all Routra that include a place

        // Setting the private variables from a parameters Map
        this.id = (int) params.get("id");
        this.googlePlacesId = (net.sf.sprockets.google.Place.Id) params.get("googlePlacesId");
        this.address = (String) params.get("address");
        this.name = (String) params.get("name");
        this.phone = (String) params.get("phone");
        this.description = (String) params.get("description");
        this.imagesURLs = (List<String>) params.get("imagesURLs");
        this.tags = (List<String>) params.get("tags");
        this.creator = (String) params.get("creator");
        this.source = (String) params.get("source");
        this.costValue = (int) params.get("costValue");
        this.mapsURL = (String) params.get("mapsURL");
        this.rating = (float) params.get("rating");

    }

    // Getters

    public String getMapsURL(){

        return this.mapsURL;
    }

    public int getId(){

        return this.id;

    }

    public net.sf.sprockets.google.Place.Id getGooglePlacesId(){

        return this.googlePlacesId;

    }
    public String getAddress(){

        return this.address;

    }

    public String getName(){

        return this.name;

    }

    public String getPhone(){

        return this.phone;
    }

    public String getDescription(){

        return this.description;

    }

    public List<String> getImagesURLs(){

        return this.imagesURLs;

    }

    public List<String> getTags(){

        return this.tags;
    }

    public String getCreator(){

        return this.creator;

    }

    public String getSource(){

        return this.source;

    }

    public int getCostValue(){

        return this.costValue;

    }

    public float getRating(){

        return this.rating;
    }

    // Setters

    public void setMapsURL(String mapsURL){

        this.mapsURL = mapsURL;
    }

    public void setId(int id){

        this.id = id;

    }

    public void setGooglePlacesId(net.sf.sprockets.google.Place.Id id){

        this.googlePlacesId = id;

    }

    public void setAddress(String address){

        this.address = address;

    }

    public void setName(String name){

        this.name = name;

    }

    public void setPhone(String phone){

        this.phone = phone;

    }

    public void setDescription(String description){

        this.description = description;

    }

    public void setImagesURLs(List<String> imagesURLs){

        this.imagesURLs = imagesURLs;

    }

    public void setTags(List<String> tags){

        this.tags = tags;

    }

    public void setCreator(String creator){

        this.creator = creator;
    }

    public void setSource(String source){

        this.source = source;

    }

    public void setCostValue(int costValue){

        this.costValue = costValue;
    }

    public void setRating(float rating){

        this.rating = rating;

    }
}
