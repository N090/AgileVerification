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



    @Test
    public void testConstructor1() {
        // Test case 1
        int start1=0;
        int end1=24;
        Period period1 = new Period(start1, end1);
    }

    @Test
    public void testConstructor2() {
        // Test case 2
        int start2 = 1;
        int end2 = 23;
        Period period2 = new Period(start2, end2);
    }

    @Test
    public void testConstructor3() {
        // Test case 3
        int start3 = -1;
        int end3 = 1;
        Period period3 = new Period(start3, end3);
    }

    @Test
    public void testConstructor4() {
        // Test case 4
        int start4 = 24;
        int end4 = 0;
        Period period4 = new Period(start4, end4);
    }

    @Test
    public void testConstructor5() {
        // Test case 5
        int start5 = 25;
        int end5 = 26;
        Period period5 = new Period(start5, end5);
    }

    @Test
    public void testConstructor6() {
        // Test case 6
        int start6 = 0;
        int end6 = 0;
        Period period6 = new Period(start6, end6);
    }

    @Test
    public void testConstructor7() {
        // Test case 7
        int start7 = 23;
        int end7 = 24;
        Period period7 = new Period(start7, end7);
    }

    @Test
    public void testConstructor8() {
        // Test case 8
        int start8 = -1;
        int end8 = -1;
        Period period2 = new Period(start8, end8);
    }

    @Test
    public void testConstructor9() {
        // Test case 9
        int start9 = 5;
        int end9 = -1;
        Period period9 = new Period(start9, end9);
    }



    @Test
    void testOverlaps1() {
        // Test case 10
        Period period10 = new Period(12, 14);
        Period period11 = new Period(11, 15);
        assertTrue(period10.overlaps(period11));
    }

    @Test
    void testOverlaps2() {
        // Test case 11
        Period period12 = new Period(21, 23);
        Period period13 = new Period(22, 24);
        assertTrue(period12.overlaps(period13));
    }

    @Test
    void testOverlaps3() {
        // Test case 12
        Period period14 = new Period(22, 23);
        Period period15 = new Period(22, 24);
        assertTrue(period14.overlaps(period15));
    }

    @Test
    void testOverlaps4() {
        // Test case 13
        Period period16 = new Period(10, 12);
        Period period17 = new Period(10, 12);
        assertTrue(period16.overlaps(period17));
    }

    @Test
    void testOverlaps5() {
        // Test case 14
        Period period18 = new Period(1, 4);
        Period period19 = new Period(4, 6);
        assertFalse(period18.overlaps(period19));
    }

    @Test
    void testOverlaps6() {
        // Test case 15
        Period period20 = new Period(4, 6);
        Period period21 = new Period(1, 4);
        assertFalse(period20.overlaps(period21));
    }

    @Test
    void testOverlaps7() {
        // Test case 16
        Period period22 = new Period(6, 8);
        Period period23 = new Period(13, 15);
        assertFalse(period22.overlaps(period23));
    }

    @Test
    void testOverlaps8() {
        // Test case 17
        Period period24 = new Period(25, 27);
        Period period25 = new Period(23, 25);
        assertFalse(period24.overlaps(period25));
    }

    @Test
    void testOverlaps9() {
        // Test case 18
        Period period26 = new Period(-5, 0);
        Period period27 = new Period(0, 5);
        assertFalse(period26.overlaps(period27));
    }

    @Test
    void testOverlaps10() {
        // Test case 19
        Period period28 = new Period(0, 5);
        Period period29 = new Period(-4, -1);
        assertFalse(period28.overlaps(period29));
    }

    @Test
    void testOverlaps11() {
        // Test case 20
        Period period30 = new Period(-5, 10);
        Period period31 = new Period(-5, 0);
        assertTrue(period30.overlaps(period31));
    }

    @Test
    void testOverlaps12() {
        // Test case 21
        Period period32 = new Period(-5, -1);
        Period period33 = new Period(-3, 1);
        assertTrue(period32.overlaps(period33));
    }

    @Test
    void testOverlaps13() {
        // Test case 22
        Period period34 = new Period(23, 25);
        Period period35 = new Period(24, 26);
        assertTrue(period34.overlaps(period35));
    }

    @Test
    void testOverlaps14() {
        // Test case 23
        Period period36 = new Period(26, 28);
        Period period37 = new Period(25, 27);
        assertTrue(period36.overlaps(period37));
    }

    @Test
    void testDuration1() {
        // Test case 24
        Period period38 = new Period(0, 24);
        assertEquals(24, period38.duration());
    }

    @Test
    void testDuration2() {
        // Test case 25
        Period period39 = new Period(1, 23);
        assertEquals(22, period39.duration());
    }

    @Test
    void testDuration3() {
        // Test case 26
        Period period40 = new Period(24, 24);
        assertEquals(0, period40.duration());
    }

    @Test
    void testDuration4() {
        // Test case 27
        Period period41 = new Period(24, 25);
        assertEquals(0, period41.duration());
    }

    @Test
    void testDuration5() {
        // Test case 28
        Period period42 = new Period(23, 24);
        assertEquals(1, period42.duration());
    }

    @Test
    void testDuration6() {
        // Test case 29
        Period period43 = new Period(24, 0);
        assertEquals(0, period43.duration());
    }

    @Test
    void testDuration7() {
        // Test case 30
        Period period44 = new Period(-1, 1);
        assertEquals(0, period44.duration());
    }

    @Test
    void testDuration8() {
        // Test case 31
        Period period45 = new Period(25, 26);
        assertEquals(0, period45.duration());
    }

    @Test
    void testDuration9() {
        // Test case 32
        Period period46 = new Period(0, 0);
        assertEquals(0, period46.duration());
    }


}
