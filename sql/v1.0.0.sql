-- 계정 Table
CREATE TABLE account
(
    id         BIGINT DEFAULT nextval('seq_account_id') CONSTRAINT account_pk PRIMARY KEY,
    email      VARCHAR(255) NOT NULL CONSTRAINT account_uk UNIQUE,
    password   text         NOT NULL,
    last_sign_in_at   TIMESTAMP    NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

COMMENT ON TABLE account IS '계정';

COMMENT ON COLUMN account.id IS 'PK';

COMMENT ON COLUMN account.email IS '이메일';

COMMENT ON COLUMN account.password IS '패스워드';

CREATE SEQUENCE seq_account_id;
