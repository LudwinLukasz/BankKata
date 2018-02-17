package account;

import org.junit.Test;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
/**
 * Created by arabk on 17.02.2018.
 */
public class AccountTest {

    @Test
    public void shouldPrintEmptyStatement() {
        Account account = new Account();
        PrintStream printStream = mock(PrintStream.class);
        account.getStatement(printStream);
        verify(printStream, times(1)).print("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void shouldPrintStatementAfterOneDeposit() {
        Account account = new Account();
        PrintStream printStream = mock(PrintStream.class);
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2018, Calendar.FEBRUARY, 17);
        account.deposit(calendar.getTime(), 100);
        account.getStatement(printStream);
        verify(printStream, times(1)).print("DATE | AMOUNT | BALANCE\n17/02/2018 | 100.0 | 100.0");
    }

    @Test
    public void shouldPrintStatementAfterOneDepositAndOneWithdraw() {
        Account account = new Account();
        PrintStream printStream = mock(PrintStream.class);
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2018, Calendar.FEBRUARY, 17);
        account.deposit(calendar.getTime(), 100);
        calendar.set(2018, Calendar.FEBRUARY, 18);
        account.withdraw(calendar.getTime(),50);
        account.getStatement(printStream);
        verify(printStream, times(1)).print("DATE | AMOUNT | BALANCE\n" +
                        "17/02/2018 | 100.0 | 100.0\n" +
                        "18/02/2018 | -50.0 | 50.0"
        );
    }
}
