package Routru;


import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by nisargap on 3/21/16.
 * Copyright 2016 Routru
 * Filename: PlacesController.java
 * Package: Routru
 * Dependencies: Places.java, Place.java, User.java
 * Description: This is the controller for all places related routes. This class includes all route definitions.
 * For a basic guide on creating a REST API with the Spring framework checkout this link: https://spring.io/guides/gs/rest-service/
 **/

@CrossOrigin
@RestController
public class PlacesController {



    /**
     * Before State: Valid location, type, and query parameters are inputted.
     * @param location
     * @param type
     * @param query
     * @return Places Object that contains data regarding the query
     * After State: The user is presented with valid places data
     */


    @RequestMapping(value = "/searchPlaces", method = RequestMethod.GET)
    public Places searchPlaces(@RequestParam(value="location", defaultValue="") String location,
                               @RequestParam(value="type", defaultValue="") String type,
                               @RequestParam(value="offset", defaultValue="0") int offset,
                               @RequestParam(value="query", defaultValue="") String query) throws IOException {

        try {
            Places results = new Places(location, type, query);

            // return some fakeData for now on this API call

            // TODO: Query an API grab the data format it into the Places list as Place Objects
            // TODO: Format placesList by type i.e. make it into a HashMap with the key being type

            // Predictions regarding a place from Google Autocomplete
            List<net.sf.sprockets.google.Place.Prediction> predictions = net.sf.sprockets.google.Places.queryAutocomplete(net.sf.sprockets.google.Places.Params.create().query(location)).getResult();

            // Check if predictions is empty
            if (!predictions.isEmpty() && !query.isEmpty()) {

                // Modify a query if it exists
                query += " near " + predictions.get(0).getDescription();

            } else if (!predictions.isEmpty() && query.isEmpty()) {

                query += predictions.get(0).getDescription();

            } else {

                query = "";
            }

            // Setting the retrieved places
            List<net.sf.sprockets.google.Place> retrievedPlaces = net.sf.sprockets.google.Places.textSearch(net.sf.sprockets.google.Places.Params.create().query(query).type(type).offset(offset).maxResults(60)).getResult();

            // Set places list that is part of the homemade Places object

            if (retrievedPlaces.isEmpty()) {
                // Offline mode only: results.setPlacesList(fakeData());
                return results;
            }

            results.setPlacesList(formatGoogleResults(retrievedPlaces));

            return results;
        }catch(Exception e){

            Places results = new Places(location, type, query);

            // Offline mode only: results.setPlacesList(fakeData());

            return results;

        }
    }


    // Formats raw data from Google into nice Place object

    private List<Place> formatGoogleResults(List<net.sf.sprockets.google.Place> rawResults){

        List<Place> results = new ArrayList<>();
        for(net.sf.sprockets.google.Place place : rawResults){

            Place current = new Place();

            // set the Routru place object information
            current.setId(place.getPlaceId().hashCode());
            current.setGooglePlacesId(place.getPlaceId());
            current.setName(place.getName());
            current.setAddress(place.getFormattedAddress());
            current.setTags(place.getTypes());
            current.setPhone(place.getFormattedPhoneNumber());
            current.setRating(place.getRating());


            try {
                current.setMapsURL("https://www.google.com/maps/place/" + URLEncoder.encode(current.getAddress(), UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            List<net.sf.sprockets.google.Place.Photo> photos = place.getPhotos();
            List<String> urls = new ArrayList<>();

            for(net.sf.sprockets.google.Place.Photo photo : photos){
                urls.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+photo.getReference()+"&sensor=false&key=AIzaSyAcQA2flrhp3_2VK6TTQZQtvQdp4Zf-jnE");

            }

            current.setImagesURLs(urls);

            current.setCostValue(place.getPriceLevel());
            current.setDescription("");
            current.setCreator("Google");
            current.setSource("Google");

            results.add(current);
        }
        return results;
    }


    // This is a temporary function to just return some fakeData
    private ArrayList<Place> fakeData(){

        ArrayList<Place> placesToAdd = new ArrayList<>();

        Map<String, Object> placeParams = new HashMap<>();
        placeParams.put("id", 1);
        placeParams.put("address", "1000 Hilltop Circle, Baltimore, MD 21250");
        placeParams.put("name", "University of Maryland Baltimore County");
        placeParams.put("phone", "410-455-1000");
        placeParams.put("description", "UMBC is an American public research university, located in Baltimore County, Maryland, United States, mostly in the community of Catonsville, approximately 10 minutes (8.3 miles) from downtown Baltimore City and 30 minutes (33.5 miles) from Washington, D.C. ");

        List<String> imagesURLs = new ArrayList<>();
        imagesURLs.add("https://upload.wikimedia.org/wikipedia/commons/6/66/UMBC_Seal.png");
        imagesURLs.add("https://www.google.com/maps/vt/data=RfCSdfNZ0LFPrHSm0ublXdzhdrDFhtmHhN1u-gM,NOPkK0G89g11qjNqFNQ8y75qHqVBOqIgOgw_cN5MBJzpoi6BV0--IbkYPFqyUT-bgNOQIovl78KsDVWpMpyzOV5Ho2dMgEeP1GcmKqHiN8Q4eL_6PIOMkoYfziXEJjWVkakdy2qbOhIFsw");
        imagesURLs.add("http://womenscenter.umbc.edu/files/2015/06/xxxlarge-7.jpg");

        placeParams.put("imagesURLs", imagesURLs);
        placeParams.put("category", "School");
        placeParams.put("creator", "Nisarga");
        placeParams.put("source", "manual");
        placeParams.put("costValue", 2);
        Place umbc = new Place(placeParams);

        // -------------------------------------------------------------------------

        placeParams.clear();
        placeParams.put("id", 2);
        placeParams.put("address", "622 Frederick Rd, Catonsville, MD 21228");
        placeParams.put("name", "Indian Delight");
        placeParams.put("phone", "410-744-4422");
        placeParams.put("description", "Relaxed, family-owned Indian place features tandoori fare, vegetarian dishes & a daily lunch buffet.");

        imagesURLs = new ArrayList<>();
        imagesURLs.add("http://vp.cdn.cityvoterinc.com/GetImage.ashx?img=00/00/00/01/21/05/12105-51064.jpg&ar=maintain");
        imagesURLs.add("http://vp.cdn.cityvoterinc.com/GetImage.ashx?img=00/00/00/01/21/05/12105-51069.jpg&ar=maintain");
        imagesURLs.add("http://s3-media3.fl.yelpcdn.com/bphoto/wSfeo1gtcBiPvB0mtcNsvw/348s.jpg");

        placeParams.put("imagesURLs", imagesURLs);
        placeParams.put("category", "Restaurant");
        placeParams.put("creator", "Nisarga");
        placeParams.put("source", "manual");
        placeParams.put("costValue", 3);

        Place indianDelight = new Place(placeParams);

        // -------------------------------------------------------------------------

        placeParams.clear();
        placeParams.put("id", 3);
        placeParams.put("address", "501 E Pratt St, Baltimore, MD 21202");
        placeParams.put("name", "National Aquarium");
        placeParams.put("phone", "410-576-3800");
        placeParams.put("description", "The National Aquarium is a non-profit public aquarium located at 501 E Pratt Street in the Inner Harbor area of Baltimore, Maryland in the United States.");

        imagesURLs = new ArrayList<>();
        imagesURLs.add("https://lh4.googleusercontent.com/-iYsnqcMplxk/AAAAAAAAAAI/AAAAAAAAUAE/mExXAvME-7c/s0-c-k-no-ns/photo.jpg");
        imagesURLs.add("https://www.google.com/maps/vt/data=RfCSdfNZ0LFPrHSm0ublXdzhdrDFhtmHhN1u-gM,xaoCXlKii7S0jqtlDBcIfqOwC5ryOdcoX7qDvUuN6dcB1L6i9qvIwy8WaXG0sZkuNINHRBtesud42cIVXRmbhmAQiPPn5gLqtC7J3tOHydGLwfg5bu_dq1IL0KHsvC0JGrxhNCrZUuRIHw");

        placeParams.put("imagesURLs", imagesURLs);
        placeParams.put("category", "Attractions");
        placeParams.put("creator", "Nisarga");
        placeParams.put("source", "manual");
        placeParams.put("costValue", 3);

        Place baltimoreAquarium = new Place(placeParams);

        // -------------------------------------------------------------------------


        placesToAdd.add(umbc);
        placesToAdd.add(indianDelight);
        placesToAdd.add(baltimoreAquarium);

        return placesToAdd;

    }
}
