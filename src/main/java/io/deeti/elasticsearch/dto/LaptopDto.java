package io.deeti.elasticsearch.dto;

import java.io.Serializable;
import java.util.Objects;

public class LaptopDto implements Serializable {
    private String laptopId;
    private String laptopModel;
    private String brand;

    public LaptopDto() {
    }

    public LaptopDto(String id, String laptopModel, String brand) {
        this.laptopId = id;
        this.laptopModel = laptopModel;
        this.brand = brand;
    }

    public LaptopDto(String laptopModel) {
        this.laptopModel = laptopModel;
    }

    public String getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(String laptopId) {
        this.laptopId = laptopId;
    }

    public String getLaptopModel() {
        return laptopModel;
    }

    public void setLaptopModel(String laptopModel) {
        this.laptopModel = laptopModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaptopDto entity = (LaptopDto) o;
        return Objects.equals(this.laptopId, entity.laptopId) &&
                Objects.equals(this.laptopModel, entity.laptopModel) &&
                Objects.equals(this.brand, entity.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laptopId, laptopModel, brand);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + laptopId + ", " +
                "laptopModel = " + laptopModel + ", " +
                "brand = " + brand + ")";
    }
}
