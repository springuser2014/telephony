package telephony.core.service.dto;

public class ProducerDto {

    Long producerId;
    String label;

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProducerDto dto = (ProducerDto) o;

        if (producerId != null ? !producerId.equals(dto.producerId) : dto.producerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return producerId != null ? producerId.hashCode() : 0;
    }
}
