package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMeeting is a Querydsl query type for Meeting
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeeting extends EntityPathBase<Meeting> {

    private static final long serialVersionUID = 388231225L;

    public static final QMeeting meeting = new QMeeting("meeting");

    public final NumberPath<Integer> areaId = createNumber("areaId", Integer.class);

    public final StringPath deskId = createString("deskId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QMeeting(String variable) {
        super(Meeting.class, forVariable(variable));
    }

    public QMeeting(Path<? extends Meeting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeeting(PathMetadata metadata) {
        super(Meeting.class, metadata);
    }

}

