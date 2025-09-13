-- Project Name : Diet
-- Date/Time    : 2025/09/13 20:36:40
-- Author       : S.Kono
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  << 注意！！ >>
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
*/

-- dietスキーマ作成
create schema diet;

-- ユーザーテーブル
-- * RestoreFromTempTable
create table diet.t_mealt_user (
  meal_id INT not null
  , register_by CHAR(36)
  , register_date DATE
  , meal_type INT
  , calorie FLOAT
  , comment VARCHAR(100)
  , created_by CHAR(36) not null
  , created_at DATETIME not null
  , updated_by CHAR(36) not null
  , updated_at DATETIME not null
  , deleted INT not null
  , constraint t_mealt_user_PKC primary key (meal_id)
) ;

-- 食事画像テーブル
-- * RestoreFromTempTable
create table diet.t_mealt_meal_image (
  meal_image_id INT not null
  , meal_id INT not null
  , created_by CHAR(36) not null
  , created_at DATETIME not null
  , updated_by CHAR(36) not null
  , updated_at DATETIME not null
  , deleted INT not null
  , constraint t_mealt_meal_image_PKC primary key (meal_image_id,meal_id)
) ;

-- 食事テーブル
-- * RestoreFromTempTable
create table diet.t_meal (
  meal_id INT not null
  , register_by CHAR(36) not null
  , register_date DATE
  , meal_type INT
  , calorie FLOAT
  , comment VARCHAR(100)
  , created_by CHAR(36) not null
  , created_at DATETIME not null
  , updated_by CHAR(36) not null
  , updated_at DATETIME not null
  , deleted INT not null
  , constraint t_meal_PKC primary key (meal_id)
) ;

alter table diet.t_mealt_meal_image
  add constraint t_mealt_meal_image_FK1 foreign key (meal_id) references diet.t_meal(meal_id);

comment on table diet.t_mealt_user is 'ユーザーテーブル';
comment on column diet.t_mealt_user.meal_id is '食事ID';
comment on column diet.t_mealt_user.register_by is '登録者';
comment on column diet.t_mealt_user.register_date is '登録日';
comment on column diet.t_mealt_user.meal_type is '食事種別';
comment on column diet.t_mealt_user.calorie is 'カロリー';
comment on column diet.t_mealt_user.comment is 'コメント';
comment on column diet.t_mealt_user.created_by is '作成者ID';
comment on column diet.t_mealt_user.created_at is '作成日時';
comment on column diet.t_mealt_user.updated_by is '更新者ID';
comment on column diet.t_mealt_user.updated_at is '更新日時';
comment on column diet.t_mealt_user.deleted is '削除フラグ';

comment on table diet.t_mealt_meal_image is '食事画像テーブル';
comment on column diet.t_mealt_meal_image.meal_image_id is '食事画像ID';
comment on column diet.t_mealt_meal_image.meal_id is '食事ID';
comment on column diet.t_mealt_meal_image.created_by is '作成者ID';
comment on column diet.t_mealt_meal_image.created_at is '作成日時';
comment on column diet.t_mealt_meal_image.updated_by is '更新者ID';
comment on column diet.t_mealt_meal_image.updated_at is '更新日時';
comment on column diet.t_mealt_meal_image.deleted is '削除フラグ';

comment on table diet.t_meal is '食事テーブル';
comment on column diet.t_meal.meal_id is '食事ID';
comment on column diet.t_meal.register_by is '登録者ID';
comment on column diet.t_meal.register_date is '登録日';
comment on column diet.t_meal.meal_type is '食事種別';
comment on column diet.t_meal.calorie is 'カロリー';
comment on column diet.t_meal.comment is 'コメント';
comment on column diet.t_meal.created_by is '作成者ID';
comment on column diet.t_meal.created_at is '作成日時';
comment on column diet.t_meal.updated_by is '更新者ID';
comment on column diet.t_meal.updated_at is '更新日時';
comment on column diet.t_meal.deleted is '削除フラグ';

