package com.hongdaestudy.recipebackend.common;

import org.springframework.data.domain.AbstractAggregateRoot;

public abstract class BaseEntity<ENTITY, ID> extends AbstractAggregateRoot<BaseEntity<ENTITY, ID>> {
}
