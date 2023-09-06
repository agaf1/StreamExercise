package pl.aga.exercise1;

import org.junit.jupiter.api.Test;
import pl.aga.exercise1.Dish;
import pl.aga.exercise1.StreamOperations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public void should_be_ok() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());

        for(int [] i : pairs){
            System.out.println(Arrays.toString(i));
            }

    }


}