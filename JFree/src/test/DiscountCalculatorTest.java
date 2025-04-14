package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    @Test
    public void testIsTheSpecialWeekWhenFalse() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 22);  // March 22, 2025
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator testObject = new DiscountCalculator(week);
        // Act

        // Assert
        assertFalse(testObject.isTheSpecialWeek());
    }
    @Test
    public void testIsTheSpecialWeekWhenTrue() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 23);  // ( JUNE, 23 is a date in week 26 )
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator testObject = new DiscountCalculator(week);
        // Act

        // Assert
        assertTrue(testObject.isTheSpecialWeek());
    }
    @Test(expected = Exception.class)
    public void testIsTheSpecialWeekWhenNullWeek() throws Exception {
        // Arrange
        Week week = new Week(null);
        DiscountCalculator testObject = new DiscountCalculator(week);
        // Act
        testObject.isTheSpecialWeek();
    }

    @Test
    public void testgetDiscountPercentageWhenEvenWeek() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.APRIL, 12);  // ( April, 12 is a date in an even week )
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator testObject = new DiscountCalculator(week);
        // Act
        int resultDiscountPercentage = testObject.getDiscountPercentage();

        // Assert
        assertEquals(7, resultDiscountPercentage);
    }

    @Test
    public void testgetDiscountPercentageWhenOddWeek() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.APRIL, 19);  // ( April, 19 is a date in an odd week )
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator testObject = new DiscountCalculator(week);
        // Act
        int resultDiscountPercentage = testObject.getDiscountPercentage();

        // Assert
        assertEquals(5, resultDiscountPercentage);
    }

    //Mesh aaref ezay bas ahe mawgoda law eerefna
    @Test
    public void testgetDiscountPercentageWhenOutOfBounds() throws Exception {}


    @Test(expected = Exception.class)
    public void testgetDiscountPercentageWhenNull() throws Exception {
        // Arrange
        Week week = new Week(null);
        DiscountCalculator testObject = new DiscountCalculator(week);

        // Act
        int resultDiscountPercentage = testObject.getDiscountPercentage();

        //Assert
        assertEquals(-1, resultDiscountPercentage);//Not sure?
    }

    // Test missing cases ( JUNE, 23 is a date in week 26 )

}
