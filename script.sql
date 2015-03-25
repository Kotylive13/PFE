#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
 
 
CREATE TABLE User(
        id_u            int (11) Auto_increment  NOT NULL ,
        avatar          longBlob,
        firstName       Varchar (32) NOT NULL ,
        lastName        Varchar (32) NOT NULL ,
        adress          Varchar (128) NOT NULL ,
        phone           Varchar (10) ,
        birthDate       Date ,
        inscriptAppDate Date ,
        password        Varchar (32) NOT NULL ,
        email           Varchar (255) NOT NULL ,
        status          Varchar (16) DEFAULT 'W' ,
        PRIMARY KEY (id_u) ,
        UNIQUE (email)
)ENGINE=InnoDB;

 
CREATE TABLE Hobby(
        nameH Varchar (32) NOT NULL ,
        PRIMARY KEY (nameH)
)ENGINE=InnoDB;
 
 
CREATE TABLE UserHobby(
        nameH Varchar (25) NOT NULL ,
        id_u  Int NOT NULL ,
        PRIMARY KEY (nameH ,id_u),
        CONSTRAINT FK_UserHobby_nameH FOREIGN KEY (nameH) REFERENCES Hobby(nameH),
        CONSTRAINT FK_UserHobby_id_u FOREIGN KEY (id_u) REFERENCES User(id_u)
)ENGINE=InnoDB;
 
 
CREATE TABLE AcademicPeriod(
        id_p        int (11) Auto_increment  NOT NULL ,
        start       Date NOT NULL ,
        end         Date NOT NULL ,
        name        Varchar (32) NOT NULL ,
        description Text NOT NULL ,
        id_u        Int NOT NULL ,
        PRIMARY KEY (id_p ,id_u),
        CONSTRAINT FK_AcademicPeriod_id_u FOREIGN KEY (id_u) REFERENCES User(id_u)
)ENGINE=InnoDB;
 
 
CREATE TABLE ProfessionalPeriod(
        id_p        int (11) Auto_increment  NOT NULL ,
        start       Date NOT NULL ,
        end         Date NOT NULL ,
        name        Varchar (32) NOT NULL ,
        description Text NOT NULL ,
        id_u        Int NOT NULL ,
        PRIMARY KEY (id_p ,id_u),
        CONSTRAINT FK_ProfessionalPeriod_id_u FOREIGN KEY (id_u) REFERENCES User(id_u)
)ENGINE=InnoDB;
 
CREATE TABLE Student(
        numStudent       Varchar (10) NOT NULL ,
        inscriptUnivDate Date ,
        promo            Varchar (128) NOT NULL ,
        id_u             Int NOT NULL ,
        PRIMARY KEY (id_u) ,
        UNIQUE (numStudent),
        CONSTRAINT FK_Student_id_u FOREIGN KEY (id_u) REFERENCES User(id_u)
)ENGINE=InnoDB;

 
CREATE TABLE Message(
        dateM   Datetime NOT NULL ,
        content Varchar (2048) NOT NULL ,
        author  Int NOT NULL ,
        PRIMARY KEY (dateM ,author),
        CONSTRAINT FK_Message_id_u FOREIGN KEY (author) REFERENCES User(id_u)
)ENGINE=InnoDB;
 
 
CREATE TABLE MessageReceiver(
        id_u        Int NOT NULL ,
        dateM       Datetime NOT NULL ,
        receiver    Int NOT NULL ,
    consult     Bool NOT NULL DEFAULT false,
        PRIMARY KEY (id_u ,dateM ,receiver),
        CONSTRAINT FK_MessageReceiver_id_u FOREIGN KEY (id_u) REFERENCES User(id_u),
        CONSTRAINT FK_MessageReceiver_dateM FOREIGN KEY (dateM) REFERENCES Message(dateM),
        CONSTRAINT FK_MessageReceiver_id_u_User FOREIGN KEY (receiver) REFERENCES User(id_u)
)ENGINE=InnoDB;

 
CREATE TABLE `Group`(
        nameG       Varchar (32) NOT NULL ,
        avatar      longBlob ,
        description Text ,
        PRIMARY KEY (nameG)
)ENGINE=InnoDB;
 
 
CREATE TABLE UserGroup(
        nameG Varchar (32) NOT NULL ,
        owner Int ,
        PRIMARY KEY (nameG),
        CONSTRAINT FK_UserGroup_nameG FOREIGN KEY (nameG) REFERENCES `Group`(nameG),
        CONSTRAINT FK_UserGroup_id_u FOREIGN KEY (owner) REFERENCES User(id_u)
)ENGINE=InnoDB;
 
 
CREATE TABLE MembershipGroup(
        id_u  Int NOT NULL ,
        nameG Varchar (32) NOT NULL ,
        PRIMARY KEY (id_u ,nameG),
        CONSTRAINT FK_MembershipGroup_id_u FOREIGN KEY (id_u) REFERENCES User(id_u),
        CONSTRAINT FK_MembershipGroup_nameG FOREIGN KEY (nameG) REFERENCES `Group`(nameG)
)ENGINE=InnoDB;


CREATE TABLE Category(
        nameC           Varchar (32) ,
        nameG           Varchar (32) ,
        PRIMARY KEY (nameC ,nameG),
        CONSTRAINT FK_Category_nameG FOREIGN KEY (nameG) REFERENCES `Group`(nameG)
)ENGINE=InnoDB;

CREATE TABLE Subcategory(
        nameS           Varchar (32) ,
        nameC           Varchar (32) ,
        nameG    	Varchar (32) ,
        PRIMARY KEY (nameS, nameC ,nameG),
        CONSTRAINT FK_Category_nameC_nameG FOREIGN KEY (nameC, nameG) REFERENCES Category(nameC, nameG)
)ENGINE=InnoDB;
 
CREATE TABLE Publication(
        id_pub      int (11) Auto_increment ,
        title       Varchar (32) NOT NULL ,
	dateP	    Datetime NOT NULL,
        content     Text NOT NULL ,
        author      Int NOT NULL ,
	nameS	    Varchar (32) NOT NULL ,
        nameC       Varchar (32) NOT NULL ,
        nameG       Varchar (32) NOT NULL ,
        PRIMARY KEY (id_pub),
        FOREIGN KEY (author) REFERENCES User(id_u),
	FOREIGN KEY (nameS, nameC, nameG) REFERENCES Subcategory(nameS, nameC, nameG)
)ENGINE=InnoDB;
 
 
CREATE TABLE PublicationFile(
        id_pf       int (11) Auto_increment  NOT NULL ,
        title       Varchar (32) NOT NULL ,
        file        longBlob NOT NULL ,
        id_pub      Int NOT NULL  ,
        PRIMARY KEY (id_pf ,id_pub),
        CONSTRAINT FK_PublicationFile_id_pub FOREIGN KEY (id_pub) REFERENCES Publication(id_pub)
)ENGINE=InnoDB;
 
 
CREATE TABLE Opinion(
        id_op     int (11) Auto_increment  NOT NULL ,
        value     Varchar (32) ,
        author    Int NOT NULL ,
        id_pub    Int NOT NULL ,
        PRIMARY KEY (id_op ,id_pub),
        CONSTRAINT FK_Opinion_id_u_op FOREIGN KEY (author) REFERENCES User(id_u),
        CONSTRAINT FK_Opinion_id_pub FOREIGN KEY (id_pub) REFERENCES Publication(id_pub)
)ENGINE=InnoDB;
 
 
CREATE TABLE Comment(
        id_com    int (11) Auto_increment  NOT NULL ,
	dateC	  Datetime NOT NULL ,
        content   Text NOT NULL ,
        author    Int NOT NULL ,
        id_pub    Int NOT NULL ,
        PRIMARY KEY (id_com ,id_pub),
        CONSTRAINT FK_Comment_id_u_com FOREIGN KEY (author) REFERENCES User(id_u),
        CONSTRAINT FK_Comment_id_pub FOREIGN KEY (id_pub) REFERENCES Publication(id_pub)
)ENGINE=InnoDB;
 
 
CREATE TABLE CommentFile(
        id_cf     int (11) Auto_increment  NOT NULL ,
        title     Varchar (32) NOT NULL ,
        file      longBlob NOT NULL ,
        id_com    Int NOT NULL ,
        id_pub    Int NOT NULL ,
        PRIMARY KEY (id_cf ,id_com ,id_pub),
        CONSTRAINT FK_CommentFile_id_com FOREIGN KEY (id_com) REFERENCES Comment(id_com),
        CONSTRAINT FK_CommentFile_id_pub FOREIGN KEY (id_pub) REFERENCES Publication(id_pub)
)ENGINE=InnoDB;
 
 
CREATE TABLE Admin(
        id_a     int (11) Auto_increment  NOT NULL ,
        login    Varchar (32) NOT NULL ,
        password Varchar (32) NOT NULL ,
        role     Varchar (32) NOT NULL ,
        PRIMARY KEY (id_a) ,
        UNIQUE (login)
)ENGINE=InnoDB;

SET GLOBAL max_allowed_packet=5242880;