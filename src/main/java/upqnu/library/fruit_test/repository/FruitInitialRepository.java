package upqnu.library.fruit_test.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class FruitInitialRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitInitialRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public FruitInfoResponse saveFruitInfo(FruitInfoRequest request) {
    public void saveFruitInfo(String name, LocalDate warehousingDate, Long price) {
        String sql = "INSERT into FRUIT (name, warehousingDate, price) values (?, ?, ?)";
//        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
        jdbcTemplate.update(sql, name, warehousingDate, price);

//        FruitInfoResponse response = new FruitInfoResponse(request.getName(), request.getWarehousingDate(), request.getPrice());
//        return response;
    }

    public boolean findFruit(Long id) {
        String sql = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void findFruitSold(Long id) {
        String sql = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Long getSalesResult(String name) {
        String saleSql = "select sum(price) from fruit where name = ? and is_sold = 1";
        Long salesResult = jdbcTemplate.queryForObject(saleSql, new Object[]{name}, Long.class);
        return salesResult;
    }

    public Long getNotSalesResult(String name) {
        String saleSql = "select sum(price) from fruit where name = ? and is_sold = 0";
        Long notSalesResult = jdbcTemplate.queryForObject(saleSql, new Object[]{name}, Long.class);
        return notSalesResult;
    }
}
