package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDesk is a Querydsl query type for Desk
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDesk extends EntityPathBase<Desk> {

    private static final long serialVersionUID = 846023003L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesk desk = new QDesk("desk");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final StringPath altitude = createString("altitude");

    public final QArea area;

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final StringPath deleteYN = createString("deleteYN");

    public final StringPath deskId = createString("deskId");

    public final StringPath engName = createString("engName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath korName = createString("korName");

    public final StringPath latitude = createString("latitude");

    public final StringPath password = createString("password");

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QDesk(String variable) {
        this(Desk.class, forVariable(variable), INITS);
    }

    public QDesk(Path<? extends Desk> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDesk(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDesk(PathMetadata metadata, PathInits inits) {
        this(Desk.class, metadata, inits);
    }

    public QDesk(Class<? extends Desk> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new QArea(forProperty("area")) : null;
    }

}

