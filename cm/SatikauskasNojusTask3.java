package cm;


import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SatikauskasNojusTask3 {

    @Test
    void visitorPays(){
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = new BigDecimal(5);
        BigDecimal reducedRate = new BigDecimal(3);
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        //Arrange
        normalPeriods.add(new Period(10, 12));
        normalPeriods.add(new Period(7, 10));
        reducedPeriods.add(new Period(15, 17));
        reducedPeriods.add(new Period(13, 15));
        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(9, 13);
        //Assert
        assertEquals(2.50, rate.calculate(periodStay).doubleValue());
    }

    @Test
    void managerPaysMinimum() {
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = new BigDecimal(2);
        BigDecimal reducedRate = new BigDecimal(1);
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        //Arrange
        normalPeriods.add(new Period(10, 12));
        reducedPeriods.add(new Period(15, 17));
        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(9, 11);
        //Assert
        assertEquals(5.0, rate.calculate(periodStay).doubleValue());
    }

    @Test
    void studentDiscount() {
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = new BigDecimal(6);
        BigDecimal reducedRate = new BigDecimal(1);
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        //Arrange
        normalPeriods.add(new Period(9, 12));
        normalPeriods.add(new Period(16, 18));
        reducedPeriods.add(new Period(12, 16));
        Rate r = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(11, 13);
        //Assert
        assertEquals(4.69, r.calculate(periodStay).doubleValue());
    }

    @Test
    void staffPaysMaximum() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = new BigDecimal(5);
        BigDecimal reducedRate = new BigDecimal(3);
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        //Arrange
        normalPeriods.add(new Period(10, 14));
        reducedPeriods.add(new Period(14, 18));
        Rate r = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        Period periodStay = new Period(12, 17);
        //Assert
        assertEquals(10, r.calculate(periodStay).doubleValue());
    }



    // Task 1 & 2 code
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

        @Test
        public void testRateConstructorReducedRateNull() {
            try {
                assertThrows(IllegalArgumentException.class, () -> {
                    Rate rate = new Rate(
                            CarParkKind.STAFF, BigDecimal.valueOf(5.0), null,
                            new ArrayList<>(),
                            new ArrayList<>() {{
                                add(new Period(10, 14));
                            }}
                    );
                });
            } catch (Exception e) {
                fail("Exception " + e);
            }
        }


        // Additional test cases for calculate to cover branches

        @Test
        void testIsIn() {
            Period period = new Period(8, 12);

            // Test within the period
            assertTrue(period.isIn(9));

            // Test at the start of the period
            assertTrue(period.isIn(8));

            // Test at the end of the period
            assertFalse(period.isIn(12));

            // Test outside the period
            assertFalse(period.isIn(6));
            assertFalse(period.isIn(13));
        }

        @Test
        void testDuration() {
            Period period = new Period(8, 12);

            // Duration of the period should be 4 hours
            assertEquals(4, period.duration());
        }

        @Test
        void testOccurences() {
            Period period = new Period(8, 12);

            // Create a list of periods
            List<Period> periodList = new ArrayList<>();
            periodList.add(new Period(9, 11));
            periodList.add(new Period(14, 16));

            // The period overlaps with the first period in the list, so occurrences should be 2
            assertEquals(2, period.occurences(periodList));

            // Test with an empty list, occurrences should be 0
            assertEquals(0, period.occurences(new ArrayList<>()));
        }

        @Test
        void testValidRateCreation() {
            // Test valid rate creation
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();
            normalPeriods.add(new Period(8, 12));
            reducedPeriods.add(new Period(18, 22));

            Rate rate = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(10), BigDecimal.valueOf(5), normalPeriods, reducedPeriods);

            assertEquals(CarParkKind.STUDENT, rate.kind);
            assertEquals(BigDecimal.valueOf(10), rate.hourlyNormalRate);
            assertEquals(BigDecimal.valueOf(5), rate.hourlyReducedRate);
            assertEquals(normalPeriods, rate.normal);
            assertEquals(reducedPeriods, rate.reduced);
        }

        @Test
        void testInvalidRateCreation() {
            // Test invalid rate creation

            // Null periods
            assertThrows(IllegalArgumentException.class, () -> {
                new Rate(CarParkKind.STAFF, BigDecimal.valueOf(15), BigDecimal.valueOf(7), null, null);
            });

            // Null rates
            assertThrows(IllegalArgumentException.class, () -> {
                new Rate(CarParkKind.MANAGEMENT, null, BigDecimal.valueOf(8), new ArrayList<>(), new ArrayList<>());
            });

            // Negative rates
            assertThrows(IllegalArgumentException.class, () -> {
                new Rate(CarParkKind.STAFF, BigDecimal.valueOf(-5), BigDecimal.valueOf(3), new ArrayList<>(), new ArrayList<>());
            });

            // Normal rate less than or equal to reduced rate
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();
            normalPeriods.add(new Period(8, 12));
            reducedPeriods.add(new Period(18, 22));

            assertThrows(IllegalArgumentException.class, () -> {
                new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(8), BigDecimal.valueOf(8), normalPeriods, reducedPeriods);
            });

            // Overlapping periods
            normalPeriods.add(new Period(10, 14));

            assertThrows(IllegalArgumentException.class, () -> {
                new Rate(CarParkKind.MANAGEMENT, BigDecimal.valueOf(12), BigDecimal.valueOf(6), normalPeriods, reducedPeriods);
            });
        }

        @Test
        void testCalculate() {
            // Test calculate method

            Rate rate = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(10), BigDecimal.valueOf(5),
                    createPeriodList(8, 12), createPeriodList(18, 22));

            // Test with a period that falls entirely within normal hours
            Period periodWithinNormal = new Period(9, 11);
            assertEquals(BigDecimal.valueOf(20), rate.calculate(periodWithinNormal));

            // Test with a period that falls entirely within reduced hours
            Period periodWithinReduced = new Period(19, 21);
            assertEquals(BigDecimal.valueOf(15), rate.calculate(periodWithinReduced));

            // Test with a period that spans both normal and reduced hours
            Period periodSpanningBoth = new Period(10, 20);
            assertEquals(BigDecimal.valueOf(120), rate.calculate(periodSpanningBoth));

            // Test with a visitor rate (should always return 0)
            Rate visitorRate = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(8), BigDecimal.valueOf(4),
                    createPeriodList(8, 18), createPeriodList(18, 22));

            assertEquals(BigDecimal.valueOf(0), visitorRate.calculate(periodSpanningBoth));
        }

        // Helper method to create a list of periods
        private ArrayList<Period> createPeriodList(int start, int end) {
            ArrayList<Period> periods = new ArrayList<>();
            periods.add(new Period(start, end));
            return periods;
        }


        @Test
        void testOverlaps() {
            Period period1 = new Period(8, 12);
            Period period2 = new Period(10, 14);
            Period period3 = new Period(14, 18);

            // Test overlapping periods
            assertTrue(period1.overlaps(period2));

            // Test non-overlapping periods
            assertFalse(period1.overlaps(period3));
        }


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



