package Back.Services;

import Back.Models.*;

import java.util.Arrays;
import java.util.List;

public abstract class DataBaseService implements IDataBaseService {
    private static DataBaseService instance;
    public static DataBaseService getInstance(){
        if(instance == null){
            instance = FileDataBaseService.getInstance();
        }
        return instance;
    }
}
