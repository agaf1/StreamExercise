package pl.aga.exercise1;

import org.junit.jupiter.api.Test;
import pl.aga.exercise1.Dish;
import pl.aga.exercise1.StreamOperations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summarizingInt;
import static org.assertj.core.api.Assertions.assertThat;

class StreamOperationsTest {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawn", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    StreamOperations streamOperations = new StreamOperations();

    @Test
    public void should_get_list_of_three_high_caloric_dish_names() {
        List<String> result = streamOperations.getThreeHighCaloricDishNames(menu);

        assertThat(result).containsExactlyElementsOf(List.of("pork", "beef", "chicken"));
    }

    @Test
    public void should_get_vegetarian_menu() {
        List<Dish> result = streamOperations.getVegetarianMenu(menu);

        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void should_get_sum_of_calories_from_all_menu() {
        int result = streamOperations.getSumOfCaloriesFromAllMenu(menu);

        assertThat(result).isEqualTo(4200);
    }

    @Test
    public void should_get_most_calorie_dish_from_all_menu() {
        Optional<Dish> result = streamOperations.getMostCalorieDish(menu);

        assertThat(result.get()).isEqualTo(new Dish("pork", false, 800, Dish.Type.MEAT));
        assertThat(result.get().getCalories()).isEqualTo(800);

        IntSummaryStatistics menuStatistic = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistic);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu);
    }

    @Test
    public void should_get_grouping_menu_by_type_of_dish() {
        Map<Dish.Type, List<Dish>> result = streamOperations.getGroupingDishByType(menu);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(Dish.Type.MEAT))
                .isEqualTo(List.of
                               (new Dish("pork", false, 800, Dish.Type.MEAT),
                                new Dish("beef", false, 700, Dish.Type.MEAT),
                                new Dish("chicken", false, 400, Dish.Type.MEAT)));
    }

    @Test
    public void should_get_menu_grouping_by_caloricLevel_of_dish(){
        Map<Dish.CaloricLevel, List<Dish>> result = streamOperations.getDishesGroupingByCaloricLevel(menu);

        assertThat(result.get(Dish.CaloricLevel.DIET).size()).isEqualTo(4);
    }

    @Test
    public void should_counting_Dishes_for_every_type(){
        Map<Dish.Type, Long> result = streamOperations.getQuantityOfDishesEveryType(menu);

        assertThat(result.get(Dish.Type.MEAT)).isEqualTo(3);
        assertThat(result.get(Dish.Type.OTHER)).isEqualTo(4);
        assertThat(result.get(Dish.Type.FISH)).isEqualTo(2);
    }

    @Test
    public void should_find_most_caloric_dish_for_every_type(){
        Map<Dish.Type,Optional<Dish>> result = streamOperations.getMostCaloricDishFromEveryType(menu);

        assertThat(result.get(Dish.Type.MEAT).get()).isEqualTo(new Dish("pork", false, 800, Dish.Type.MEAT));
        assertThat(result.get(Dish.Type.OTHER).get()).isEqualTo(new Dish("pizza", true, 550, Dish.Type.OTHER));
        assertThat(result.get(Dish.Type.FISH).get()).isEqualTo(new Dish("salmon", false, 450, Dish.Type.FISH));
    }



}