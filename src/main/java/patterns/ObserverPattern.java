package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * ¬³¬à¬Ù¬Õ¬Ñ¬Ö¬ä ¬Þ¬Ö¬ç¬Ñ¬ß¬Ú¬Ù¬Þ ¬å ¬Ü¬Ý¬Ñ¬ã¬ã¬Ñ, ¬Ü¬à¬ä¬à¬â¬í¬Û ¬á¬à¬Ù¬Ó¬à¬Ý¬ñ¬Ö¬ä ¬á¬à¬Ý¬å¬é¬Ñ¬ä¬î ¬ï¬Ü¬Ù¬Ö¬Þ¬á¬Ý¬ñ¬â¬å ¬à¬Ò¬ì¬Ö¬Ü¬ä¬Ñ ¬ï¬ä¬à¬Ô¬à ¬Ü¬Ý¬Ñ¬ã¬ã¬Ñ ¬à¬á¬à¬Ó¬Ö¬ë¬Ö¬ß¬Ú¬ñ
 * ¬à¬ä ¬Õ¬â¬å¬Ô¬Ú¬ç ¬à¬Ò¬ì¬Ö¬Ü¬ä¬à¬Ó ¬à¬Ò ¬Ú¬Ù¬Þ¬Ö¬ß¬Ö¬ß¬Ú¬Ú ¬Ú¬ç ¬ã¬à¬ã¬ä¬à¬ñ¬ß¬Ú¬ñ, ¬ä¬Ö¬Þ ¬ã¬Ñ¬Þ¬í¬Þ ¬ß¬Ñ¬Ò¬Ý¬ð¬Õ¬Ñ¬ñ ¬Ù¬Ñ ¬ß¬Ú¬Þ¬Ú.
 */
public class ObserverPattern {

    interface Observer {
        void update(float temperature);
    }

    interface Observable {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }

    class WeatherData implements Observable {
        private List<Observer> observers;
        private float temperature;

        public WeatherData() {
            observers = new ArrayList<Observer>();
        }

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer: observers)
                observer.update(temperature);
        }

        public void setMeasurements(float temperature) {
            this.temperature = temperature;
            notifyObservers();
        }
    }

    class CurrentConditionsDisplay implements Observer {
        private float temperature;
        private WeatherData weatherData;

        public CurrentConditionsDisplay(WeatherData weatherData) {
            this.weatherData = weatherData;
            weatherData.registerObserver(this);
        }

        @Override
        public void update(float temperature) {
            this.temperature = temperature;
            display();
        }

        public void display() {
            System.out.printf("¬³¬Ö¬Û¬é¬Ñ¬ã ¬Ù¬ß¬Ñ¬é¬Ö¬ß¬Ú¬ñ: %.1f ¬Ô¬â¬Ñ¬Õ¬å¬ã¬à¬Ó ¬è¬Ö¬Ý¬î¬ã¬Ú¬ñ\n", temperature);
        }
    }

    public static void main(String[] args) {
        ObserverPattern instance = new ObserverPattern();
        WeatherData weatherData = instance.new WeatherData();
        CurrentConditionsDisplay currentDisplay = instance.new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(29);
    }

}
