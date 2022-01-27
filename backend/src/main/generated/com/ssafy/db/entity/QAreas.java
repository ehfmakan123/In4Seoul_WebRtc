package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAreas is a Querydsl query type for Areas
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAreas extends EntityPathBase<Areas> {

    private static final long serialVersionUID = 454512388L;

    public static final QAreas areas = new QAreas("areas");

    public final StringPath engName = createString("engName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath korName = createString("korName");

    public QAreas(String variable) {
        super(Areas.class, forVariable(variable));
    }

    public QAreas(Path<? extends Areas> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAreas(PathMetadata metadata) {
        super(Areas.class, metadata);
    }

}

