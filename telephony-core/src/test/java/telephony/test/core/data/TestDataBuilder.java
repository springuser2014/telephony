package telephony.test.core.data;

import org.joda.time.DateTime;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.PricingDto;
import telephony.core.service.dto.ProductAddDto;

import java.util.Calendar;
import java.util.Date;

// TODO add to the class services
public class TestDataBuilder {

    public static ProductAddDto getProductB(ModelDto model) {
        ProductAddDto p = new ProductAddDto();
        p.setColor("niebieski");
        p.setImei("123451234512345");
        p.setModel(model.getLabel());

        PricingDto dto = new PricingDto();
        dto.setFrom(new Date());
        dto.setTo(null);
        dto.setRate(200.0d);
        p.setCurrentPrice(dto);
        p.setPriceIn(100.0);

        return p;
    }

    public static ProductAddDto getProductA(ModelDto model) {
        ProductAddDto p = new ProductAddDto();
        p.setColor("zielony");
        p.setImei("098760987609876");
        p.setModel(model.getLabel());

        PricingDto dto = new PricingDto();
        dto.setFrom(new Date());
        dto.setTo(null);
        dto.setRate(200.0d);
        p.setCurrentPrice(dto);
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

    public static Date getDate(int year, int month, int day, int hour, int minute, int second, int milisec) {
        return new DateTime()
            .withDate(year, month, day)
            .withTime(hour, minute, second, milisec)
            .toDate();
    }

    public static Date getDate(int year, int month, int day) {
        return getDate(year, month, day, 0,0,0,0);
    }
}
