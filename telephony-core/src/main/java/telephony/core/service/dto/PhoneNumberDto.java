package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumberDto {

    private String prefix;

    private String number;

    public PhoneNumberDto() {}

    public PhoneNumberDto(String prefix, String number) {
        this.prefix = prefix;
        this.number = number;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumberDto that = (PhoneNumberDto) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prefix != null ? prefix.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
