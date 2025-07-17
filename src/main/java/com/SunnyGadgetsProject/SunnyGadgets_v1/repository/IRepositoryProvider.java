package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryProvider extends JpaRepository<Provider, Long> {
    //Query's
    @Query(value = "SELECT e.name, e.salary AS salary FROM providers p INNER JOIN employes e ON e.id_employee = p.id_provider"
            , nativeQuery = true)
    List<NameTotalSalarySeller> getProvidersSalary();
}
