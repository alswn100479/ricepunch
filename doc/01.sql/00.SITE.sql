/** 접속로그 **/
CREATE TABLE ACCESS_HISTORY ( 
	ID							INT AUTO_INCREMENT PRIMARY KEY, 
	USERID						INT,
	IP							VARCHAR(16),
	STATUS						INT,
	BROWSER						VARCHAR(100),
	OPERATING_SYSTEM			VARCHAR(100),
	ACCESS_DATE					DATE DEFAULT NOW()
);

/** 로그인로그 **/
CREATE TABLE LOGIN_HISTORY ( 
	ID							INT AUTO_INCREMENT PRIMARY KEY, 
	USERID						INT,
	IP							VARCHAR(16),
	STATUS						INT,
	LOGIN_DATE					DATE DEFAULT NOW()
);

/** 로그인 **/
CREATE TABLE LOGIN (
	ID			INT PRIMARY KEY,
	LOGIN_DATE	DATE,
	AGE_RANGE	INT
);