package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
   public static List<Dish> dishes = new ArrayList<>();
   @PostConstruct
    public void init() {
       chefs=new ArrayList<>();
       dishes=new ArrayList<>();
       chefs.add(new Chef(0L,"Marko","Markovki","Mastef Chef",new ArrayList<>()));
       chefs.add(new Chef(1L,"Gordon","Ramsey","Mastef Chef",new ArrayList<>()));
       chefs.add(new Chef(2L,"Massimo","Bottura","Mastef Chef",new ArrayList<>()));
       chefs.add(new Chef(3L,"Joel","Robuchon","Mastef Chef",new ArrayList<>()));
       chefs.add(new Chef(4L,"Heston","Blumenthal","Mastef Chef",new ArrayList<>()));

       dishes.add(new Dish("d1","Muska","Macedonia",30));
       dishes.add(new Dish("d2","Pizza","Italy",30));
       dishes.add(new Dish("d3","Spagetti","Italy",20));
       dishes.add(new Dish("d4","Gyro","Greece",30));
       dishes.add(new Dish("d5","Ajvar","Macedonia",60));


   }
}
