package pl.aga.exercise2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TasksOnStreamsTest {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    TasksOnStreams tasksOnStreams = new TasksOnStreams();

    @Test
    public void should_get_sorted_transactions_from_2011() {
        List<Transaction> result = tasksOnStreams.getSortedTransactionFrom2011(transactions);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).
                containsExactlyElementsOf(
                        List.of(new Transaction(brian, 2011, 300),
                                new Transaction(raoul, 2011, 400)));
    }

    @Test
    public void should_get_All_Cities(){
        Set<String> result = tasksOnStreams.getAllCity(transactions);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains("Cambridge","Milan");
    }

    @Test
    public void should_get_sorted_Traders_from_Cambridge(){
        List<Trader> result = tasksOnStreams.getSortedTradersFromCambridge(transactions);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("Alan");
    }

    @Test
    public void should_get_sorted_names_of_traders(){
        List<String> result = tasksOnStreams.getSortedNamesOfTraders(transactions);

        assertThat(result.size()).isEqualTo(4);
        assertThat(result).containsExactlyElementsOf(List.of("Alan","Brian","Mario","Raoul"));
    }

    @Test
    public void should_get_True_when_is_Trader_from_Milan(){
        boolean result = tasksOnStreams.findTraderFromMilan(transactions);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void should_get_Values_Of_Transaction_From_Cambridge(){
        List<Integer> result = tasksOnStreams.getAllValueOfTransactionOfTradersFromCambridge(transactions);

        System.out.println(result);

        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void should_get_max_value_from_all_transactions(){
        int result = tasksOnStreams.getMaxValueOfTransaction(transactions);

        assertThat(result).isEqualTo(1000);
    }

    @Test
    public void should_get_min_transaction(){
        Transaction result = tasksOnStreams.getTransactionWithMinValue(transactions);

        assertThat(result.getValue()).isEqualTo(300);
    }

}