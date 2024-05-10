CREATE DATABASE hospital_db;

USE hospital_db;

CREATE TABLE Roles (
    RoleID VARCHAR(50) PRIMARY KEY NOT NULL,
    Title VARCHAR(50) NOT NULL,
    isProvider TINYINT(1) NOT NULL
);

CREATE TABLE StatusLookupTable (
    StatusID VARCHAR(50) PRIMARY KEY NOT NULL,
    StatusName VARCHAR(10) NOT NULL
);

CREATE TABLE Departments (
    DeptID VARCHAR(50) PRIMARY KEY NOT NULL,
    DeptName VARCHAR(50) NOT NULL
);

CREATE TABLE Rooms (
    RoomID VARCHAR(50) PRIMARY KEY NOT NULL,
    RoomName VARCHAR(50) NOT NULL,
    RoomStatus VARCHAR(50) NOT NULL,
    Department VARCHAR(50) NOT NULL,
    FOREIGN KEY (Department) REFERENCES Departments(DeptID),
    FOREIGN KEY (RoomStatus) REFERENCES StatusLookupTable(StatusID)
);

CREATE TABLE Addresses (
    AddressID VARCHAR(50) PRIMARY KEY NOT NULL,
    Label VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(50),
    Line1 VARCHAR(150) NOT NULL,
    Line2 VARCHAR(150),
    City VARCHAR(150) NOT NULL,
    Region VARCHAR(150) NOT NULL,
    PostalCode VARCHAR(25) NOT NULL,
    Country VARCHAR(150) NOT NULL
);

CREATE TABLE InsuranceAgency (
    AgencyID VARCHAR(50) PRIMARY KEY NOT NULL,
    AgencyName VARCHAR(25) NOT NULL
);

CREATE TABLE InsurancePlan (
    PlanID VARCHAR(50) PRIMARY KEY NOT NULL,
    Title VARCHAR(50) NOT NULL,
    PlanDescription TEXT NOT NULL,
    Deductible DECIMAL(10, 2) NOT NULL,
    PostDeductibleSplit DECIMAL(3, 2) NOT NULL,
    InsuranceAgency VARCHAR(50) NOT NULL,
    FOREIGN KEY (InsuranceAgency) REFERENCES InsuranceAgency(AgencyID)
);

CREATE TABLE PaymentPlans (
    PlanID VARCHAR(50) PRIMARY KEY NOT NULL,
    Title VARCHAR(50) NOT NULL,
    PaymentPeriodDays INT NOT NULL,
    MonthlyMinimum DECIMAL(10, 2) NOT NULL,
    PaidPrinciple DECIMAL(10, 2) NOT NULL,
    MonthlyInterest DECIMAL(3, 2) NOT NULL
);

CREATE TABLE PayTypes (
    PayTypeID VARCHAR(50) PRIMARY KEY NOT NULL,
    PayType VARCHAR(50) NOT NULL,
    AssociatedTaxDocument VARCHAR(50) NOT NULL
);

CREATE TABLE Staff (
    StaffID VARCHAR(50) PRIMARY KEY NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    StaffRole VARCHAR(50) NOT NULL,
    Department VARCHAR(50) NOT NULL,
    PayType VARCHAR(50) NOT NULL,
    Salary DECIMAL(10, 2),
    SSN VARCHAR(11) NOT NULL,
    HomeAddress VARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    TaxFilingStatus VARCHAR(50) NOT NULL,
    HasOtherJob TINYINT(1) NOT NULL,
    OtherJobIncome DECIMAL(10, 2),
    DependantTaxAdjustments DECIMAL(10, 2) NOT NULL,
    TaxDeductions DECIMAL(10, 2) NOT NULL,
    ExtraTaxWithholding DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (StaffRole) REFERENCES Roles(RoleID),
    FOREIGN KEY (Department) REFERENCES Departments(DeptID),
    FOREIGN KEY (PayType) REFERENCES PayTypes(PayTypeID),
    FOREIGN KEY (HomeAddress) REFERENCES Addresses(AddressID),
    FOREIGN KEY (TaxFilingStatus) REFERENCES StatusLookupTable(StatusID)
);

CREATE TABLE Patients (
    PatientID VARCHAR(50) PRIMARY KEY NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    HomeAddress VARCHAR(50),
    DateOfBirth DATE NOT NULL,
    SSN VARCHAR(11),
    InsuranceMembershipID VARCHAR(50) UNIQUE,
    DeductibleProgress DECIMAL(10, 2),
    PatientStatus VARCHAR(50) NOT NULL,
    FOREIGN KEY (HomeAddress) REFERENCES Addresses(AddressID),
    FOREIGN KEY (PatientStatus) REFERENCES StatusLookupTable(StatusID)
);

CREATE TABLE SupplyInventory (
    SupplyID VARCHAR(50) PRIMARY KEY NOT NULL,
    Item VARCHAR(50) NOT NULL,
    Description TEXT NOT NULL,
    Quantity INT NOT NULL,
    QuantityUnit VARCHAR(50) NOT NULL,
    UnitPrice DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Procedures (
    ProcedureID VARCHAR(50) PRIMARY KEY NOT NULL,
    ProcedureDescription TEXT NOT NULL,
    Code VARCHAR(256) NOT NULL UNIQUE,
    Fee DECIMAL(10, 2) NOT NULL,
    Department VARCHAR(50) NOT NULL,
    FOREIGN KEY (Department) REFERENCES Departments(DeptID)
);

CREATE TABLE WorkOrders (
    OrderID VARCHAR(50) PRIMARY KEY NOT NULL,
    OrderDate DATE NOT NULL,
    SupplyCode VARCHAR(50) NOT NULL,
    Status VARCHAR(50) NOT NULL,
    Description TEXT NOT NULL,
    ServiceCompanyName VARCHAR(50) NOT NULL,
    ServiceTechName VARCHAR(50) NOT NULL,
    ServiceCompanyContact VARCHAR(50) NOT NULL,
    LastServiceDate DATE NOT NULL,
    FOREIGN KEY (supplyCode) REFERENCES SupplyInventory(supplyID),
    FOREIGN KEY (status) REFERENCES StatusLookupTable(StatusID),
    FOREIGN KEY (serviceCompanyContact) REFERENCES Addresses(AddressID)
);

CREATE TABLE PurchaseOrders (
    OrderID VARCHAR(50) PRIMARY KEY NOT NULL,
    OrderDate DATE NOT NULL,
    SupplyCode VARCHAR(50) NOT NULL,
    Status VARCHAR(50) NOT NULL,
    Quantity INT NOT NULL,
    UnitPrice DECIMAL(10, 2) NOT NULL,
    TotalPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (supplyCode) REFERENCES SupplyInventory(supplyID),
    FOREIGN KEY (status) REFERENCES StatusLookupTable(StatusID)
);

CREATE TABLE ProcedureRoleRequirements (
    RequirementID VARCHAR(50) PRIMARY KEY NOT NULL,
    RoleCode VARCHAR(50) NOT NULL,
    ProcedureCode VARCHAR(50) NOT NULL,
    Description TEXT NOT NULL,
    Quantity INT NOT NULL,
    Cost DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ProcedureCode) REFERENCES Procedures(ProcedureID),
    FOREIGN KEY (RoleCode) REFERENCES Roles(RoleID)
);

CREATE TABLE ProcedureSupplyRequirements (
    RequirementID VARCHAR(50) PRIMARY KEY NOT NULL,
    SupplyCode VARCHAR(50) NOT NULL,
    ProcedureCode VARCHAR(50) NOT NULL,
    Quantity INT NOT NULL,
    UnitPrice DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ProcedureCode) REFERENCES Procedures(ProcedureID),
    FOREIGN KEY (SupplyCode) REFERENCES SupplyInventory(supplyID)
);

CREATE TABLE Appointment (
    AppointmentID VARCHAR(50) PRIMARY KEY NOT NULL,
    AppointmentDate DATETIME NOT NULL,
    StartTime TIME,
    EndTime TIME,
    PatientID VARCHAR(50) NOT NULL,
    ProviderID VARCHAR(50) NOT NULL,
    RoomID VARCHAR(50) NOT NULL,
    ProcedureCode VARCHAR(50) NOT NULL,
    Fee DECIMAL(10, 2) NOT NULL,
    Notes TEXT,
    ZippedFiles BLOB,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (ProviderID) REFERENCES Staff(StaffID),
    FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID),
    FOREIGN KEY (ProcedureCode) REFERENCES Procedures(ProcedureID)
);

CREATE TABLE Claims (
    ClaimID VARCHAR(50) PRIMARY KEY NOT NULL,
    FileDate DATE NOT NULL,
    MembershipID VARCHAR(50) NOT NULL,
    PatientID VARCHAR(50) NOT NULL,
    AppointmentID VARCHAR(50) NOT NULL,
    PaymentPlanID VARCHAR(50) NOT NULL,
    PaidDeductible DECIMAL(10, 2) NOT NULL,
    PatientCopay DECIMAL(10, 2) NOT NULL,
    InsurancePay DECIMAL(10, 2) NOT NULL,
    ClaimStatus VARCHAR(50) NOT NULL,
    Adjustments DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (MembershipID) REFERENCES Patients(InsuranceMembershipID),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (AppointmentID) REFERENCES Appointment(AppointmentID),
    FOREIGN KEY (PaymentPlanID) REFERENCES PaymentPlans(PlanID),
    FOREIGN KEY (ClaimStatus) REFERENCES StatusLookupTable(StatusID)
);

CREATE TABLE Transactions (
    TransactionID VARCHAR(50) PRIMARY KEY NOT NULL,
    TransactionDate DATE NOT NULL,
    PatientInsurancePlan VARCHAR(50),
    IsSourceInsurance TINYINT(1) NOT NULL,
    PaymentPlanID VARCHAR(50) NOT NULL,
    AppointmentID VARCHAR(50) NOT NULL,
    PatientID VARCHAR(50) NOT NULL,
    Total DECIMAL(10, 2) NOT NULL,
    RemainingBalance DECIMAL(10, 2) NOT NULL,
    TransactionStatus VARCHAR(50) NOT NULL,
    InNetwork TINYINT(1) NOT NULL,
    FOREIGN KEY (PaymentPlanID) REFERENCES PaymentPlans(PlanID),
    FOREIGN KEY (AppointmentID) REFERENCES Appointment(AppointmentID),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (TransactionStatus) REFERENCES StatusLookupTable(StatusID),
    FOREIGN KEY (PatientInsurancePlan) REFERENCES InsurancePlan(PlanID)
);

CREATE TABLE PlanMembershipJoin (
    MembershipID VARCHAR(50) NOT NULL,
    PlanID VARCHAR(50) NOT NULL,
    PRIMARY KEY (MembershipID, PlanID),
    FOREIGN KEY (MembershipID) REFERENCES Patients(InsuranceMembershipID),
    FOREIGN KEY (PlanID) REFERENCES InsurancePlan(PlanID)
);

CREATE TABLE Payroll (
    PaymentID VARCHAR(50) PRIMARY KEY NOT NULL,
    IssueDate DATE NOT NULL,
    StaffID VARCHAR(50) NOT NULL,
    PayType VARCHAR(50) NOT NULL,
    StaffTIN VARCHAR(11) NOT NULL,
    GrossWages DECIMAL(10, 2) NOT NULL,
    SocialTaxableWages DECIMAL(10, 2) NOT NULL,
    SocialTax DECIMAL(10, 2) NOT NULL,
    MedicareTaxableWages DECIMAL(10, 2) NOT NULL,
    MedicareTax DECIMAL(10, 2) NOT NULL,
    LocalTaxableWages DECIMAL(10, 2) NOT NULL,
    LocalTax DECIMAL(10, 2) NOT NULL,
    StateTaxableWages DECIMAL(10, 2) NOT NULL,
    StateTax DECIMAL(10, 2) NOT NULL,
    FederalTax DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (staffID) REFERENCES Staff(StaffID),
    FOREIGN KEY (payType) REFERENCES PayTypes(PayTypeID)
)