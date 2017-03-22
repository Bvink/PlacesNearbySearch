package placesnearbysearch;

import placesnearbysearch.classes.PlacesService;

public class Find {

    //Give: business_type, radius, latitude, longitude

    public String findNearbyBusinesses(String businessType, int radius, double latitude, double longitude) {
        PlacesService placesService = new PlacesService(businessType, radius);
        return placesService.nearbySearch(latitude, longitude).toString();

    }

}