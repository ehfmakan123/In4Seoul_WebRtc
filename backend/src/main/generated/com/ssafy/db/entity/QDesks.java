package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDesks is a Querydsl query type for Desks
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDesks extends EntityPathBase<Desks> {

    private static final long serialVersionUID = 456909432L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDesks desks = new QDesks("desks");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final StringPath altitude = createString("altitude");

    public final QAreas area;

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

    public QDesks(String variable) {
        this(Desks.class, forVariable(variable), INITS);
    }

    public QDesks(Path<? extends Desks> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDesks(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDesks(PathMetadata metadata, PathInits inits) {
        this(Desks.class, metadata, inits);
    }

    public QDesks(Class<? extends Desks> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new QAreas(forProperty("area")) : null;
    }

}

