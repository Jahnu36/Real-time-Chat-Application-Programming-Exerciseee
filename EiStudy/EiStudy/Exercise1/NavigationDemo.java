import java.util.HashMap;
import java.util.Map;

class City {
    String name;
    double latitude;
    double longitude;

    City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

interface RouteStrategy {
    void buildRoute(City start, City end);
}

class FastestRouteStrategy implements RouteStrategy {
    @Override
    public void buildRoute(City start, City end) {
        double distance = DistanceCalculator.calculateDistance(start, end);
        System.out.printf("Building fastest route from %s to %s. Distance: %.2f km%n", 
                          start.name, end.name, distance);
        System.out.println("Estimated time: " + (distance / 100) + " hours (assuming 100 km/h average speed)");
    }
}

class ShortestRouteStrategy implements RouteStrategy {
    @Override
    public void buildRoute(City start, City end) {
        double distance = DistanceCalculator.calculateDistance(start, end);
        System.out.printf("Building shortest route from %s to %s. Distance: %.2f km%n", 
                          start.name, end.name, distance);
    }
}

class AvoidTollsRouteStrategy implements RouteStrategy {
    @Override
    public void buildRoute(City start, City end) {
        double distance = DistanceCalculator.calculateDistance(start, end) * 1.2; // Assume 20% longer to avoid tolls
        System.out.printf("Building route avoiding tolls from %s to %s. Distance: %.2f km%n", 
                          start.name, end.name, distance);
    }
}

class NavigationApp {
    private RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void buildRoute(City start, City end) {
        if (routeStrategy == null) {
            throw new IllegalStateException("Route strategy must be set before building a route.");
        }
        routeStrategy.buildRoute(start, end);
    }
}

class DistanceCalculator {
    public static double calculateDistance(City city1, City city2) {
        final int R = 6371; // Radius of the earth in km

        double latDistance = Math.toRadians(city2.latitude - city1.latitude);
        double lonDistance = Math.toRadians(city2.longitude - city1.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(city1.latitude)) * Math.cos(Math.toRadians(city2.latitude))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}

public class NavigationDemo {
    private static Map<String, City> cities = new HashMap<>();

    static {
        cities.put("New York", new City("New York", 40.7128, -74.0060));
        cities.put("Los Angeles", new City("Los Angeles", 34.0522, -118.2437));
        cities.put("Chicago", new City("Chicago", 41.8781, -87.6298));
        cities.put("Houston", new City("Houston", 29.7604, -95.3698));
    }

    public static void main(String[] args) {
        NavigationApp nav = new NavigationApp();
        
        City start = cities.get("New York");
        City end = cities.get("Los Angeles");

        nav.setRouteStrategy(new FastestRouteStrategy());
        nav.buildRoute(start, end);
        
        nav.setRouteStrategy(new ShortestRouteStrategy());
        nav.buildRoute(start, end);
        
        nav.setRouteStrategy(new AvoidTollsRouteStrategy());
        nav.buildRoute(start, end);
    }
}
