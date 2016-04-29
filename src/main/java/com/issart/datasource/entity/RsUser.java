package com.issart.datasource.entity;
 
import com.issart.datasource.dao.RSUserDaoImpl;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import java.io.Serializable;
/**
 *
 * Class represent RsUser attributes
 */
@DatabaseTable(daoClass = RSUserDaoImpl.class)
public class RsUser extends  BaseEntity implements Serializable {
/*
create table rs_user (
  user_id serial
 ,user_name text not null
 ,user_hire_date date not null
 ,user_dismiss_date date not null
 ,user_password_hash text not null
 ,user_password_salt text not null
 ,user_is_administrator boolean
 ,constraint rs_user_pk primary key (user_id)
 ,constraint rs_user_name_uk unique (user_name)
);
* */
    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(dataType = DataType.STRING, uniqueIndex = true, canBeNull = false)
    String userName;

    @DatabaseField(dataType = DataType.DATE_TIME, canBeNull = false)
    DateTime userHireDate;

    @DatabaseField(dataType = DataType.DATE_TIME, canBeNull = false)
    DateTime userDismissDate;

    @DatabaseField(dataType = DataType.STRING, canBeNull = false)
    String userPasswordHash;

    @DatabaseField(dataType = DataType.STRING, canBeNull = false)
    String userPasswordSalt;

    @DatabaseField(canBeNull = false)
    boolean userIsAdministrator;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = this.id;
    }

    public boolean IsAdministrator() {
        return userIsAdministrator;
    }

    public void setIsAdministrator(boolean userIsAdministrator) {
        this.userIsAdministrator = userIsAdministrator;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = userName;
    }

    public String getUserPasswordHash() {
        return userPasswordHash;
    }

    public void setUserPasswordHash(String userPasswordHash) {
        this.userPasswordHash = userPasswordHash;
    }

    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (this.getClass() != obj.getClass()))
            return false;
        RsUser rsUser = (RsUser)obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(userName, rsUser.userName)
                .append(userPasswordHash, rsUser.userPasswordHash)
                .isEquals();
    }

    public DateTime getUserHireDate() {
        return userHireDate;
    }

    public void setUserHireDate(DateTime userHireDate) {
        this.userHireDate = userHireDate;
    }

    public DateTime getUserDismissDate() {
        return userDismissDate;
    }

    public void setUserDismissDate(DateTime userDismissDate) {
        this.userDismissDate = userDismissDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7,89)
            .append(userName)
            .append(userPasswordHash)
            .toHashCode();
    }
}