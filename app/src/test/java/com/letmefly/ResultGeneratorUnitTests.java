package com.letmefly;

import com.letmefly.databases.entities.AirportEntity;
import com.letmefly.logic.ResultGenerator;
import com.letmefly.databases.entities.Result;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ResultGeneratorUnitTests {

    private AirportEntity testAirport = new AirportEntity("TST","USA","TestCity",false,
                                        "NULL", false, "NULL","NULL","NULL");

    @Test
    public void ShouldNotAcceptVist(){
        ResultGenerator test = new ResultGenerator(testAirport,"England");
        Result result = test.getResult();

        boolean expected = false;
        boolean actual = result.isCanVisit();

        assertEquals(expected,actual);

    }

    @Test
    public void ShouldNotAcceptTransit(){
        ResultGenerator test = new ResultGenerator(testAirport,"England");
        Result result = test.getResult();

        boolean expected = false;
        boolean actual = result.isCanTransit();

        assertEquals(expected,actual);

    }


    @Test
    public void ShouldNotAcceptVisitEnglandBlackList(){
        //system accepts based on true/false of can_visit, then rejects the result based on visit_info i.e. blacklist.

        AirportEntity testAirport = new AirportEntity("TST","USA","TestCity",true,
                "England", false, "NULL","NULL","NULL");

        ResultGenerator test = new ResultGenerator(testAirport,"England");
        Result result = test.getResult();

        boolean expected = false;
        boolean actual = result.isCanVisit();

        assertEquals(expected,actual);

    }

    @Test
    public void ShouldAcceptVisitEnglandBlackList(){
        //system accepts based on true/false of can_visit, then rejects the result based on visit_info i.e. blacklist.

        AirportEntity testAirport = new AirportEntity("TST","USA","TestCity",false,
                "England", false, "NULL","NULL","NULL");

        ResultGenerator test = new ResultGenerator(testAirport,"England");
        Result result = test.getResult();

        boolean expected = true;
        boolean actual = result.isCanVisit();

        assertEquals(expected,actual);

    }

    @Test
    public void ShouldNotAcceptTransitEnglandBlackList(){
        AirportEntity testAirport2 = new AirportEntity("TST","USA","TestCity",false,
                "NULL", true, "England","NULL","NULL");

        ResultGenerator test = new ResultGenerator(testAirport2,"England");
        Result result = test.getResult();

        boolean expected = false;
        boolean actual = result.isCanTransit();

        assertEquals(expected,actual);

    }
    @Test
    public void ShouldAcceptTransitEnglandBlackList(){
        AirportEntity testAirport2 = new AirportEntity("TST","USA","TestCity",false,
                "NULL", false, "England","NULL","NULL");

        ResultGenerator test = new ResultGenerator(testAirport2,"England");
        Result result = test.getResult();

        boolean expected = true;
        boolean actual = result.isCanTransit();

        assertEquals(expected,actual);

    }

    @Test
    public void ShouldAcceptVisitEnglandButNoTransit(){
        AirportEntity testAirport2 = new AirportEntity("TST","USA","TestCity",true,
                "NULL", true, "England","NULL","NULL");

        ResultGenerator test = new ResultGenerator(testAirport2,"England");
        Result result = test.getResult();

        boolean expected = true;
        boolean actual = result.isCanVisit();

        assertEquals(expected,actual);

    }

    @Test
    public void ShouldAcceptVisitEnglandIfPassengerEnglish(){
        AirportEntity testAirport2 = new AirportEntity("TST","United Kingdom","TestCity",false,
                "NULL", false, "NULL","NULL","NULL");

        ResultGenerator test = new ResultGenerator(testAirport2,"United Kingdom");
        Result result = test.getResult();

        boolean expected = true;
        boolean actual = result.isCanVisit();

        assertEquals(expected,actual);

    }
}