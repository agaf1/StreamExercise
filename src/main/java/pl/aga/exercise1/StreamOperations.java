package pl.aga.exercise1;

import pl.aga.exercise1.Dish;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamOperations {

    public List<String> getThreeHighCaloricDishNames(List<Dish> menu) {

        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(d -> d.getName())
                        .limit(3)
                        .collect(Collectors.toList());

        return threeHighCaloricDishNames;
    }

    public List<Dish> getVegetarianMenu(List<Dish> menu) {
        List<Dish> vegetarianMenu =
                menu.stream()
                        .filter(d -> d.isVegetarian())
                        .collect(Collectors.toList());
        return vegetarianMenu;
    }

    public int getSumOfCaloriesFromAllMenu(List<Dish> menu) {
        int calories =
                menu.stream()
                        .mapToInt(Dish::getCalories)
                        .sum();
        return calories;
    }

    public Optional<Dish> getMostCalorieDish(List<Dish> menu) {
        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(Collectors.maxBy(comparingInt(Dish::getCalories)));

        return mostCalorieDish;
    }

    public Map<Dish.Type, List<Dish>> getGroupingDishByType(List<Dish> menu) {
        Map<Dish.Type, List<Dish>> menuByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType));

        return menuByType;
    }

    public Map<Dish.CaloricLevel, List<Dish>> getDishesGroupingByCaloricLevel(List<Dish> menu) {
        Map<Dish.CaloricLevel, List<Dish>> menuByCaloricLevel =
                menu.stream()
                        .collect(groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        }));
        return menuByCaloricLevel;
    }

    public Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> getDishesGroupingByTypeAndCaloricLevel(List<Dish> menu) {
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> menuByTypeAndCaloricLevel =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                            else return Dish.CaloricLevel.FAT;
                                        }
                                )));
        return menuByTypeAndCaloricLevel;
    }

    public Map<Dish.Type, Long> getQuantityOfDishesEveryType(List<Dish> menu) {
        Map<Dish.Type, Long> countedDishes =
                menu.stream()
                        .collect(groupingBy(Dish::getType, counting()));
        return countedDishes;
    }

    public Map<Dish.Type, Optional<Dish>> getMostCaloricDishFromEveryType(List<Dish> menu) {
        Map<Dish.Type, Optional<Dish>> mostCaloricDishes =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));
        return mostCaloricDishes;
    }

    public Map<Boolean, List<Dish>> groupingMenuByVegetarianDishes(List<Dish> menu) {
        Map<Boolean, List<Dish>> vegetarianMenu =
                menu.stream()
                        .collect(partitioningBy(Dish::isVegetarian));
        return vegetarianMenu;
    }

    public Map<Boolean,Map<Dish.Type,List<Dish>>> groupingMenuByVegetarianAndTypeDishes(List<Dish> menu){
        Map<Boolean,Map<Dish.Type,List<Dish>>> vegetarianMenuByType =
                menu.stream()
                        .collect(partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType)));
        return vegetarianMenuByType;
    }
}
