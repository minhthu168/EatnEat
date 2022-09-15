
package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Vieworders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lenovo
 */
public interface ViewOrdersRepository extends JpaRepository<Vieworders, Integer> {
    @Query("SELECT o FROM Vieworders o WHERE o.empid = :empid ORDER BY o.orderdate DESC")
    List<Vieworders> searchOrder(String empid);
}
