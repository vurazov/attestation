package com.issart.datasource.entity;

import org.apache.commons.lang3.builder.Builder;
import org.joda.time.DateTime;

/**
 * Created by Admin on 30.04.2016.
 */
public class RsActivityBuilder implements Builder {

    private RsActivity rsActivity;

    private RsActivityBuilder() {
        rsActivity = new RsActivity();
    }

    public static RsActivityBuilder create() {
        return new RsActivityBuilder();
    }

    public RsActivityBuilder rsActivityId(int rsActivityId) {
        rsActivity.id = rsActivityId;
        return this;
    }

    public RsActivityBuilder rsActivityDate(DateTime activityDate) {
        rsActivity.rsActivityDate = activityDate;
        return this;
    }

    public RsActivityBuilder rsActivityUser(RsUser rsActivityUser) {
        rsActivity.rsActivityUser = rsActivityUser;
        return this;
    }

    public RsActivityBuilder rsCreatedByUser(RsUser rsCreatedByUser) {
        rsActivity.rsCreatedByUser = rsCreatedByUser;
        return this;
    }

    public RsActivityBuilder rsApprovedByUser(RsUser rsApprovedByUser) {
        rsActivity.rsApprovedByUser = rsApprovedByUser;
        return this;
    }

    public RsActivityBuilder rsActivityType(RsActivityType rsActivityType) {
        rsActivity.rsActivityType = rsActivityType;
        return this;
    }

    public RsActivityBuilder rsActivityTypeLoyaltyScore(Integer rsActivityTypeLoyaltyScore) {
        rsActivity.rsActivityTypeLoyaltyScore = rsActivityTypeLoyaltyScore;
        return this;
    }

    public RsActivityBuilder rsActivityTypeExpirienceScore(Integer rsActivityTypeExpirienceScore) {
        rsActivity.rsActivityTypeExpirienceScore = rsActivityTypeExpirienceScore;
        return this;
    }

    public RsActivityBuilder rsActivityTypeCommunicationScore(Integer rsActivityTypeCommunicationScore) {
        rsActivity.rsActivityTypeCommunicationScore = rsActivityTypeCommunicationScore;
        return this;
    }

    public RsActivity build() {
        return rsActivity;
    }
}
