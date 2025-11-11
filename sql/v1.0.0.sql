CREATE SEQUENCE seq_id;

-- 계정
CREATE TABLE account
(
    id         BIGINT DEFAULT nextval('seq_id') CONSTRAINT account_pk PRIMARY KEY,
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

COMMENT ON COLUMN account.last_sign_in_at IS '최근 로그인 일자';

-- 신체 계측 정보
CREATE TABLE body_metric
(
    id             BIGINT DEFAULT nextval('seq_id') CONSTRAINT body_metric_pk PRIMARY KEY,
    account_id     BIGINT        NOT NULL,
    height         NUMERIC(4, 1) NOT NULL,
    weight         NUMERIC(4, 1) NOT NULL,
    body_fat_rate  NUMERIC(3, 1) NOT NULL,
    activity_level VARCHAR(20)   NOT NULL,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
);

COMMENT ON TABLE body_metric IS '신체 계측';

COMMENT ON COLUMN body_metric.account_id IS '계정 ID';

COMMENT ON COLUMN body_metric.height IS '키 (cm)';

COMMENT ON COLUMN body_metric.weight IS '몸무게 (kg)';

COMMENT ON COLUMN body_metric.body_fat_rate IS '체지방률 (%)';

COMMENT ON COLUMN body_metric.activity_level IS '활동수준';

COMMENT ON COLUMN body_metric.created_at IS '생성일자';

COMMENT ON COLUMN body_metric.updated_at IS '수정일자';

-- 음식 섭취 기록
CREATE TABLE food_log
(
    id                   BIGINT DEFAULT nextval('seq_id') CONSTRAINT food_log_pk PRIMARY KEY,
    account_id           BIGINT        NOT NULL,
    food_name            VARCHAR(100)  NOT NULL,
    serving_size         INTEGER,
    kcal                 NUMERIC(7, 3) NOT NULL,
    carbohydrate         NUMERIC(7, 3) NOT NULL,
    sugar                NUMERIC(7, 3) NOT NULL,
    protein              NUMERIC(7, 3) NOT NULL,
    fat                  NUMERIC(7, 3) NOT NULL,
    saturated_fatty_acid NUMERIC(7, 3),
    trans_fatty_acid     NUMERIC(7, 3),
    cholesterol          NUMERIC(7, 3) NOT NULL,
    sodium               NUMERIC(7, 3) NOT NULL,
    dietary_fiber        NUMERIC(7, 3),
    meal_type            VARCHAR(20),
    created_at           TIMESTAMP
);

COMMENT ON TABLE food_log IS '음식 섭취 기록';

COMMENT ON COLUMN food_log.account_id IS '계정 ID';

COMMENT ON COLUMN food_log.food_name IS '음식 이름';

COMMENT ON COLUMN food_log.serving_size IS '영양성분함량기준량 (g)';

COMMENT ON COLUMN food_log.kcal IS '열량 (kcal)';

COMMENT ON COLUMN food_log.carbohydrate IS '탄수화물 (g)';

COMMENT ON COLUMN food_log.protein IS '단백질 (g)';

COMMENT ON COLUMN food_log.fat IS '지방 (g)';

COMMENT ON COLUMN food_log.saturatedFattyAcid IS '포화지방산 (g)';

COMMENT ON COLUMN food_log.transFattyAcid IS '트랜스지방산 (g)';

COMMENT ON COLUMN food_log.cholesterol IS '콜레스테롤 (mg)';

COMMENT ON COLUMN food_log.sodium IS '나트륨 (mg)';

COMMENT ON COLUMN food_log.dietaryFiber IS '식이섬유 (g)';

COMMENT ON COLUMN food_log.meal_type IS '식사유형';

COMMENT ON COLUMN food_log.created_at IS '생성일자';
