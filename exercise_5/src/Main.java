import java.util.ArrayList;
import java.util.List;

public class Main {
    // Singleton: WeatherStation
    private static class WeatherStation {
        private static final WeatherStation instance = new WeatherStation();
        private String weatherData = "Sunny, 25°C";

        private WeatherStation() {}

        public static WeatherStation getInstance() {
            return instance;
        }

        public String getWeatherData() {
            return weatherData;
        }

        public void setWeatherData(String weatherData) {
            this.weatherData = weatherData;
            System.out.println("Weather updated to: " + weatherData);
        }
    }

    // Observer: Device interface and concrete observers
    private interface Observer {
        void update(String weatherData);
    }

    private static class PhoneDisplay implements Observer {
        @Override
        public void update(String weatherData) {
            System.out.println("PhoneDisplay: Weather updated -> " + weatherData);
        }
    }

    private static class DesktopDisplay implements Observer {
        @Override
        public void update(String weatherData) {
            System.out.println("DesktopDisplay: Weather updated -> " + weatherData);
        }
    }

    // Factory: Alert system
    private static abstract class Alert {
        abstract void alertMessage();
    }

    private static class RainAlert extends Alert {
        @Override
        void alertMessage() {
            System.out.println("Rain Alert: Carry an umbrella!");
        }
    }

    private static class HeatAlert extends Alert {
        @Override
        void alertMessage() {
            System.out.println("Heat Alert: Stay hydrated!");
        }
    }

    private static class AlertFactory {
        public static Alert getAlert(String type) {
            if (type.equalsIgnoreCase("Rain")) return new RainAlert();
            else if (type.equalsIgnoreCase("Heat")) return new HeatAlert();
            return null;
        }
    }

    public static void main(String[] args) {
        // Singleton usage
        WeatherStation weatherStation = WeatherStation.getInstance();

        // Observer setup
        List<Observer> observers = new ArrayList<>();
        observers.add(new PhoneDisplay());
        observers.add(new DesktopDisplay());

        // Notify method for observer pattern
        Runnable notifyObservers = () -> {
            String weatherData = weatherStation.getWeatherData();
            for (Observer observer : observers) {
                observer.update(weatherData);
            }
        };

        // Simulate weather updates and notify observers
        weatherStation.setWeatherData("Rainy, 20°C");
        notifyObservers.run();

        // Factory usage for alerts
        Alert rainAlert = AlertFactory.getAlert("Rain");
        if (rainAlert != null) rainAlert.alertMessage();

        weatherStation.setWeatherData("Hot, 35°C");
        notifyObservers.run();

        Alert heatAlert = AlertFactory.getAlert("Heat");
        if (heatAlert != null) heatAlert.alertMessage();
    }
}