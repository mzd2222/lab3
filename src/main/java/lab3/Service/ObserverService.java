package lab3.Service;

import lab3.Dao.CarDao;
import lab3.Dao.PersonDao;
import lab3.Observer.Car;
import lab3.Observer.Observer;
import lab3.Observer.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObserverService {

    @Autowired
    CarDao carDao;
    @Autowired
    PersonDao personDao;

    public List getAllObserver() {

        List<Observer> observers = new ArrayList<Observer>();

        for (Car i : carDao.findAll()) {
            observers.add(i);
        }

        for (Person i : personDao.findAll()) {
            observers.add(i);
        }

//        System.out.println(observers.size());
        return observers;
    }

    public void StoreObserver(Observer observer) {
        if (observer != null) {
            if (observer.getType().equals("car")) {
                carDao.save((Car) observer);
            } else {
                personDao.save((Person) observer);
            }
        }
    }

    public Observer createObserver(String name, Integer position, String type) {
        if (type.equals("car")) {
            Car car = new Car();
            car.setName(name);
            car.setPosition(position);
            return car;
        }
        if (type.equals("person")) {
            Person person = new Person();
            person.setName(name);
            person.setPosition(position);
            return person;
        } else {
            return null;
        }
    }

}
