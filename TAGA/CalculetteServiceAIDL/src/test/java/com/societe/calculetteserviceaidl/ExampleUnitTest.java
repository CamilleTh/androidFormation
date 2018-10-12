package com.societe.calculetteserviceaidl;

import com.societe.calculetteserviceaidl.CalculetteService.Stub;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        CalculetteServ cs = new CalculetteServ();
        double result = ((Stub)cs.onBind(null)).additionner(3.0,3.0);
        assertEquals(result, 3 + 3);
    }
}