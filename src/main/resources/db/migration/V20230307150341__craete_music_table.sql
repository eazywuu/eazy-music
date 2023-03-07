CREATE TABLE music
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '音乐ID',
    name VARCHAR(64) NOT NULL COMMENT '音乐名',
    description TEXT NULL COMMENT '音乐简介',
    status VARCHAR(32) DEFAULT 'DRAFT' NOT NULL COMMENT '音乐上架状态，DRAFT-草稿，PUBLISHED-已上架，CLOSED-已下架',
    created_time datetime(6) NOT NULL COMMENT '创建时间',
    updated_time datetime(6) NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '音乐表';
