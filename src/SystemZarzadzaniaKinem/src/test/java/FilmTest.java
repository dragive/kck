import Back.Controllers.FilmController;
import Back.Controllers.Interfaces.FileJSONDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FilmTest {
    @Test
    public void fimCOntrollerReadAllTestZero(){
        FileJSONDatabase filmController = new FileJSONDatabase();
        List ret = filmController.getAllFilm();
        System.out.println(ret);
        Assert.assertTrue(!ret.isEmpty());
    }
}
