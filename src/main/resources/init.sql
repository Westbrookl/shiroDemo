---权限表---
CREATE  TABLE permission(
  pid int(11) NOT NULL,
  pname VARCHAR(255) NOT NULL DEFAULT '',
  url VARCHAR(255) DEFAULT '',
  PRIMARY KEY(pid)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO permission VALUES('1','add','');
INSERT INTO permission VALUES ('2','delete','');
INSERT INTO permission VALUES('3','edit','');
INSERT INTO permission VALUES('4','query','');
----用户表----
CREATE TABLE user(
  uid int(10) NOT NULL,
  username VARCHAR(255) NOT NULL DEFAULT '',
  password VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY(uid)
)ENGINE = InooDB DEFAULT CHARSET = utf8;
INSERT INTO user VALUES('1','admin','123');
INSERT INTO user VALUES ('2','demo','123');
---角色表---
CREATE TABLE role(
  rid int(10) NOT  NULL DEFAULT '',
  rname VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY  KEY(rid)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO role VALUES ('1','admin');
INSERT INTO role VALUES ('2','customer');
----权限角色关联表--
CREATE TABLE permission_role(
  pid int(10) NOT NULL,
  rid int(10) NOT NULL,
  KEY idx_pid(pid),
  KEY idx_rid(rid)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO permission_role VALUES('1','1');
INSERT INTO permission_role VALUES('2','1');
INSERT INTO permission_role VALUES('3','1');
INSERT INTO permission_role VALUES('4','1');
INSERT INTO permission_role VALUES('1','2');
INSERT INTO permission_role VALUES('4','2');
---用户角色表---
CREATE TABLE user_role(
  uid int(10) NOT NULL,
  rid int(10) NOT NULL,
  KEY idx_uid(uid),
  KEY idx_rid(rid)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO user_role VALUES(1,1);
INSERT INTO user_role VALUES(2,2);