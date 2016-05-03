package com.issart.datasource.entity;

import org.apache.commons.lang3.builder.Builder;

/**
 * Created by Admin on 30.04.2016.
 */
public class RsActivityTypeBuilder implements Builder {
    private RsActivityType rsActivityType;

    private RsActivityTypeBuilder() {
        rsActivityType = new RsActivityType();
    }

    public static RsActivityTypeBuilder create() {
        return new RsActivityTypeBuilder();
    }

    public RsActivityTypeBuilder rsActivityTypeId(Integer rsActivityTypeId) {
        rsActivityType.id = rsActivityTypeId;
        return this;
    }

    public RsActivityTypeBuilder activityTypeName(String activityTypeName) {
        rsActivityType.activityTypeName = activityTypeName;
        return this;
    }

    public RsActivityTypeBuilder activityTypeDefaultLoyaltyScore(int activityTypeDefaultLoyaltyScore) {
        rsActivityType.activityTypeDefaultLoyaltyScore = activityTypeDefaultLoyaltyScore;
        return this;
    }

    public RsActivityTypeBuilder activityTypeDefaultExpirienceScore(int activityTypeDefaultExpirienceScore) {
        rsActivityType.activityTypeDefaultExpirienceScore = activityTypeDefaultExpirienceScore;
        return this;
    }

    public RsActivityTypeBuilder activityTypeDefaultCommunicationScore(int activityTypeDefaultCommunicationScore) {
        rsActivityType.activityTypeDefaultCommunicationScore = activityTypeDefaultCommunicationScore;
        return this;
    }

    public RsActivityType build() {
        return rsActivityType;
    }
}

