CREATE TABLE player(
	id 					INT PRIMARY KEY AUTO_INCREMENT,
    discid		        VARCHAR(100),
    username	    VARCHAR(50),
    money				INT,
    lvl						INT,
    exp					INT,
    percent			DOUBLE,
    floor					INT,
    map					INT,
    att					INT,
    def					INT,
    hp					INT,
    maxhp				INT,
    fa1					BOOL,
    fa2					BOOL,
    fa3					BOOL,
    fa4					BOOL,
    fa5					BOOL
);

CREATE TABLE floor(
	id 					INT PRIMARY KEY AUTO_INCREMENT,
    name				VARCHAR(50),
    requirement		INT,
    bossname		VARCHAR(50),
    maps				INT
);

CREATE TABLE map(
	id 					INT PRIMARY KEY AUTO_INCREMENT,
   name				VARCHAR(50),
   floor					INT,
   number				INT,
    FOREIGN KEY(floor) REFERENCES floor(id)
);

CREATE TABLE monster(
	id 					INT PRIMARY KEY AUTO_INCREMENT,
    name				VARCHAR(100),
	lvl						INT,
    att					INT,
    def					INT,
    floor					INT,
    map					INT,
    hp					INT,
    isboss				BOOL,
    coins				INT,
    exp					INT,
	FOREIGN KEY(floor) REFERENCES floor(id),
    FOREIGN KEY(map) REFERENCES map(id)
);

CREATE TABLE item(
		id 					INT PRIMARY KEY AUTO_INCREMENT,
		name				VARCHAR(100),
		lvl						INT,
		att					INT,
		def					INT,
        type					INT,
        worth				INT,
        classification	VARCHAR(50),
        hp			        INT,
        rate					DOUBLE
);

CREATE TABLE equip(
		pid					INT,
        iid					INT,
		FOREIGN KEY(pid) REFERENCES player(id)  ON DELETE CASCADE,
		FOREIGN KEY(iid) REFERENCES item(id)
);

CREATE TABLE droplist(
		mid					INT,
        iid					INT,
        FOREIGN KEY(mid) REFERENCES monster(id),
		FOREIGN KEY(iid) REFERENCES item(id)
);

CREATE TABLE inventory(
		pid					INT,
        iid					INT,
        FOREIGN KEY(pid) REFERENCES player(id)  ON DELETE CASCADE,
		FOREIGN KEY(iid) REFERENCES item(id)
);

CREATE TABLE levels(
		lvl					INT,
        exp				int
);

CREATE TABLE shop(
	fid					INT,
    iid					INT,
    FOREIGN KEY(fid) REFERENCES floor(id),
    FOREIGN KEY(iid) REFERENCES item(id)
);
    


