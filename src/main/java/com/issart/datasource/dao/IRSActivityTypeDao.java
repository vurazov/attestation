package com.issart.datasource.dao;

import java.util.Optional;
import com.issart.datasource.entity.RSActivityType;
import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.Dao;

/**
 * Created by vurazov on 29.04.2016.
 */
public interface IRSActivityTypeDao extends Dao<RSActivityType, Integer> {

  Optional<RSActivityType> find(RSActivityType entry) throws DataSourceException;

  void modify(RSActivityType entry) throws DataSourceException;

  Optional<RSActivityType> createActivityType(RSActivityType entry) throws DataSourceException;
}
