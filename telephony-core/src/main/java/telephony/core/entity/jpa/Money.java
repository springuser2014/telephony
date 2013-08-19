package telephony.core.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * Represents Money domain object.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
@Embeddable
public class Money implements Serializable {

    private static final long serialVersionUID = 621818883510132071L;

    @Column(name = "money_value")
    private Long value;


    /**
     * Initialize - 0 zł.
     */
    public Money() {
        this(new Long(0));
    }

    /**
     * Initailize with given fractional part.
     * @param gr asd.
     */
    public Money(Long gr) {
        this.setValue(gr);
    }

    /**
     * Initailize with given entire and fractional part.
     * @param zl aasd.
     * @param gr aasd.
     */
    public Money(int zl, int gr) {
        if (zl < 0) {
            throw new IllegalArgumentException("illegal range: zl < 0!");
        }
        if (gr < 0) {
            throw new IllegalArgumentException("illegal range: gr < 0!");
        }
        this.setValue(new Long(zl * 100 + gr));
    }


    /**
     * Format given number to string representation of the decimal.
     * @param v number in fractional part.
     * @return asd.
     */
    public static String formatValue(Long v) {
        if (v == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append((long) Math.floor(v.longValue() / 100.0));
            sb.append(".");

            long gr = v.longValue() % 100;
            if (gr < 10) {
                sb.append("0");
            }
            sb.append(gr);

            return sb.toString();
        }
    }

    /**
     * Compares object with another one.
     * @param obj to compare with.
     * @return boolean value, if are equals returns true otherwise false.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Money)) {
            return false;
        }

        final Money other = (Money) obj;
        if (this.value != null && other.value != null) {
            return this.value.equals(other.value);
        }

        return false;
    }

    /**
     * Generates hashCode for given entity.
     * @return A number.
     */
    @Override
    public final int hashCode() {
        if (value == null) {
            return 0;
        } else {
            return value.hashCode();
        }
    }

    /**
     * Sets money value.
     * @param value in the fractional part.
     */
    public final void setValue(Long value) {
        this.value = value;
    }

    /**
     * asd.
     * @return asd.
     */
    public final Long getValue() {
        return value;
    }

    /**
     * asd.
     * @return asd.
     */
    @Transient
    public final String getLabel() {
        String v = toString();
        if ("".equals(v)) {
            return "";
        }
        return v + " zł";
    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final String toString() {
        return Money.formatValue(this.value);
    }

}
