package test;
import JFree.DiscountManager;
import JFree.IDiscountCalculator;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscountManagerTest {

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsFalse() throws Exception {
        // Arrange
        boolean isDiscountsSeason = false;
        double originalPrice = 100.0;
        double expectedPrice = 100.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){
            {
                // make sure that none of the functions are called
                never(mockedDependency).getDiscountPercentage();

                never(mockedDependency).isTheSpecialWeek();
            }
        });
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        // Act
        double resultPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        // make sure that mocking Expectations Is Satisfied
        mockingContext.assertIsSatisfied();
        // make sure that the actual value exactly equals the expected value
        assertEquals(expectedPrice, resultPrice, 0.000);
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsTrue() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 100.0 * 0.8;
        boolean isSpecialWeek = true;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){
            {
                allowing(mockedDependency).isTheSpecialWeek();
                will(returnValue(isSpecialWeek));
                // make sure that none of the other functions are called
                never(mockedDependency).getDiscountPercentage();
            }
        });
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        // Act
        double resultPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        // make sure that mocking Expectations Is Satisfied
        mockingContext.assertIsSatisfied();
        // make sure that the actual value exactly equals the expected value
        assertEquals(expectedPrice, resultPrice, 0.000);
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsFalse() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        int testDiscountPercentage = 7;
        double expectedPrice = 100.0 * ((double)(100 - testDiscountPercentage)/100);
        boolean isSpecialWeek = false;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){
            {
                // make sure that none of the functions are called
                allowing(mockedDependency).getDiscountPercentage();
                will(returnValue(testDiscountPercentage));

                allowing(mockedDependency).isTheSpecialWeek();
                will(returnValue(isSpecialWeek));
            }
        });
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        // Act
        double resultPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        // make sure that mocking Expectations Is Satisfied
        mockingContext.assertIsSatisfied();
        // make sure that the actual value exactly equals the expected value
        assertEquals(expectedPrice, resultPrice, 0.000);
    }

    //Works even though it should not (because it is zero)
    //Fault execution, error with no failure
    @Test
    public void testCalculatePriceWhenPriceIsZero() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 0;
        int testDiscountPercentage = 7;
        double expectedPrice = 0 * ((double)(100 - testDiscountPercentage)/100);
        boolean isSpecialWeek = false;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){
            {
                // make sure that none of the functions are called
                allowing(mockedDependency).getDiscountPercentage();
                will(returnValue(testDiscountPercentage));

                allowing(mockedDependency).isTheSpecialWeek();
                will(returnValue(isSpecialWeek));
            }
        });
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        // Act
        double resultPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        // make sure that mocking Expectations Is Satisfied
        mockingContext.assertIsSatisfied();
        // make sure that the actual value exactly equals the expected value
        assertEquals(expectedPrice, resultPrice, 0.000);
    }
    // test missing cases
}
