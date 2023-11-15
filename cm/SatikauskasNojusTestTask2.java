package cm;


import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SatikauskasNojusTestTask2 {

    @Test
    public void testConstructorValidInputs() {
        // Arrange
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = BigDecimal.valueOf(10);
        BigDecimal reducedRate = BigDecimal.valueOf(5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        // Act & Assert
        assertDoesNotThrow(() -> new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods));
    }

    // Additional test cases for the constructor to cover branches

    @Test
    public void testConstructorNullPeriods() {
        // Arrange
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.valueOf(8);
        BigDecimal reducedRate = BigDecimal.valueOf(4);
        ArrayList<Period> normalPeriods = null;
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        // Act
        new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
    }

    // Additional test cases for the constructor to cover branches

    @Test
    public void testIsValidPeriodsTrue() {
        // Arrange
        ArrayList<Period> periods1 = new ArrayList<>(Arrays.asList(new Period(8, 12), new Period(14, 18)));
        ArrayList<Period> periods2 = new ArrayList<>(Arrays.asList(new Period(1, 5), new Period(20, 22)));

        // Act
        boolean result = new Rate(CarParkKind.STAFF, BigDecimal.ONE, BigDecimal.ZERO, periods1, periods2).isValidPeriods(periods1, periods2);

        // Assert
        assertTrue(result);
    }

    // Additional test cases for isValidPeriods to cover branches

    @Test
    public void testCalculateVisitorCarParkKind() {
        // Arrange
        Rate rate = new Rate(CarParkKind.VISITOR, BigDecimal.TEN, BigDecimal.ZERO, new ArrayList<>(), new ArrayList<>());
        Period period = new Period(9, 15);

        // Act
        BigDecimal result = rate.calculate(period);

        // Assert
        assertEquals(BigDecimal.ZERO, result);
    }

    // Additional test cases for calculate to cover branches

    @Test
    public void testCalculateNonVisitorCarParkKind() {
        // Arrange
        Rate rate = new Rate(CarParkKind.STAFF, BigDecimal.TEN, BigDecimal.ONE, new ArrayList<>(), new ArrayList<>());
        Period period = new Period(8, 12);

        // Act
        BigDecimal result = rate.calculate(period);

        // Assert
        assertEquals(BigDecimal.valueOf(40), result);
    }

    // Additional test cases for calculate to cover branches
}
