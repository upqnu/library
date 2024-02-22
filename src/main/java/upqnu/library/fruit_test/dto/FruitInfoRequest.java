package upqnu.library.fruit_test.dto;

import java.time.LocalDate;

public class FruitInfoRequest {
    private String name;
    private LocalDate warehousingDate;
    private Long price;

    public FruitInfoRequest(String name, LocalDate warehousingDate, Long price) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public Long getPrice() {
        return price;
    }
}
