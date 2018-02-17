package account;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by arabk on 17.02.2018.
 */
public class Account {

    private Queue<TransactionData>transactions = new LinkedList<TransactionData>();
    private float currentBalance = 0;

    private static final String reportHeader = "DATE | AMOUNT | BALANCE";

    public void getStatement(PrintStream printStream) {
        printStream.print(createReport());
    }

    public void deposit(Date transactionTime, float amount) {
        this.currentBalance += amount;
        addTransactionData(transactionTime, amount);
    }

    public void withdraw(Date transactionTime, float amount) {
        this.currentBalance -= amount;
        addTransactionData(transactionTime, -amount);
    }

    private String createReport() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(reportHeader);

        Iterator<TransactionData> iterator = transactions.iterator();
        while(iterator.hasNext()) {
            stringBuilder.append("\n");
            stringBuilder.append(iterator.next());
        }

        return stringBuilder.toString();
    }

    private void addTransactionData(Date transactionTime, float amount) {
        transactions.add(new TransactionData(transactionTime, amount, this.currentBalance));
    }

    private class TransactionData {

        private Date date;
        private float amount;
        private float balance;
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        private TransactionData(Date date, float amount, float balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(simpleDateFormat.format(date));
            stringBuilder.append(" | ");
            stringBuilder.append(amount);
            stringBuilder.append(" | ");
            stringBuilder.append(balance);

            return stringBuilder.toString();
        }
    }

}
