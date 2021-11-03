import Back.Models.Cinema;
import Back.Models.City;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class CityTest{
    @Test
    public void CitySavingNameTest(){

        City city = new City();
        city.setName("123");
        assertTrue(city.getName()=="123");
    }

}