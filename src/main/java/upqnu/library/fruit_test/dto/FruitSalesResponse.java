package upqnu.library.fruit_test.dto;

public class FruitSalesResponse {
    private Long salesAmount;
    private Long notSalesAmount;

    public FruitSalesResponse(Long salesAmount, Long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public Long getSalesAmount() {
        return salesAmount;
    }

    public Long getNotSalesAmount() {
        return notSalesAmount;
    }

}
