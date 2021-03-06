CREATE DATABASE IF NOT EXISTS unit-test CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS book;

CREATE TABLE book
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	code VARCHAR(30) NULL DEFAULT NULL COMMENT 'code',
	company_Id BIGINT(20) NULL DEFAULT NULL COMMENT 'companyId',
--	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS company;
CREATE TABLE company
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	company_Name VARCHAR(30) NULL DEFAULT NULL COMMENT 'companyName',
	company_Id BIGINT(20) NULL DEFAULT NULL COMMENT 'companyId'
	PRIMARY KEY (id)
);