package upqnu.library.fruit_test.dto;

import upqnu.library.fruit_test.entity.Fruit;

import java.time.LocalDate;

public class FruitInfoResponse {
    private Long id;
    private String name;
    private LocalDate warehousingDate;
    private Long price;

    public FruitInfoResponse(String name, LocalDate warehousingDate, Long price) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }

    public FruitInfoResponse(Fruit fruit) {
        this.id = fruit.getId();
        this.name = fruit.getName();
        this.warehousingDate = fruit.getWarehousingDate();
        this.price = fruit.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public void setWarehousingDate(LocalDate warehousingDate) {
        this.warehousingDate = warehousingDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
