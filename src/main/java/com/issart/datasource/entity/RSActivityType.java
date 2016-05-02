package com.issart.datasource.entity;

import com.issart.datasource.dao.RSActivityTypeDaoImpl;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;

/**
 * Created by vurazov on 29.04.2016.
 */
@DatabaseTable(daoClass = RSActivityTypeDaoImpl.class)
public class RsActivityType extends  BaseEntity implements Serializable {
    /*
    create table rs_activity_type (
      activity_type_id serial
     ,activity_type_name text not null
     ,activity_type_default_loyalty_score integer
     ,activity_type_default_expirience_score integer
     ,activity_type_default_communication_score integer
     ,constraint rs_activity_type_pk primary key (activity_type_id)
    );
    * */
    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(dataType = DataType.STRING, canBeNull = false)
    String activityTypeName;

    @DatabaseField(dataType = DataType.INTEGER, canBeNull = true)
    Integer activityTypeDefaultLoyaltyScore;

    @DatabaseField(dataType = DataType.INTEGER, canBeNull = true)
    Integer activityTypeDefaultExpirienceScore;

    @DatabaseField(dataType = DataType.INTEGER, canBeNull = true)
    Integer activityTypeDefaultCommunicationScore;


    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getActivityTypeDefaultLoyaltyScore() {
        return activityTypeDefaultLoyaltyScore;
    }

    public void setActivityTypeDefaultLoyaltyScore(Integer activityTypeDefaultLoyaltyScore) {
        this.activityTypeDefaultLoyaltyScore = activityTypeDefaultLoyaltyScore;
    }

    public Integer getActivityTypeDefaultExpirienceScore() {
        return activityTypeDefaultExpirienceScore;
    }

    public void setActivityTypeDefaultExpirienceScore(Integer activityTypeDefaultExpirienceScore) {
        this.activityTypeDefaultExpirienceScore = activityTypeDefaultExpirienceScore;
    }

    public Integer getActivityTypeDefaultCommunicationScore() {
        return activityTypeDefaultCommunicationScore;
    }

    public void setActivityTypeDefaultCommunicationScore(Integer activityTypeDefaultCommunicationScore) {
        this.activityTypeDefaultCommunicationScore = activityTypeDefaultCommunicationScore;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7,89)
            .append(activityTypeName)
            .toHashCode();
    }
}
