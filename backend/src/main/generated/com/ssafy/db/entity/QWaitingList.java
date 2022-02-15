package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWaitingList is a Querydsl query type for WaitingList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWaitingList extends EntityPathBase<WaitingList> {

    private static final long serialVersionUID = 1473135497L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWaitingList waitingList = new QWaitingList("waitingList");

    public final QArea area;

    public final QDesk desk;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QWaitingList(String variable) {
        this(WaitingList.class, forVariable(variable), INITS);
    }

    public QWaitingList(Path<? extends WaitingList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWaitingList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWaitingList(PathMetadata metadata, PathInits inits) {
        this(WaitingList.class, metadata, inits);
    }

    public QWaitingList(Class<? extends WaitingList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new QArea(forProperty("area")) : null;
        this.desk = inits.isInitialized("desk") ? new QDesk(forProperty("desk"), inits.get("desk")) : null;
    }

}

