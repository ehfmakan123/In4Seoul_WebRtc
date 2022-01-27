package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMeetingHistory is a Querydsl query type for MeetingHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeetingHistory extends EntityPathBase<MeetingHistory> {

    private static final long serialVersionUID = 19499547L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMeetingHistory meetingHistory = new QMeetingHistory("meetingHistory");

    public final QDesks desks;

    public final DateTimePath<java.time.LocalDateTime> endedAt = createDateTime("endedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QStaff staff;

    public final DateTimePath<java.time.LocalDateTime> startedAt = createDateTime("startedAt", java.time.LocalDateTime.class);

    public QMeetingHistory(String variable) {
        this(MeetingHistory.class, forVariable(variable), INITS);
    }

    public QMeetingHistory(Path<? extends MeetingHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMeetingHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMeetingHistory(PathMetadata metadata, PathInits inits) {
        this(MeetingHistory.class, metadata, inits);
    }

    public QMeetingHistory(Class<? extends MeetingHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.desks = inits.isInitialized("desks") ? new QDesks(forProperty("desks"), inits.get("desks")) : null;
        this.staff = inits.isInitialized("staff") ? new QStaff(forProperty("staff"), inits.get("staff")) : null;
    }

}

