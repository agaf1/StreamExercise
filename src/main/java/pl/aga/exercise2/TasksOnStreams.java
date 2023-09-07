package pl.aga.exercise2;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TasksOnStreams {

    public List<Transaction> getSortedTransactionFrom2011(List<Transaction> transactions) {
        List<Transaction> transactionsFrom2011 =
                transactions.stream()
                        .filter(t -> t.getYear() == 2011)
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .collect(Collectors.toList());

        return transactionsFrom2011;
    }

    public Set<String> getAllCity(List<Transaction> transactions) {
        Set<String> cities =
                transactions.stream()
                        .map(t -> t.getTrader().getCity())
                        .collect(Collectors.toSet());

        return cities;
    }

    public List<Trader> getSortedTradersFromCambridge(List<Transaction> transactions) {
        List<Trader> traders =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(d -> d.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(Comparator.comparing(Trader::getName))
                        .collect(Collectors.toList());

        return traders;
    }

    public List<String> getSortedNamesOfTraders(List<Transaction> transactions) {
        List<String> namesOfTraders =
                transactions.stream()
                        .map(t -> t.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());

        return namesOfTraders;
    }

    public boolean findTraderFromMilan(List<Transaction> transactions) {
        boolean isTraderFromMilan =
//                transactions.stream()
//                        .map(t -> t.getTrader())
//                        .filter(t -> t.getCity() == "Milan")
//                        .findAny()
//                        .isPresent();
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        return isTraderFromMilan;
    }

    public List<Integer> getAllValueOfTransactionOfTradersFromCambridge(List<Transaction> transactions) {
        List<Integer> valueOfTransaction =
                transactions.stream()
                        .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                        .map(Transaction::getValue)
                        .collect(Collectors.toList());

        return valueOfTransaction;
    }

    public int getMaxValueOfTransaction(List<Transaction> transactions) {
        int maxValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max)
                        .orElse(0);
        return maxValue;
    }

    public Transaction getTransactionWithMinValue(List<Transaction> transactions) {
        Transaction minTransaction =
                transactions.stream()
//                        .sorted(Comparator.comparing(Transaction::getValue))
//                        .findFirst()
//                        .get();

//                        .reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
//                        .get();

                        .min(Comparator.comparing(Transaction::getValue))
                        .get();

        return minTransaction;
    }


}
