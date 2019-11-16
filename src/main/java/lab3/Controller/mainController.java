package lab3.Controller;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import lab3.Observable.RedGreenLight;
import lab3.Observer.Car;
import lab3.Observer.Observer;
import lab3.Observer.Person;
import lab3.Service.ObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class mainController {

    @Autowired
    RedGreenLight redGreenLight;

    @Autowired
    ObserverService observerService;

    private List<Observer> observers = new ArrayList<Observer>();

    @RequestMapping("/")
    public String index(Map<String, Object> map) {

        if (observers.size() == 0) {
            observers.addAll(observerService.getAllObserver());
            for (Observer i : observers) {
                redGreenLight.addObserver(i);
                i.setObservable(redGreenLight);
            }
        }

        map.put("msg", observers);
        return "index";
    }

    @ResponseBody
    @GetMapping("/add")
    public String add(String name, Integer position, String type) {
        System.out.println(name);
        System.out.println(position);
        System.out.println(type);
        Observer observer = observerService.createObserver(name, position, type);
        observers.add(observer);
        redGreenLight.addObserver(observer);
        observer.setObservable(redGreenLight);
        observerService.StoreObserver(observer);
        return "ok";
    }

}
