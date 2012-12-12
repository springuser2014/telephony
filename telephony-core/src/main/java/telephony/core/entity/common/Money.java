package telephony.core.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
public class Money implements Serializable {

    private static final long serialVersionUID = 621818883510132071L;

    @Column(name = "money_value")
    private Long value;


    public Money() {
        this(new Long(0));
    }

    public Money(Long gr) {
        this.setValue(gr);
    }

    public Money(int zl, int gr) {
        if (zl < 0)
            throw new IllegalArgumentException("illegal range: zl < 0!");
        if (gr < 0)
            throw new IllegalArgumentException("illegal range: gr < 0!");
        this.setValue(new Long(zl * 100 + gr));
    }

    public static String formatValue(Long v) {
        if (v == null)
            return "";
        else {
            StringBuilder sb = new StringBuilder();
            sb.append((long) Math.floor(v.longValue() / 100.0));
            sb.append(".");

            long gr = v.longValue() % 100;
            if (gr < 10)
                sb.append("0");
            sb.append(gr);

            return sb.toString();
        }
    }

    public boolean equals(final Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Money))
            return false;

        final Money other = (Money) obj;
        if (this.value != null && other.value != null)
            return this.value.equals(other.value);

        return false;
    }

    public int hashCode() {
        if (value == null)
            return 0;
        else
            return value.hashCode();
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Transient
    public String getLabel() {
        String v = toString();
        if ("".equals(v))
            return "";
        return v + " zł";
    }

    public String toString() {
        return Money.formatValue(this.value);
    }

}
