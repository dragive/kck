package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Payment;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentController {
    public Payment getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Payment> list){ DataBaseService.getInstance().saveAllPayment(list);}
    public List<Payment> getAll(){
        return FileDataBaseService.getInstance().getAllPayment();
    }

    public void createNew(Payment object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Payment object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Payment object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
