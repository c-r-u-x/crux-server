-- role 테이블 생성
create table role
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name enum ('ADMIN', 'USER') not null,
    created_date datetime(6) NULL,
    modified_date datetime(6) NULL,
    CONSTRAINT role_name_unique
        UNIQUE (name)
);

-- member 테이블 생성
CREATE TABLE member (
                        id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        role_id INTEGER NOT NULL,
                        oauth2id VARCHAR(50) NOT NULL,
                        nick_name VARCHAR(255) NULL,
                        description VARCHAR(255) NULL,
                        created_date datetime(6) NULL,
                        modified_date datetime(6) NULL,
                        CONSTRAINT member_role_fk1
                            FOREIGN KEY (role_id) REFERENCES role(id),
                        CONSTRAINT member_oauth2id_unique
                            UNIQUE (oauth2id)
);

-- sprint 테이블 생성
CREATE TABLE sprint (
                        id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        member_id INTEGER NOT NULL,
                        spot VARCHAR(255) NULL,
                        start_at datetime(6) NULL,
                        end_at datetime(6) NULL,
                        created_date datetime(6) NULL,
                        modified_date datetime(6) NULL,
                        CONSTRAINT sprint_member_fk1
                            FOREIGN KEY (member_id) REFERENCES member(id)
);

-- route 테이블 생성
CREATE TABLE route (
                       id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       sprint_id INTEGER NOT NULL,
                       try_count INTEGER NOT NULL,
                       is_solved BOOLEAN NOT NULL,
                       file_path VARCHAR(255) NULL,
                       color INTEGER NULL,
                       created_date datetime(6) NULL,
                       modified_date datetime(6) NULL,
                       CONSTRAINT route_sprint_fk1
                           FOREIGN KEY (sprint_id) REFERENCES sprint(id)
);

-- 인덱스
CREATE INDEX idx_member_id ON sprint(member_id);
CREATE INDEX idx_sprint_id ON route(sprint_id);