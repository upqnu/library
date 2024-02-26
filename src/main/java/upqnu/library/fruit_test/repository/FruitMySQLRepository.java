package upqnu.library.fruit_test.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Primary
public class FruitMySQLRepository implements FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitMySQLRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveFruitInfo(String name, LocalDate warehousingDate, Long price) {
        String sql = "INSERT into FRUIT (name, warehousingDate, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, name, warehousingDate, price);
    }

    @Override
    public boolean findFruit(Long id) {
        String sql = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 0, id).isEmpty();
    }

    @Override
    public void findFruitSold(Long id) {
        String sql = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Long getSalesResult(String name) {
        String saleSql = "select sum(price) from fruit where name = ? and is_sold = 1";
        Long salesResult = jdbcTemplate.queryForObject(saleSql, new Object[]{name}, Long.class);
        return salesResult;
    }

    @Override
    public Long getNotSalesResult(String name) {
        String saleSql = "select sum(price) from fruit where name = ? and is_sold = 0";
        Long notSalesResult = jdbcTemplate.queryForObject(saleSql, new Object[]{name}, Long.class);
        return notSalesResult;
    }
}
