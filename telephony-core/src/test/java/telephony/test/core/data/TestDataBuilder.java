package telephony.test.core.data;

import org.joda.time.DateTime;
import telephony.core.entity.jpa.Model;
import telephony.core.entity.jpa.Pricing;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductTax;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProductDto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// TODO add to the class services
public class TestDataBuilder {

    public static ProductDto getProductB(ModelDto model) {
        ProductDto p = new ProductDto();
        p.setColor("niebieski");
        p.setImei("123451234512345");
        p.setModelId(model.getId());

        p.setCurrentPrice(20.0);
        p.setPriceIn(100.0);

        return p;
    }

    public static ProductDto getProductA(ModelDto model) {
        ProductDto p = new ProductDto();
        p.setColor("zielony");
        p.setImei("098760987609876");
        p.setModelId(model.getId());

        p.setCurrentPrice(20.0);
        p.setPriceIn(110.0);

        return p;
    }

    public static Date getFutureDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return new DateTime().withDate(year + 1, 11, 11).withTime(0,0,0,0).toDate();
    }

    public static Date getPastDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return new DateTime().withDate(year - 1, 11, 11).withTime(0,0,0,0).toDate();
    }
}
