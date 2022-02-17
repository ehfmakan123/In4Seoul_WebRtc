package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMeetingLog is a Querydsl query type for MeetingLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeetingLog extends EntityPathBase<MeetingLog> {

    private static final long serialVersionUID = -550427573L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMeetingLog meetingLog = new QMeetingLog("meetingLog");

    public final StringPath content = createString("content");

    public final QDesk desk;

    public final StringPath endedAt = createString("endedAt");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QStaff staff;

    public final StringPath startedAt = createString("startedAt");

    public QMeetingLog(String variable) {
        this(MeetingLog.class, forVariable(variable), INITS);
    }

    public QMeetingLog(Path<? extends MeetingLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMeetingLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMeetingLog(PathMetadata metadata, PathInits inits) {
        this(MeetingLog.class, metadata, inits);
    }

    public QMeetingLog(Class<? extends MeetingLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.desk = inits.isInitialized("desk") ? new QDesk(forProperty("desk"), inits.get("desk")) : null;
        this.staff = inits.isInitialized("staff") ? new QStaff(forProperty("staff"), inits.get("staff")) : null;
    }

}

