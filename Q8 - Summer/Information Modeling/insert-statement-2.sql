INSERT INTO Roles (RoleID, Title, isProvider)
VALUES
    ('R001', 'Doctor', 1),
    ('R002', 'Nurse', 0),
    ('R003', 'Administrator', 0),
    ('R004', 'Pharmacist', 1),
    ('R005', 'Medical Assistant', 0),
    ('R006', 'Radiology Technician', 1),
    ('R007', 'Physical Therapist', 1),
    ('R008', 'Social Worker', 0),
    ('R009', 'Lab Technician', 1),
    ('R010', 'Respiratory Therapist', 1),
    ('R011', 'Surgeon', 1),
    ('R012', 'Anesthesiologist', 1),
    ('R013', 'Psychologist', 1),
    ('R014', 'Occupational Therapist', 1),
    ('R015', 'Dietitian', 1),
    ('R016', 'Patient Care Technician', 0),
    ('R017', 'Speech Therapist', 1),
    ('R018', 'Health Information Technician', 0),
    ('R019', 'Medical Coder', 0),
    ('R020', 'Chaplain', 0),
    ('R021', 'Emergency Medical Technician (EMT)', 1),
    ('R022', 'Surgical Technologist', 1),
    ('R023', 'Optometrist', 1),
    ('R024', 'Medical Interpreter', 0),
    ('R025', 'Case Manager', 0),
    ('R026', 'Home Health Aide', 0),
    ('R027', 'Phlebotomist', 1),
    ('R028', 'Infection Control Specialist', 0),
    ('R029', 'Genetic Counselor', 1),
    ('R030', 'Bioinformatics Specialist', 0);

INSERT INTO StatusLookupTable (StatusID, StatusName)
VALUES
    ('S001', 'Active'),
    ('S002', 'Inactive'),
    ('S003', 'Pending'),
    ('S004', 'Processed'),
    ('S005', 'Denied');

INSERT INTO Departments (DeptID, DeptName)
VALUES
    ('D001', 'Cardiology'),
    ('D002', 'Orthopedics'),
    ('D003', 'Pediatrics'),
    ('D004', 'Internal Medicine'),
    ('D005', 'Neurology'),
    ('D006', 'Oncology'),
    ('D007', 'Dermatology'),
    ('D008', 'Radiology'),
    ('D009', 'Gastroenterology'),
    ('D010', 'Obstetrics and Gynecology'),
    ('D011', 'Emergency Medicine'),
    ('D012', 'Anesthesiology'),
    ('D013', 'Psychiatry'),
    ('D014', 'Urology'),
    ('D015', 'Ophthalmology'),
    ('D016', 'Nephrology'),
    ('D017', 'Pulmonology'),
    ('D018', 'Endocrinology'),
    ('D019', 'Infectious Diseases'),
    ('D020', 'Hematology');

INSERT INTO Rooms (RoomID, RoomName, RoomStatus, Department)
VALUES
    ('R001', 'Cardiac Exam Room', 'S001', 'D001'),
    ('R002', 'Ortho X-ray Room', 'S002', 'D002'),
    ('R003', 'Pediatric Clinic', 'S001', 'D003'),
    ('R004', 'ICU Room 1', 'S003', 'D001'),
    ('R005', 'Operating Theater 1', 'S004', 'D002'),
    ('R006', 'Neonatal Intensive Care', 'S003', 'D003'),
    ('R007', 'Laboratory Room A', 'S005', 'D001'),
    ('R008', 'Physical Therapy Room', 'S002', 'D002'),
    ('R009', 'Pediatric Isolation Room', 'S003', 'D003'),
    ('R010', 'Radiology Suite 1', 'S004', 'D001'),
    ('R011', 'Orthopedics Clinic', 'S002', 'D002'),
    ('R012', 'Child Play Area', 'S001', 'D003'),
    ('R013', 'Endoscopy Suite', 'S004', 'D001'),
    ('R014', 'Emergency Room 1', 'S005', 'D002'),
    ('R015', 'Pediatric Emergency Room', 'S003', 'D003'),
    ('R016', 'Ultrasound Room A', 'S004', 'D001'),
    ('R017', 'Rehabilitation Gym', 'S002', 'D002'),
    ('R018', 'Neonatal Monitoring Room', 'S001', 'D003'),
    ('R019', 'Radiation Therapy Room', 'S004', 'D001'),
    ('R020', 'Surgery Recovery Room', 'S003', 'D002'),
    ('R021', 'Child Psychiatry Room', 'S002', 'D003'),
    ('R022', 'MRI Suite', 'S004', 'D001'),
    ('R023', 'Ortho Exam Room', 'S005', 'D002'),
    ('R024', 'Pediatric Ward A', 'S001', 'D003'),
    ('R025', 'Laboratory Room B', 'S004', 'D001'),
    ('R026', 'Neurology Clinic', 'S002', 'D002'),
    ('R027', 'NICU Room 2', 'S003', 'D003'),
    ('R028', 'Radiology Suite 2', 'S004', 'D001'),
    ('R029', 'Emergency Room 2', 'S005', 'D002'),
    ('R030', 'Pediatric ICU', 'S003', 'D003'),
    ('R031', 'Neurology Exam Room', 'S001', 'D001'),
    ('R032', 'Ortho Surgery Room', 'S002', 'D002'),
    ('R033', 'Pediatric Consultation Room', 'S003', 'D003'),
    ('R034', 'Laboratory Room C', 'S004', 'D001'),
    ('R035', 'Radiation Oncology Suite', 'S005', 'D002'),
    ('R036', 'NICU Room 3', 'S001', 'D003'),
    ('R037', 'Endoscopy Suite B', 'S002', 'D001'),
    ('R038', 'Psychiatry Consultation Room', 'S003', 'D003'),
    ('R039', 'MRI Suite B', 'S004', 'D001'),
    ('R040', 'Orthopedics Clinic B', 'S005', 'D002'),
    ('R041', 'Pulmonology Exam Room', 'S001', 'D003'),
    ('R042', 'Operating Theater 2', 'S002', 'D002'),
    ('R043', 'Pediatric Ward B', 'S003', 'D003'),
    ('R044', 'Cardiovascular Imaging Room', 'S004', 'D001'),
    ('R045', 'Rehabilitation Room B', 'S005', 'D002'),
    ('R046', 'ICU Room 2', 'S001', 'D001'),
    ('R047', 'Ortho X-ray Room B', 'S002', 'D002'),
    ('R048', 'Emergency Room 3', 'S003', 'D003'),
    ('R049', 'Laboratory Room D', 'S004', 'D001'),
    ('R050', 'Radiology Suite C', 'S005', 'D002'),
    ('R051', 'Pediatric ICU B', 'S001', 'D003'),
    ('R052', 'Ultrasound Room B', 'S002', 'D001'),
    ('R053', 'Surgery Recovery Room B', 'S003', 'D002'),
    ('R054', 'Neonatal Monitoring Room B', 'S004', 'D003'),
    ('R055', 'Physical Therapy Room B', 'S005', 'D001'),
    ('R056', 'Emergency Room 4', 'S001', 'D002'),
    ('R057', 'Ortho Exam Room C', 'S002', 'D003'),
    ('R058', 'Neurology Clinic B', 'S003', 'D001'),
    ('R059', 'ICU Room 3', 'S004', 'D002'),
    ('R060', 'Pediatric Consultation Room B', 'S005', 'D003');

INSERT INTO Addresses (AddressID, Label, PhoneNumber, Line1, Line2, City, Region, PostalCode, Country)
VALUES
    ('A001', 'Home', '123-456-7890', '123 Main Street', NULL, 'New York', 'NY', '10001', 'USA'),
    ('A002', 'Office', '987-654-3210', '456 Elm Avenue', 'Suite 201', 'Los Angeles', 'CA', '90002', 'USA'),
    ('A003', 'Home', '555-123-4567', '789 Oak Lane', NULL, 'Chicago', 'IL', '60601', 'USA'),
    ('A004', 'Home', '888-555-1234', '321 Pine Street', NULL, 'Houston', 'TX', '77001', 'USA'),
    ('A005', 'Apartment', '555-678-4321', '987 Maple Drive', 'Apt 102', 'Miami', 'FL', '33130', 'USA'),
    ('A006', 'Home', '222-333-4444', '654 Cedar Road', NULL, 'San Francisco', 'CA', '94105', 'USA'),
    ('A007', 'Home', '777-888-9999', '890 Walnut Avenue', NULL, 'Phoenix', 'AZ', '85001', 'USA'),
    ('A008', 'Office', '333-444-5555', '1234 Birch Lane', 'Unit B', 'Philadelphia', 'PA', '19101', 'USA'),
    ('A009', 'Home', '999-888-7777', '567 Willow Street', NULL, 'Seattle', 'WA', '98101', 'USA'),
    ('A010', 'Home', '444-555-6666', '876 Oak Avenue', NULL, 'Boston', 'MA', '02101', 'USA'),
    ('A011', 'Office', '777-888-9999', '432 Elm Court', 'Suite 301', 'Atlanta', 'GA', '30301', 'USA'),
    ('A012', 'Home', '555-444-3333', '234 Pine Road', NULL, 'Dallas', 'TX', '75201', 'USA'),
    ('A013', 'Apartment', '111-222-3333', '876 Maple Lane', 'Apt 202', 'Denver', 'CO', '80201', 'USA');

INSERT INTO InsuranceAgency (AgencyID, AgencyName)
VALUES
    ('IA001', 'BlueCross'),
    ('IA002', 'Aetna'),
    ('IA003', 'Cigna'),
    ('IA004', 'UnitedHealthcare'),
    ('IA005', 'Humana'),
    ('IA006', 'Anthem'),
    ('IA007', 'Kaiser Permanente'),
    ('IA008', 'Molina Healthcare'),
    ('IA009', 'Centene Corporation'),
    ('IA010', 'Health Net'),
    ('IA011', 'Aflac'),
    ('IA012', 'MetLife'),
    ('IA013', 'Allstate'),
    ('IA014', 'GEICO'),
    ('IA015', 'Progressive'),
    ('IA016', 'Liberty Mutual'),
    ('IA017', 'Nationwide'),
    ('IA018', 'Travelers'),
    ('IA019', 'Farmers Insurance'),
    ('IA020', 'Prudential');

INSERT INTO InsurancePlan (PlanID, Title, PlanDescription, Deductible, PostDeductibleSplit, InsuranceAgency)
VALUES
    ('IP001', 'Standard Plan', 'Basic health coverage', 1000.00, 0.8, 'IA001'),
    ('IP002', 'Gold Plan', 'Comprehensive coverage', 500.00, 0.9, 'IA002'),
    ('IP003', 'Silver Plan', 'Mid-tier coverage', 750.00, 0.85, 'IA001'),
    ('IP004', 'Platinum Plan', 'High-end coverage with low deductibles', 250.00, 0.95, 'IA003'),
    ('IP005', 'Catastrophic Plan', 'For emergencies and lower monthly premiums', 2000.00, 0.7, 'IA004'),
    ('IP006', 'Family Plan', 'Coverage for entire family', 1500.00, 0.88, 'IA005'),
    ('IP007', 'Senior Plan', 'Tailored coverage for seniors', 800.00, 0.92, 'IA006'),
    ('IP008', 'Student Plan', 'Designed for students', 500.00, 0.82, 'IA007'),
    ('IP009', 'Dental Plan', 'Coverage for dental procedures', 300.00, 0.75, 'IA008'),
    ('IP010', 'Vision Plan', 'Coverage for vision-related services', 150.00, 0.85, 'IA009'),
    ('IP011', 'Wellness Plan', 'Coverage for preventive and wellness services', 150.00, 0.9, 'IA010'),
    ('IP012', 'Maternity Plan', 'Specialized coverage for maternity and prenatal care', 1000.00, 0.85, 'IA002'),
    ('IP013', 'Mental Health Plan', 'Focused on mental health services and therapy', 300.00, 0.88, 'IA005'),
    ('IP014', 'Accident Plan', 'Coverage for accidents and injuries', 500.00, 0.75, 'IA007'),
    ('IP015', 'Prescription Plan', 'Coverage for prescription medications', 200.00, 0.95, 'IA006'),
    ('IP016', 'Chronic Illness Plan', 'Tailored coverage for chronic health conditions', 750.00, 0.8, 'IA009'),
    ('IP017', 'Short-Term Plan', 'Temporary coverage for specific periods', 800.00, 0.7, 'IA004'),
    ('IP018', 'Long-Term Care Plan', 'Coverage for long-term care services', 1000.00, 0.85, 'IA008'),
    ('IP019', 'Cancer Coverage Plan', 'Specialized coverage for cancer treatments', 500.00, 0.9, 'IA003'),
    ('IP020', 'Retirement Health Plan', 'Designed for post-retirement healthcare needs', 1500.00, 0.82, 'IA010');

INSERT INTO PaymentPlans (PlanID, Title, PaymentPeriodDays, MonthlyMinimum, PaidPrinciple, MonthlyInterest)
VALUES
    ('PP001', 'Standard Plan', 30, 50.00, 0.00, 0.02),
    ('PP002', 'Premium Plan', 30, 100.00, 250.00, 0.03),
    ('PP003', 'Basic Plan', 30, 25.00, 0.00, 0.01);

INSERT INTO PayTypes (PayTypeID, PayType, AssociatedTaxDocument)
VALUES
    ('P001', 'Salary', 'W-2'),
    ('P002', 'Hourly', 'W-2'),
    ('P003', 'Contract', '1099-MISC'),
    ('P004', 'Commission', '1099-MISC'),
    ('P005', 'Freelance', '1099-MISC'),
    ('P006', 'Internship Stipend', 'W-2'),
    ('P007', 'Overtime', 'W-2'),
    ('P008', 'Salaried Non-Exempt', 'W-2'),
    ('P009', 'Piece Rate', '1099-MISC'),
    ('P010', 'Tips', 'W-2');

INSERT INTO Staff (
    StaffID, FirstName, LastName, StaffRole, Department, PayType, Salary, SSN, HomeAddress, DateOfBirth,
    TaxFilingStatus, HasOtherJob, OtherJobIncome, DependantTaxAdjustments, TaxDeductions, ExtraTaxWithholding
)
VALUES
    ('S001', 'John', 'Doe', 'R001', 'D001', 'P001', 60000.00, '123-45-6789', 'A001', '1985-05-15', 'S001', 0, 0.00, 0.00, 0.00, 0.00),
    ('S002', 'Jane', 'Smith', 'R002', 'D003', 'P002', 25.00, '987-65-4321', 'A002', '1990-10-20', 'S001', 1, 1500.00, 100.00, 50.00, 10.00),
    ('S003', 'Michael', 'Johnson', 'R003', 'D002', 'P001', 75000.00, '555-44-3333', 'A003', '1982-03-10', 'S002', 0, 0.00, 0.00, 0.00, 0.00),
    ('S004', 'Emily', 'Brown', 'R002', 'D005', 'P003', 0.00, '222-11-4444', 'A004', '1995-07-08', 'S001', 1, 800.00, 50.00, 25.00, 5.00),
    ('S005', 'Daniel', 'Williams', 'R004', 'D006', 'P002', 30.00, '777-88-9999', 'A005', '1988-12-01', 'S002', 0, 0.00, 0.00, 0.00, 0.00),
    ('S006', 'Olivia', 'Miller', 'R001', 'D003', 'P004', 0.00, '111-22-3333', 'A006', '1993-06-25', 'S001', 0, 0.00, 0.00, 0.00, 0.00),
    ('S007', 'James', 'Wilson', 'R005', 'D005', 'P001', 80000.00, '444-55-6666', 'A007', '1980-09-12', 'S003', 0, 0.00, 0.00, 0.00, 0.00),
    ('S008', 'Sophia', 'Taylor', 'R002', 'D007', 'P005', 0.00, '666-77-8888', 'A008', '1994-04-18', 'S002', 1, 1200.00, 75.00, 30.00, 10.00),
    ('S009', 'Benjamin', 'Anderson', 'R003', 'D009', 'P001', 85000.00, '333-44-5555', 'A009', '1987-11-05', 'S002', 0, 0.00, 0.00, 0.00, 0.00),
    ('S010', 'Ava', 'Thomas', 'R005', 'D007', 'P002', 28.00, '888-99-0000', 'A010', '1991-08-30', 'S001', 0, 0.00, 0.00, 0.00, 0.00);


INSERT INTO Patients (PatientID, FirstName, LastName, HomeAddress, DateOfBirth, SSN, InsuranceMembershipID, DeductibleProgress, PatientStatus)
VALUES
    ('P001', 'John', 'Doe', 'A001', '1985-05-15', '123-45-6789', 'M001', 100.00, 'S001'),
    ('P002', 'Jane', 'Smith', NULL, '1990-09-22', '987-65-4321', 'M002', 0.00, 'S001'),
    ('P003', 'Michael', 'Johnson', NULL, '1978-12-10', '555-12-3456', 'NAP003', 50.00, 'S001'),
    ('P004', 'Emily', 'Williams', 'A004', '1995-03-18', '111-22-3333', 'M003', 75.00, 'S001'),
    ('P005', 'Daniel', 'Brown', 'A005', '1982-07-04', '444-55-6666', 'M004', 0.00, 'S001'),
    ('P006', 'Olivia', 'Martinez', 'A006', '2000-11-30', '777-88-9999', 'M005', 125.00, 'S001'),
    ('P007', 'William', 'Garcia', 'A007', '1970-02-25', '222-33-4444', 'NAP007', 25.00, 'S001'),
    ('P008', 'Sophia', 'Robinson', 'A008', '1988-09-12', '666-77-8888', 'M006', 0.00, 'S001'),
    ('P009', 'Liam', 'Davis', 'A009', '1993-06-08', '888-99-0000', 'M007', 50.00, 'S001'),
    ('P010', 'Emma', 'Harris', 'A010', '1975-04-20', '333-44-5555', 'NAP010', 75.00, 'S001'),
    ('P011', 'Noah', 'Taylor', 'A011', '2002-01-03', '999-00-1111', 'M008', 0.00, 'S001'),
    ('P012', 'Ava', 'Adams', 'A012', '1998-08-17', '444-55-6666', 'M009', 100.00, 'S001'),
    ('P013', 'James', 'Wilson', 'A013', '1983-12-06', '222-33-4444', 'M010', 125.00, 'S001');

INSERT INTO SupplyInventory (
    supplyID, item, description, quantity, quantityUnit, unitPrice
)
VALUES
    ('SUP001', 'Pens', 'Pack of 10 black ink pens', 100, 'pack', 5.99),
    ('SUP002', 'Notepads', '5x8 inches, 50 sheets per pad', 50, 'pad', 2.49),
    ('SUP003', 'Staplers', 'Standard desk stapler', 20, 'piece', 12.99),
    ('SUP004', 'Printer Paper', 'Letter size, 500 sheets per ream', 150, 'ream', 7.89),
    ('SUP005', 'Binder Clips', 'Assorted sizes, pack of 30', 50, 'pack', 3.75),
    ('SUP006', 'Highlighters', 'Set of 5 assorted colors', 40, 'set', 6.49),
    ('SUP007', 'Scissors', '8-inch office scissors', 25, 'pair', 8.99),
    ('SUP008', 'Whiteboard Markers', 'Pack of 4 assorted colors', 30, 'pack', 9.25),
    ('SUP009', 'File Folders', 'Letter size, pack of 50', 75, 'pack', 11.50),
    ('SUP010', 'Tape Dispensers', 'Desktop tape dispenser', 15, 'piece', 4.99),
    ('SUP011', 'Paper Clips', 'Box of 1000 standard paper clips', 100, 'box', 1.99),
    ('SUP012', 'Calculators', 'Basic 12-digit calculators', 10, 'piece', 14.75);


INSERT INTO Procedures (ProcedureID, ProcedureDescription, Code, Fee, Department)
VALUES
    ('P0001', 'Cardiac Stress Test', 'CST001', 300.00, 'D001'),
    ('P0002', 'Knee X-ray', 'XRY001', 150.00, 'D002'),
    ('P0003', 'Pediatric Check-up', 'CHK001', 80.00, 'D003');

INSERT INTO WorkOrders (
    orderID, orderDate, supplyCode, status, description, serviceCompanyName, serviceTechName, serviceCompanyContact, lastServiceDate
)
VALUES
    ('WO001', '2023-08-15', 'SUP001', 'S001', 'Replace broken pens in office', 'Office Supplies Inc.', 'John Smith', 'A001', '2023-08-20'),
    ('WO002', '2023-08-16', 'SUP003', 'S005', 'Repair staplers on 3rd floor', 'Tech Repair Solutions', 'Emily Johnson', 'A005', '2023-08-22'),
    ('WO003', '2023-08-17', 'SUP007', 'S004', 'Inspect and maintain office scissors', 'ToolFix Services', 'Michael Brown', 'A009', '2023-08-18'),
    ('WO004', '2023-08-18', 'SUP010', 'S003', 'Check and refill tape dispensers', 'Office Maintenance Co.', 'Sarah Lee', 'A004', '2023-08-21'),
    ('WO005', '2023-08-19', 'SUP005', 'S002', 'Restock binder clips in storage room', 'Supplies R Us', 'David Johnson', 'A012', '2023-08-25'),
    ('WO006', '2023-08-20', 'SUP011', 'S004', 'Deliver paper clips to 2nd floor', 'Fast Delivery Services', 'Jessica White', 'A008', '2023-08-21'),
    ('WO007', '2023-08-21', 'SUP006', 'S005', 'Replace worn-out highlighters in conference room', 'Bright Office Solutions', 'Alex Davis', 'A002', '2023-08-23'),
    ('WO008', '2023-08-22', 'SUP009', 'S001', 'Organize and label file folders in supply closet', 'FileMasters', 'Daniel Wilson', 'A006', '2023-08-24'),
    ('WO009', '2023-08-23', 'SUP002', 'S002', 'Deliver notepads to reception desk', 'Speedy Supplies Delivery', 'Olivia Adams', 'A010', '2023-08-24'),
    ('WO010', '2023-08-24', 'SUP004', 'S003', 'Check printer paper stock and reorder if needed', 'Printing Essentials', 'Sophia Smith', 'A007', '2023-08-28');

INSERT INTO PurchaseOrders (
    orderID, orderDate, supplyCode, status, quantity, unitPrice, totalPrice
)
VALUES
    ('PO001', '2023-08-15', 'SUP001', 'S001', 100, 10.00, 1000.00),
    ('PO002', '2023-08-16', 'SUP003', 'S002', 50, 5.00, 250.00),
    ('PO003', '2023-08-17', 'SUP007', 'S003', 200, 2.50, 500.00),
    ('PO004', '2023-08-18', 'SUP010', 'S004', 30, 8.00, 240.00),
    ('PO005', '2023-08-19', 'SUP005', 'S003', 150, 12.00, 1800.00),
    ('PO006', '2023-08-20', 'SUP011', 'S002', 75, 6.00, 450.00),
    ('PO007', '2023-08-21', 'SUP006', 'S001', 40, 3.00, 120.00),
    ('PO008', '2023-08-22', 'SUP009', 'S002', 100, 7.50, 750.00),
    ('PO009', '2023-08-23', 'SUP002', 'S003', 80, 4.00, 320.00),
    ('PO010', '2023-08-24', 'SUP004', 'S004', 60, 1.50, 90.00);

INSERT INTO ProcedureRoleRequirements (RequirementID, RoleCode, ProcedureCode, Description, Quantity, Cost)
VALUES
    ('PRR001', 'R001', 'P0001', 'Medical doctor', 1, 150.00),
    ('PRR002', 'R002', 'P0002', 'Nursing staff', 1, 50.00),
    ('PRR003', 'R003', 'P0003', 'Administrative staff', 1, 0.00);

INSERT INTO ProcedureSupplyRequirements (RequirementID, SupplyCode, ProcedureCode, Quantity, UnitPrice)
VALUES
    ('PSR001', 'SUP001', 'P0001', 10, 5.00),
    ('PSR002', 'SUP002', 'P0002', 50, 2.00),
    ('PSR003', 'SUP003', 'P0003', 5, 10.00);

INSERT INTO Appointment (
    AppointmentID, AppointmentDate, StartTime, EndTime, PatientID, ProviderID, RoomID, ProcedureCode, Fee, Notes, ZippedFiles
)
VALUES
    ('A001', '2023-08-15 09:00:00', '09:00:00', '10:00:00', 'P001', 'S001', 'R001', 'P0001', 150.00, 'Regular checkup', NULL),
    ('A002', '2023-08-15 11:30:00', '11:30:00', '12:30:00', 'P002', 'S003', 'R003', 'P0003', 200.00, 'Dental cleaning', NULL),
    ('A003', '2023-08-16 14:15:00', '14:15:00', '15:30:00', 'P003', 'S002', 'R002', 'P0002', 300.00, 'Physical examination', NULL),
    ('A004', '2023-08-17 10:45:00', '10:45:00', '11:30:00', 'P004', 'S004', 'R002', 'P0003', 180.00, 'Follow-up appointment', NULL),
    ('A005', '2023-08-18 13:00:00', '13:00:00', '14:00:00', 'P005', 'S001', 'R001', 'P0002', 250.00, 'Diagnostic test review', NULL),
    ('A006', '2023-08-19 16:30:00', '16:30:00', '17:15:00', 'P006', 'S003', 'R003', 'P0002', 120.00, 'Minor procedure', NULL),
    ('A007', '2023-08-20 12:45:00', '12:45:00', '13:30:00', 'P007', 'S002', 'R002', 'P0001', 170.00, 'Consultation', NULL),
    ('A008', '2023-08-21 15:30:00', '15:30:00', '16:30:00', 'P008', 'S004', 'R001', 'P0003', 220.00, 'Treatment session', NULL),
    ('A009', '2023-08-22 09:15:00', '09:15:00', '10:00:00', 'P009', 'S001', 'R001', 'P0002', 190.00, 'Medication review', NULL),
    ('A010', '2023-08-22 11:00:00', '11:00:00', '12:00:00', 'P010', 'S003', 'R003', 'P0001', 160.00, 'Follow-up', NULL),
    ('A011', '2023-08-23 14:00:00', '14:00:00', '15:00:00', 'P011', 'S002', 'R002', 'P0003', 180.00, 'Document review', X'789C56301234567890ABCDEF');

INSERT INTO Claims (ClaimID, FileDate, MembershipID, PatientID, AppointmentID, PaymentPlanID, PaidDeductible, PatientCopay, InsurancePay, ClaimStatus, Adjustments)
VALUES
    ('C001', '2023-07-15', 'M001', 'P001', 'A001', 'PP001', 0.00, 20.00, 80.00, 'S001', 5.00),
    ('C002', '2023-07-20', 'M002', 'P002', 'A002', 'PP002', 0.00, 30.00, 70.00, 'S003', 10.00),
    ('C003', '2023-07-25', 'M001', 'P001', 'A003', 'PP003', 20.00, 10.00, 40.00, 'S005', 0.00);

INSERT INTO Transactions (TransactionID, TransactionDate, PatientInsurancePlan, IsSourceInsurance, PaymentPlanID, AppointmentID, PatientID, Total, RemainingBalance, TransactionStatus, InNetwork)
VALUES
    ('T001', '2023-07-01', 'IP001', 1, 'PP001', 'A001', 'P001', 100.00, 100.00, 'S001', 1),
    ('T002', '2023-07-05', NULL, 0, 'PP002', 'A002', 'P002', 250.00, 250.00, 'S002', 1),
    ('T003', '2023-07-10', 'IP003', 1, 'PP003', 'A003', 'P001', 50.00, 50.00, 'S003', 1);

INSERT INTO PlanMembershipJoin (MembershipID, PlanID)
VALUES
    ('M001', 'IP001'),
    ('M002', 'IP001'),
    ('M003', 'IP003'),
    ('M004', 'IP002'),
    ('M005', 'IP003'),
    ('M006', 'IP004'),
    ('M007', 'IP004'),
    ('M008', 'IP005'),
    ('M009', 'IP006'),
    ('M010', 'IP007');

INSERT INTO Payroll (paymentID, issueDate, staffID, payType, staffTIN, grossWages, socialTaxableWages, socialTax, medicareTaxableWages, medicareTax, localTaxableWages, localTax, stateTaxableWages, stateTax, federalTax)
VALUES
    ('P001', '2023-08-01', 'S001', 'P001', '123456789', 5000.00, 5000.00, 250.00, 5000.00, 125.00, 5000.00, 150.00, 5000.00, 200.00, 1000.00),
    ('P002', '2023-08-01', 'S002', 'P001', '987654321', 1000.00, 1000.00, 50.00, 1000.00, 25.00, 1000.00, 30.00, 1000.00, 40.00, 200.00),
    ('P003', '2023-08-01', 'S003', 'P001', '456789012', 6000.00, 6000.00, 300.00, 6000.00, 150.00, 6000.00, 180.00, 6000.00, 240.00, 1200.00),
    ('P004', '2023-08-01', 'S004', 'P001', '789012345', 5500.00, 5500.00, 275.00, 5500.00, 137.50, 5500.00, 165.00, 5500.00, 220.00, 1100.00),
    ('P005', '2023-08-01', 'S005', 'P001', '345678901', 1200.00, 1200.00, 60.00, 1200.00, 30.00, 1200.00, 36.00, 1200.00, 48.00, 240.00),
    ('P006', '2023-08-01', 'S006', 'P002', '890123456', 4800.00, 4800.00, 240.00, 4800.00, 120.00, 4800.00, 144.00, 4800.00, 192.00, 960.00),
    ('P007', '2023-08-01', 'S007', 'P002', '567890123', 900.00, 900.00, 45.00, 900.00, 22.50, 900.00, 27.00, 900.00, 36.00, 180.00),
    ('P008', '2023-08-01', 'S008', 'P002', '234567890', 6200.00, 6200.00, 310.00, 6200.00, 155.00, 6200.00, 186.00, 6200.00, 248.00, 1240.00),
    ('P009', '2023-08-01', 'S009', 'P001', '901234567', 1500.00, 1500.00, 75.00, 1500.00, 37.50, 1500.00, 45.00, 1500.00, 60.00, 300.00);