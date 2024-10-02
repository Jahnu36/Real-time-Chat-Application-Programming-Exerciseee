import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Observer {
    void update(float temperature, float humidity, float pressure);
}

class WeatherData {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    private Random random;

    public WeatherData() {
        observers = new ArrayList<>();
        random = new Random();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements() {
        // Simulate real weather changes
        this.temperature = 20 + random.nextFloat() * 15; // 20-35 degrees Celsius
        this.humidity = 30 + random.nextFloat() * 70; // 30-100% humidity
        this.pressure = 970 + random.nextFloat() * 60; // 970-1030 hPa
        measurementsChanged();
    }
}

class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.printf("Current conditions: %.1f°C and %.1f%% humidity%n", temperature, humidity);
    }
}

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        weatherData.registerObserver(currentDisplay);

        System.out.println("Simulating weather changes over 10 time steps:");
        for (int i = 0; i < 10; i++) {
            weatherData.setMeasurements();
            try {
                Thread.sleep(2000); // Wait for 2 seconds between updates
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}