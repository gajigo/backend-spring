package br.com.uniamerica.gajigo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {
    @Override
    @Query("select e from #{#entityName} e where e.active=true")
    Page<T> findAll(Pageable pageable);

    @RestResource(path = "inactive", rel = "inactive")
    Page<T> findByActiveIsFalse(Pageable pageable);

    @RestResource(path = "all", rel = "all")
    Page<T> findByActiveIsNotNull(Pageable pageable);
}
