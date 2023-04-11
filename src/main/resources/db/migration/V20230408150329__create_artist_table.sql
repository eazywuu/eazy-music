CREATE TABLE artist
(
    `id` VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '歌手ID',
    `name` VARCHAR(64) NOT NULL COMMENT '歌手名',
    `remark` VARCHAR(128) NULL COMMENT '备注',
    `photo_id` VARCHAR(32) NULL COMMENT '歌手照片ID',
    `status` VARCHAR(32) DEFAULT 'DRAFT' NOT NULL COMMENT '歌单状态，DRAFT-草稿，PUBLISHED-已上架，BLOCKED-已封禁',
    `created_by_user_id` VARCHAR(32) NOT NULL COMMENT '创建者ID',
    `updated_by_user_id` VARCHAR(32) NOT NULL COMMENT '最后更新者ID',
    `created_time` datetime(6) NOT NULL COMMENT '创建时间',
    `updated_time` datetime(6) NOT NULL COMMENT '更新时间',
    CONSTRAINT `c_artist_photo_id`
        FOREIGN KEY (`photo_id`) REFERENCES `file` (`id`),
    CONSTRAINT `c_artist_created_by_id`
        FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `c_artist_updated_by_id`
        FOREIGN KEY (`updated_by_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '歌手表';
