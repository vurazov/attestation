package com.issart.datasource.dao;

import java.util.List;
import java.util.Optional;
import com.issart.datasource.entity.RsActivityType;
import com.issart.exception.DataSourceException;
import com.j256.ormlite.dao.Dao;

/**
 * Created by vurazov on 29.04.2016.
 */
public interface IRSActivityTypeDao extends Dao<RsActivityType, Integer> {

  Optional<RsActivityType> find(RsActivityType entry) throws DataSourceException;

  Optional<List<RsActivityType>> getListActivityType() throws DataSourceException;

  void modify(RsActivityType entry) throws DataSourceException;

  Optional<RsActivityType> createActivityType(RsActivityType entry) throws DataSourceException;
}
