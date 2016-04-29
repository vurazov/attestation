package com.issart.datasource.entity;

public abstract class BaseEntity {

    public abstract int getId();

    protected static boolean equals(BaseEntity entity1, BaseEntity entity2) {
        if (entity1 == entity2)
            return true;
        if (entity1 == null)
            return false;
        else if (entity2 == null)
            return false;
        return (entity1.getId() == entity2.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (this.getClass() != obj.getClass()))
            return false;
        return (getId() == ((BaseEntity)obj).getId());
    }
}
