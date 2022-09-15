package fpt.aptech.EatnEat.repository;

import fpt.aptech.EatnEat.entities.Vieworderdetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewRepository extends JpaRepository<Vieworderdetail, Integer> {

////    @Query("SELECT e FROM View e")
////    Page<View> findAll(Pageable pageable);
//    @Query("SELECT v FROM Vieworderdetail v WHERE DATEDIFF(day,v.orderdate,getdate())=0")
//    List<Vieworderdetail> OrdersOfDayList();
//    
//    @Query("SELECT v FROM Vieworderdetail v WHERE v.empid = :empid")
//    List<Vieworderdetail> findByEmp(String empid);
    
    @Query("SELECT v FROM Vieworderdetail v WHERE v.orderid = :orderid")
    List<Vieworderdetail> findByOrder(Integer orderid);
    
    
    
}
