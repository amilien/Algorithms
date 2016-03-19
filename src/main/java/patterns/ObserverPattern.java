package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * ����٬լѬ֬� �ެ֬�Ѭ߬ڬ٬� �� �ܬݬѬ���, �ܬ������ ���٬Ӭ�ݬ�֬� ���ݬ��Ѭ�� ��ܬ٬֬ެ�ݬ��� ��Ҭ�֬ܬ�� ����Ԭ� �ܬݬѬ��� ����Ӭ֬�֬߬ڬ�
 * ��� �լ��Ԭڬ� ��Ҭ�֬ܬ��� ��� �ڬ٬ެ֬߬֬߬ڬ� �ڬ� �������߬ڬ�, ��֬� ��Ѭެ�� �߬ѬҬݬ�լѬ� �٬� �߬ڬެ�.
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
            System.out.printf("���֬۬�Ѭ� �٬߬Ѭ�֬߬ڬ�: %.1f �Ԭ�Ѭլ���� ��֬ݬ��ڬ�\n", temperature);
        }
    }

    public static void main(String[] args) {
        ObserverPattern instance = new ObserverPattern();
        WeatherData weatherData = instance.new WeatherData();
        CurrentConditionsDisplay currentDisplay = instance.new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(29);
    }

}
