package Back.Controllers;

import Back.Models.Items;
import Back.Models.Payment;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    public Payment getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Payment> getAll(){
        return FileDataBaseService.getInstance().getAllPayment();
    }

    public void createNew(Payment payment){}
    public void update(Payment payment){}
    public void delete(Payment payment){}
}
