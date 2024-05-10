--USE Master
--DROP DATABASE hospital_db

--CREATE DATABASE hospital_db


USE hospital_db

CREATE TABLE Roles (
	RoleID VARCHAR(50) PRIMARY KEY NOT NULL,
	Title NVARCHAR(50) NOT NULL,
	isProvider BIT NOT NULL
);

CREATE TABLE StatusLookupTable (
	StatusID VARCHAR(50) PRIMARY KEY NOT NULL,
	StatusName NVARCHAR(10) NOT NULL
)

CREATE TABLE Departments (
	DeptID VARCHAR(50) PRIMARY KEY NOT NULL,
	DeptName NVARCHAR(50) NOT NULL,
);

CREATE TABLE Rooms (
	RoomID VARCHAR(50) PRIMARY KEY NOT NULL,
	RoomName NVARCHAR(50) NOT NULL,
	RoomStatus VARCHAR(50) NOT NULL,
	Department VARCHAR(50) NOT NULL,
	CONSTRAINT FK_Department_1 FOREIGN KEY (Department) REFERENCES Departments(DeptID),
	CONSTRAINT FK_Status_1 FOREIGN KEY (RoomStatus) REFERENCES StatusLookupTable(StatusID)
)

CREATE TABLE Addresses (
	AddressID VARCHAR(50) PRIMARY KEY NOT NULL,
	Line1 NVARCHAR(150) NOT NULL,
	Line2 NVARCHAR(150),
	City NVARCHAR(150) NOT NULL,
	Region NVARCHAR(150) NOT NULL,
	PostalCode VARCHAR(25) NOT NULL,
	Country NVARCHAR(150) NOT NULL
)

CREATE TABLE InsuranceAgency (
	AgencyID VARCHAR(50) PRIMARY KEY NOT NULL,
	AgencyName NVARCHAR(25) NOT NULL
)

CREATE TABLE InsurancePlan (
	PlanID VARCHAR(50) PRIMARY KEY NOT NULL,
	Title NVARCHAR(50) NOT NULL,
	PlanDescription NVARCHAR(MAX) NOT NULL,
	Deductible MONEY NOT NULL,
	PostDeductibleSplit DECIMAL(3,2) NOT NULL,
	InsuranceAgency VARCHAR(50) NOT NULL,
	CONSTRAINT FK_InsuranceAgency_1 FOREIGN KEY (InsuranceAgency) REFERENCES InsuranceAgency(AgencyID)
)

CREATE TABLE PaymentPlans (
	PlanID VARCHAR(50) PRIMARY KEY NOT NULL,
	Title NVARCHAR(50) NOT NULL,
	PaymentPeriodDays int NOT NULL,
	MonthlyMinimum MONEY NOT NULL,
	PaidPrinciple MONEY NOT NULL,
	MonthlyInterest DECIMAL(3,2) NOT NULL,
)

CREATE TABLE Staff (
    StaffID VARCHAR(50) PRIMARY KEY NOT NULL,
    fullName NVARCHAR(150) NOT NULL,
    StaffRole VARCHAR(50) NOT NULL,
    Department VARCHAR(50) NOT NULL,
	CONSTRAINT FK_StaffRole FOREIGN KEY (StaffRole) REFERENCES Roles(RoleID),
	CONSTRAINT FK_Department_2 FOREIGN KEY (Department) REFERENCES Departments(DeptID)
);

CREATE TABLE Patients (
	PatientID VARCHAR(50) PRIMARY KEY NOT NULL,
	FullName NVARCHAR(150) NOT NULL,
	HomeAddress VARCHAR(50),
	DateOfBirth DATE NOT NULL,
	SSN VARCHAR(11),
	InsuranceMembershipID VARCHAR(50) UNIQUE,
	DeductibleProgress MONEY,
	PatientStatus VARCHAR(50) NOT NULL,
	CONSTRAINT FK_PatientAddress FOREIGN KEY (HomeAddress) REFERENCES Addresses(AddressID),
	CONSTRAINT FK_PatientStatus FOREIGN KEY (PatientStatus) REFERENCES StatusLookupTable(StatusID)
)

CREATE TABLE ProcedureHistory (
	ProcedureID VARCHAR(50) PRIMARY KEY NOT NULL,
	ProcedureDescription NVARCHAR(MAX) NOT NULL,
	Code VARCHAR(256) NOT NULL UNIQUE,
	Fee MONEY NOT NULL,
	Department VARCHAR(50) NOT NULL,
	CONSTRAINT FK_Department_3 FOREIGN KEY (Department) REFERENCES Departments(DeptID)
)

CREATE TABLE ProcedureRoleRequirements (
	RequirementID VARCHAR(50) PRIMARY KEY NOT NULL,
	RoleCode VARCHAR(50) NOT NULL,
	ProcedureCode VARCHAR(50) NOT NULL,
	RequiredRole VARCHAR(50) NOT NULL,
	RequiredDescription NVARCHAR(MAX) NOT NULL,
	IsOptional BIT NOT NULL,
	Quantity INT NOT NULL,
	Cost MONEY NOT NULL,
	CONSTRAINT FK_RoleReqProcedureCode FOREIGN KEY (ProcedureCode) REFERENCES ProcedureHistory(ProcedureCode),
	CONSTRAINT FK_RoleReqRoleCode FOREIGN KEY (RoleCode) REFERENCES Roles(RoleID),
)

CREATE TABLE ProcedureSupplyRequirements (
	SupplyID VARCHAR(50) PRIMARY KEY NOT NULL,
	SupplyCode VARCHAR(7) NOT NULL,
	ProcedureCode VARCHAR(50) NOT NULL,
	Item VARCHAR(MAX) NOT NULL,
	ItemDescription NVARCHAR(MAX) NOT NULL,
	Quantity INT NOT NULL,
	QuantityUnit VARCHAR(50) NOT NULL,
	UnitPrice MONEY NOT NULL,
	CONSTRAINT FK_SupplyReqProcedureCode FOREIGN KEY (ProcedureCode) REFERENCES ProcedureHistory(ProcedureID)
)

CREATE TABLE Appointment (
	AppointmentID VARCHAR(50) PRIMARY KEY NOT NULL,
	AppointmentDate DATETIME NOT NULL,
	StartTime TIME,
	EndTime TIME,
	PatientID VARCHAR(50) NOT NULL,
	ProviderID VARCHAR(50) NOT NULL,
	RoomID VARCHAR(50) NOT NULL,
	ProcedureCode VARCHAR(256) NOT NULL,
	Fee MONEY NOT NULL,
	CONSTRAINT FK_Patient_1 FOREIGN KEY (PatientID) REFERENCES Patients(PatientID), 
	CONSTRAINT FK_Provider FOREIGN KEY (ProviderID) REFERENCES Staff(StaffID),
	CONSTRAINT FK_Room FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID),
	CONSTRAINT FK_ProcedureCode FOREIGN KEY (ProcedureCode) REFERENCES ProcedureHistory(Code)
)

CREATE TABLE Claims (
	ClaimID VARCHAR(50) PRIMARY KEY NOT NULL,
	FileDate DATE NOT NULL,
	MembershipID VARCHAR(50) NOT NULL,
	PatientID VARCHAR(50) NOT NULL,
	AppointmentID VARCHAR(50) NOT NULL,
	PaymentPlanID VARCHAR(50) NOT NULL,
	PaidDeductible MONEY NOT NULL,
	PatientCopay MONEY NOT NULL,
	InsurancePay MONEY NOT NULL,
	ClaimStatus VARCHAR(50) NOT NULL,
	Adjustments MONEY NOT NULL,
	CONSTRAINT FK_Membership_1 FOREIGN KEY (MembershipID) REFERENCES Patients(InsuranceMembershipID),
	CONSTRAINT FK_Patient_2 FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
	CONSTRAINT FK_Appointment_1 FOREIGN KEY (AppointmentID) REFERENCES Appointment(AppointmentID),
	CONSTRAINT FK_PaymentPlan_1 FOREIGN KEY (PaymentPlanID) REFERENCES PaymentPlans(PlanID),
	CONSTRAINT FK_Status_3 FOREIGN KEY (ClaimStatus) REFERENCES StatusLookupTable(StatusID)
)

CREATE TABLE Transactions (
	TransactionID VARCHAR(50) PRIMARY KEY NOT NULL,
	TransactionDate DATE NOT NULL,
	PatientInsurancePlan VARCHAR(50),
	IsSourceInsurance BIT NOT NULL,
	PaymentPlanID VARCHAR(50) NOT NULL,
	AppointmentID VARCHAR(50) NOT NULL,
	PatientID VARCHAR(50) NOT NULL,
	Total MONEY NOT NULL,
	RemainingBalance MONEY NOT NULL,
	TransactionStatus VARCHAR(50) NOT NULL,
	InNetwork BIT NOT NULL,
	CONSTRAINT FK_PaymentPlan_2 FOREIGN KEY (PaymentPlanID) REFERENCES PaymentPlans(PlanID),
	CONSTRAINT FK_Appointment_2 FOREIGN KEY (AppointmentID) REFERENCES Appointment(AppointmentID),
	CONSTRAINT FK_Patient_3 FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
	CONSTRAINT FK_Status_2 FOREIGN KEY (TransactionStatus) REFERENCES StatusLookupTable(StatusID),
	CONSTRAINT FK_PatientInsurancePlan FOREIGN KEY (PatientInsurancePlan) REFERENCES InsurancePlan(PlanID)
)

CREATE TABLE PlanMembershipJoin (
	MembershipID VARCHAR(50) NOT NULL,
	PlanID VARCHAR(50) NOT NULL,
	CONSTRAINT FK_Membership_2 FOREIGN KEY (MembershipID) REFERENCES Patients(InsuranceMembershipID),
	CONSTRAINT FK_Plan FOREIGN KEY (PlanID) REFERENCES InsurancePlan(PlanID),
	CONSTRAINT PK_PlanMembershipJoin PRIMARY KEY (MembershipID, PlanID)
)