package com.letmefly;

import com.letmefly.databases.entities.AirportEntity;
import com.letmefly.logic.ResultGenerator;
import com.letmefly.viewmodels.PassengerViewModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ResultGeneratorUnitTests {

    private AirportEntity testAirport = new AirportEntity("TST","USA","TestCity",false,"Poland,China,England", false, "Test Transit Info");
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void ShouldAcceptNationalityFromWhiteList(){
        ResultGenerator test = new ResultGenerator(testAirport,"England");

        test.generateVisitWhitelist();

        boolean expected = true;
        boolean actual = test.getCanVisit();

        assertEquals(expected,actual);

    }

    @Test
    public void ShouldNOTAcceptNationality(){
        ResultGenerator test = new ResultGenerator(testAirport,"USA");

        test.generateVisitWhitelist();

        boolean expected = false;
        boolean actual = test.getCanVisit();

        assertEquals(expected,actual);
    }
    @Test
    public void ShouldAcceptWhiteListEmpty(){
        AirportEntity ta = new AirportEntity("TST","USA","TestCity",true,"NULL", false, "Test Transit Info");

        ResultGenerator test = new ResultGenerator(ta,"USA");

        test.generateVisitWhitelist();

        boolean expected = false;
        boolean actual = test.getCanVisit();

        assertEquals(expected,actual);

    }
    @Test
    public void ShouldAcceptBlacklistCanTransit(){
        AirportEntity ta = new AirportEntity("TST","Russia","TestCity",true,"NULL", true, "China,India,Poland");

        ResultGenerator test = new ResultGenerator(ta,"USA");

        test.generateTransitBlacklist();

        boolean expected = true;
        boolean actual = test.getCanTransit();

        assertEquals(expected,actual);

    }
    @Test
    public void ShouldRejectBlacklistCanTransit(){
        AirportEntity ta = new AirportEntity("TST","Russia","TestCity",true,"NULL", true, "China,India,Poland");

        ResultGenerator test = new ResultGenerator(ta,"Poland");

        test.generateTransitBlacklist();

        boolean expected = false;
        boolean actual = test.getCanTransit();

        assertEquals(expected,actual);

    }

    @Test
    public void ShouldAcceptTransitBlackListEmpty(){
        AirportEntity ta = new AirportEntity("TST","Russia","TestCity",true,"NULL", true, "NULL");

        ResultGenerator test = new ResultGenerator(ta,"Poland");

        test.generateTransitBlacklist();

        boolean expected = true;
        boolean actual = test.getCanTransit();

        assertEquals(expected,actual);

    }
}