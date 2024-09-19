package com.project.p_delivery_manager.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeliveryManager is a Querydsl query type for DeliveryManager
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryManager extends EntityPathBase<DeliveryManager> {

    private static final long serialVersionUID = 1608534725L;

    public static final QDeliveryManager deliveryManager = new QDeliveryManager("deliveryManager");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final ComparablePath<java.util.UUID> hubId = createComparable("hubId", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final BooleanPath is_delete = _super.is_delete;

    public final EnumPath<DeliveryManagerType> type = createEnum("type", DeliveryManagerType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

    public QDeliveryManager(String variable) {
        super(DeliveryManager.class, forVariable(variable));
    }

    public QDeliveryManager(Path<? extends DeliveryManager> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeliveryManager(PathMetadata metadata) {
        super(DeliveryManager.class, metadata);
    }

}

