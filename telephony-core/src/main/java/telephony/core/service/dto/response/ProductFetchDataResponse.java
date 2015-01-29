package telephony.core.service.dto.response;

import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProducerDto;

import java.util.ArrayList;
import java.util.List;

public class ProductFetchDataResponse extends BasicResponse {

    List<ModelDto> models;
    List<ProducerDto> producers;
    List<String> colors;

    public ProductFetchDataResponse() {
        this.models = new ArrayList<>();
        this.producers = new ArrayList<>();
        this.colors = new ArrayList<String>();
    }

    public void addModel(ModelDto dto) {

        if (!this.models.contains(dto)) {
            this.models.add(dto);
        }
    }

    public void removeModel(ModelDto dto) {

        if (this.models.contains(dto)) {
            this.models.remove(dto);
        }
    }

    public void addProducer(ProducerDto dto) {

        if (!this.producers.contains(dto)) {
            this.producers.add(dto);
        }
    }

    public void removeProducer(ProducerDto dto) {

        if (this.producers.contains(dto)) {
            this.producers.remove(dto);
        }
    }

    public void addColor(String dto) {

        if (!this.colors.contains(dto)) {
            this.colors.add(dto);
        }
    }

    public void removeColor(String dto) {

        if (this.colors.contains(dto)) {
            this.colors.remove(dto);
        }
    }

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
        this.models = models;
    }

    public List<ProducerDto> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerDto> producers) {
        this.producers = producers;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
