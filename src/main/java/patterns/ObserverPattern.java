package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Определяет зависимость типа «один ко многим» между объектами таким образом, что при изменении
 * состояния одного объекта все зависящие от него оповещаются об этом событии.
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
            System.out.printf("¬і¬Ц¬Ы¬й¬С¬г ¬Щ¬Я¬С¬й¬Ц¬Я¬Ъ¬с: %.1f ¬Ф¬в¬С¬Х¬е¬г¬а¬У ¬и¬Ц¬Э¬о¬г¬Ъ¬с\n", temperature);
        }
    }

    public static void main(String[] args) {
        ObserverPattern instance = new ObserverPattern();
        WeatherData weatherData = instance.new WeatherData();
        CurrentConditionsDisplay currentDisplay = instance.new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(29);
    }

}
