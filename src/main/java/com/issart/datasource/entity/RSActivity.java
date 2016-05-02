package com.issart.datasource.entity;

import com.issart.datasource.dao.RSActivityDaoImpl;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

/**
 *
 * Class represent RsUser attributes
 */
@DatabaseTable(daoClass = RSActivityDaoImpl.class)
public class RsActivity extends  BaseEntity {
    /*
create table rs_activity (
  activity_id serial
 ,activity_date date not null
 ,activity_user_id integer not null
 ,activity_type_id integer not null

 ,activity_loyalty_score integer
 ,activity_expirience_score integer
 ,activity_communication_score integer
 ,activity_created_by_user_id integer not null
 ,activity_approved_by_user_id integer

 ,constraint rs_activity_pk primary key (activity_id)
 ,constraint rs_activity_user_fk foreign key (activity_user_id)
    references rs_user(user_id)
 ,constraint rs_activity_creator_fk foreign key (activity_created_by_user_id)
    references rs_user(user_id)
 ,constraint rs_activity_approver_fk foreign key (activity_approved_by_user_id)
    references rs_user(user_id)
 ,constraint rs_activity_scores_ck check (activity_loyalty_score is not null or activity_expirience_score is not null or activity_communication_score is not null)
);
   * */

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(dataType = DataType.DATE_TIME, canBeNull = false)
    DateTime rsActivityDate;

    @DatabaseField(foreignColumnName = "id", foreign = true, canBeNull = false)
    RsUser rsActivityUser;

    @DatabaseField(foreignColumnName = "id", foreign = true, canBeNull = false)
    RsUser rsCreatedByUser;

    @DatabaseField(foreignColumnName = "id", foreign = true, canBeNull = true)
    RsUser rsApprovedByUser;

    @DatabaseField(foreignColumnName = "id", foreign = true, canBeNull = true)
    RsActivityType rsActivityType;

    @DatabaseField(dataType = DataType.INTEGER, canBeNull = true)
    Integer rsActivityTypeLoyaltyScore;

    @DatabaseField(dataType = DataType.INTEGER, canBeNull = true)
    Integer rsActivityTypeExpirienceScore;

    @DatabaseField(dataType = DataType.INTEGER, canBeNull = true)
    Integer rsActivityTypeCommunicationScore;

    public DateTime getRsActivityDate() {
        return rsActivityDate;
    }

    public void setRsActivityDate(DateTime rsActivityDate) {
        this.rsActivityDate = rsActivityDate;
    }

    public RsUser getRsActivityUser() {
        return rsActivityUser;
    }

    public void setRsActivityUser(RsUser rsActivityUser) {
        this.rsActivityUser = rsActivityUser;
    }

    public RsUser getRsCreatedByUser() {
        return rsCreatedByUser;
    }

    public void setRsCreatedByUser(RsUser rsCreatedByUser) {
        this.rsCreatedByUser = rsCreatedByUser;
    }

    public RsUser getRsApprovedByUser() {
        return rsApprovedByUser;
    }

    public void setRsApprovedByUser(RsUser rsApprovedByUser) {
        this.rsApprovedByUser = rsApprovedByUser;
    }

    public RsActivityType getRsActivityType() {
        return rsActivityType;
    }

    public void setRsActivityType(RsActivityType rsActivityType) {
        this.rsActivityType = rsActivityType;
    }

    public Integer getRsActivityTypeLoyaltyScore() {
        return rsActivityTypeLoyaltyScore;
    }

    public void setRsActivityTypeLoyaltyScore(Integer rsActivityTypeLoyaltyScore) {
        this.rsActivityTypeLoyaltyScore = rsActivityTypeLoyaltyScore;
    }

    public Integer getRsActivityTypeExpirienceScore() {
        return rsActivityTypeExpirienceScore;
    }

    public void setRsActivityTypeExpirienceScore(Integer rsActivityTypeExpirienceScore) {
        this.rsActivityTypeExpirienceScore = rsActivityTypeExpirienceScore;
    }

    public Integer getRsActivityTypeCommunicationScore() {
        return rsActivityTypeCommunicationScore;
    }

    public void setRsActivityTypeCommunicationScore(Integer rsActivityTypeCommunicationScore) {
        this.rsActivityTypeCommunicationScore = rsActivityTypeCommunicationScore;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
