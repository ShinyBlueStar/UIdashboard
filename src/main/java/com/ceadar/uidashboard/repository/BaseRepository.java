package com.ceadar.uidashboard.repository;

import com.ceadar.uidashboard.datamodel.entity.GeneralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<E extends GeneralEntity, ID extends Serializable>
        extends PagingAndSortingRepository<E, ID>,
        JpaSpecificationExecutor<E>, JpaRepository<E, ID> {

    List<E> findAllByIsDoneIsFalse();
}
