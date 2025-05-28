package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryProvider extends JpaRepository<Provider, Long> {
}
