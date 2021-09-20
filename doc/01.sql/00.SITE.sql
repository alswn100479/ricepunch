/** 접속로그 **/
CREATE TABLE ACCESS_HISTORY ( 
	ID					INT AUTO_INCREMENT PRIMARY KEY		COMMENT 'ID', 
	USERID				INT									COMMENT '접속자ID',
	IP					VARCHAR(16)							COMMENT '접속자IP',
	STATUS				INT									COMMENT '상태',
	BROWSER				VARCHAR(100)						COMMENT '접속 브라우저',
	OPERATING_SYSTEM	VARCHAR(100)						COMMENT '접속 기기',
	DEVICE				VARCHAR(16)							COMMENT 'device',
	ACCESS_DATE			DATE DEFAULT NOW()					COMMENT '접속일시'
);

/** 로그인로그 **/
CREATE TABLE LOGIN_HISTORY ( 
	ID							INT AUTO_INCREMENT PRIMARY KEY 	COMMENT 'ID', 
	USERID						INT								COMMENT '사용자ID',
	IP							VARCHAR(16)						COMMENT '접속IP',
	STATUS						INT								COMMENT '상태',
	FIRST_LOGIN_DATE			DATE DEFAULT NOW()				COMMENT '최초로그인일시'
);

/** 로그인 **/
CREATE TABLE USER (
	ID					INT PRIMARY KEY	COMMENT 'ID',
	FIRST_LOGIN_DATE	DATE			COMMENT '로그인일시',
	AGE_RANGE			INT				COMMENT '연령대',
	NAME				VARCHAR(100)	COMMENT '이름',
	USER_DESC			VARCHAR(1000)	COMMENT '설명'
);