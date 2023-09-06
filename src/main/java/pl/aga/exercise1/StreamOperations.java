package pl.aga.exercise1;

import pl.aga.exercise1.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class StreamOperations {

    public List<String> getThreeHighCaloricDishNames(List<Dish> menu){

        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(d -> d.getName())
                        .limit(3)
                        .collect(Collectors.toList());

        return threeHighCaloricDishNames;
    }

    public List<Dish> getVegetarianMenu(List<Dish> menu){
        List<Dish> vegetarianMenu =
                menu.stream()
                        .filter(d-> d.isVegetarian())
                        .collect(Collectors.toList());
        return vegetarianMenu;
    }
}
