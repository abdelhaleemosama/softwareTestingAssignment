package test;

import org.jfree.data.time.Year;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class YearTest {
    Year year;

    private void arrange() {
        year = new Year();
    }


    @Test
    public void testYearDefaultCtor() {
        arrange();
        assertEquals(2025, year.getYear());
    }


    @Test
    public void testYearConstructorInstantTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 12);  // March 22, 2025
        Date testDate = calendar.getTime();
        year = new Year(testDate);

        assertEquals(2025, year.getYear());
    }
    @Test(expected = NullPointerException.class)
    public void testYearConstructorInstantTimeWithNull() {
        year = new Year(null);

        assertEquals(2025, year.getYear());
    }


    @Test
    public void testYearConstructorTimeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 12);  // March 22, 2025
        Date testDate = calendar.getTime();
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        year = new Year(testDate, timeZone);

        assertEquals(2025, year.getYear());
    }
    @Test(expected = NullPointerException.class)
    public void testYearConstructorTimeZoneWithNull() {
        year = new Year(null, null);

        assertEquals(2025, year.getYear());
    }


    @Test
    public void testYearConstructorYear() {
        year = new Year(2025);

        assertEquals(2025, year.getYear());
    }
    @Test(expected = NullPointerException.class)
    public void testYearConstructorYearNullYear() {
        year = new Year(null);

        assertEquals(2025, year.getYear());
    }

    @Test
    public void testCompareTo(){
        Year earlierYear = new Year(2022);
        Year laterYear = new Year(2025);

        assertTrue(earlierYear.compareTo(laterYear) < 0);
        assertTrue(laterYear.compareTo(earlierYear) > 0);
        assertEquals(earlierYear.compareTo(new Year(2022)), 0);
    }

    @Test
    public void testEquals(){
        Year testYear1 = new Year(2022);
        Year testYear2 = new Year(2025);

        assertFalse(testYear1.equals(testYear2));
        assertTrue(testYear1.equals(new Year(2022)));
        assertFalse(testYear1.equals(null));
    }

    @Test
    public void testGetFirstMilliSecond(){
        Calendar calendar = Calendar.getInstance();
        Year testYear = new Year(2025);
        long actualOutput = testYear.getFirstMillisecond(calendar);

        calendar.set(2025, Calendar.JANUARY, 1,0,0,0);
        long expectedOutput = calendar.getTimeInMillis();

        assertEquals(expectedOutput, actualOutput);

        calendar.set(2024, Calendar.JANUARY, 1,0,0,0);
        expectedOutput = calendar.getTimeInMillis();

        assertNotEquals(expectedOutput, actualOutput);
    }


    @Test
    public void testGetLastMilliSecond(){
        Calendar calendar = Calendar.getInstance();
        Year testYear = new Year(2025);
        long actualOutput = testYear.getLastMillisecond(calendar);

        calendar.set(2026, Calendar.JANUARY, 1,0,0,0);
        long expectedOutput = calendar.getTimeInMillis();// Last milliSecond of the previous second

        assertNotEquals(expectedOutput, actualOutput);

        expectedOutput -= 1000;
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void testGetSerialIndex(){
        Year testYear = new Year(2025);

        assertEquals(2025, testYear.getSerialIndex());
        assertNotEquals(2026, testYear.getSerialIndex());
    }


    @Test
    public void testGetYear(){
        Year testYear = new Year(2025);

        assertEquals(2025, testYear.getYear());
    }


    @Test
    public void testHashCode(){
        Year testYear1 = new Year(2025);
        Year testYear2 = new Year(2026);

        assertEquals(testYear1.hashCode() , (37 * 17 + testYear1.getYear()));
        assertNotEquals(testYear1.hashCode(), (testYear2.hashCode()));
        assertNotEquals(testYear1.hashCode(), (37 * 17 + testYear2.getYear()));
    }


    @Test
    public void testNext(){
        Year testYear = new Year(2025);
        Year testYear2 = new Year(2026);

        assertEquals(testYear.next(), testYear2);
        assertNotEquals(testYear2.next(), testYear);
    }

    @Test
    public void testNextNullException(){
        Year testYear = new Year(9999);

        assertNull(testYear.next());
    }


    @Test
    public void testParseYear(){
        Year testYear = Year.parseYear("2025");

        assertEquals(2025, testYear.getYear());
        assertEquals(0,  Year.parseYear("0").getYear());
        assertEquals(-1,  Year.parseYear("-1").getYear());
        assertEquals(2023,  Year.parseYear(" 2023 ").getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseYearAlphabetInput(){
        Year testYear = Year.parseYear("abcd");
    }

    @Test(expected = NullPointerException.class)
    public void testParseYearNullInput(){
        Year testYear = Year.parseYear(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseYearInvalidInput(){
        Year testYear = Year.parseYear("2@25");
    }


    @Test
    public void testPrevious(){
        Year testYear = new Year(2025);
        Year testYear2 = new Year(2026);

        assertEquals(testYear2.previous(), testYear);
        assertNotEquals(testYear.previous(), testYear2);
    }
    @Test
    public void testPreviousNullOutput(){
        Year testYear = new Year(1900);
        Year testYear2 = new Year(-9999);

        assertNull(testYear.previous());
        assertNull(testYear2.previous());
    }

    @Test
    public void testToString(){
        Year testYear = new Year(2025);

        assertEquals("2025", testYear.toString());
        assertNotEquals(2025, testYear.toString());
        assertEquals("2025", new Year().toString());
    }
}
