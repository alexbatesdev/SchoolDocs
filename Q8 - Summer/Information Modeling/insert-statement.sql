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

INSERT INTO Addresses (AddressID, Line1, Line2, City, Region, PostalCode, Country)
VALUES
    ('A001', '123 Main Street', NULL, 'New York', 'NY', '10001', 'USA'),
    ('A002', '456 Elm Avenue', 'Suite 201', 'Los Angeles', 'CA', '90002', 'USA'),
    ('A003', '789 Oak Lane', NULL, 'Chicago', 'IL', '60601', 'USA'),
    ('A004', '321 Pine Street', NULL, 'Houston', 'TX', '77001', 'USA'),
    ('A005', '987 Maple Drive', 'Apt 102', 'Miami', 'FL', '33130', 'USA'),
    ('A006', '654 Cedar Road', NULL, 'San Francisco', 'CA', '94105', 'USA'),
    ('A007', '890 Walnut Avenue', NULL, 'Phoenix', 'AZ', '85001', 'USA'),
    ('A008', '1234 Birch Lane', 'Unit B', 'Philadelphia', 'PA', '19101', 'USA'),
    ('A009', '567 Willow Street', NULL, 'Seattle', 'WA', '98101', 'USA'),
    ('A010', '876 Oak Avenue', NULL, 'Boston', 'MA', '02101', 'USA'),
    ('A011', '432 Elm Court', 'Suite 301', 'Atlanta', 'GA', '30301', 'USA'),
    ('A012', '234 Pine Road', NULL, 'Dallas', 'TX', '75201', 'USA'),
    ('A013', '876 Maple Lane', 'Apt 202', 'Denver', 'CO', '80201', 'USA');

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

INSERT INTO Staff (StaffID, fullName, StaffRole, Department)
VALUES
    ('S001', 'Dr. Smith', 'R001', 'D001'),
    ('S002', 'Nurse Johnson', 'R002', 'D002'),
    ('S003', 'Admin Williams', 'R003', 'D003'),
    ('S004', 'Admin Davis', 'R003', 'D001'),
    ('S005', 'Dr. Brown', 'R001', 'D002'),
    ('S006', 'Nurse Adams', 'R002', 'D002'),
    ('S007', 'Admin Wilson', 'R003', 'D002'),
    ('S008', 'Dr. Taylor', 'R001', 'D003'),
    ('S009', 'Nurse White', 'R002', 'D003'),
    ('S010', 'Admin Harris', 'R003', 'D001'),
    ('S011', 'Dr. Williams', 'R001', 'D001'),
    ('S012', 'Nurse Miller', 'R002', 'D002'),
    ('S013', 'Admin Rodriguez', 'R003', 'D003'),
    ('S014', 'Dr. Jackson', 'R001', 'D002'),
    ('S015', 'Nurse Martinez', 'R002', 'D003'),
    ('S016', 'Admin Lee', 'R003', 'D001'),
    ('S017', 'Dr. Garcia', 'R001', 'D003'),
    ('S018', 'Nurse Davis', 'R002', 'D001'),
    ('S019', 'Admin Robinson', 'R003', 'D002'),
    ('S020', 'Dr. Taylor', 'R001', 'D003'),
    ('S021', 'Nurse White', 'R002', 'D001'),
    ('S022', 'Admin Harris', 'R003', 'D002'),
    ('S023', 'Dr. Smith', 'R001', 'D001'),
    ('S024', 'Nurse Johnson', 'R002', 'D003'),
    ('S025', 'Admin Davis', 'R003', 'D001'),
    ('S026', 'Dr. Brown', 'R001', 'D002'),
    ('S027', 'Nurse Adams', 'R002', 'D003'),
    ('S028', 'Admin Wilson', 'R003', 'D002'),
    ('S029', 'Dr. Martinez', 'R001', 'D001'),
    ('S030', 'Nurse Taylor', 'R002', 'D003');

INSERT INTO Patients (PatientID, FullName, HomeAddress, DateOfBirth, SSN, InsuranceMembershipID, DeductibleProgress, PatientStatus)
VALUES
    ('P001', 'John Doe', 'A001', '1985-05-15', '123-45-6789', 'M001', 100.00, 'S001'),
    ('P002', 'Jane Smith', NULL, '1990-09-22', '987-65-4321', 'M002', 0.00, 'S001'),
    ('P003', 'Michael Johnson', NULL, '1978-12-10', '555-12-3456', 'NAP003', 50.00, 'S001'),
    ('P004', 'Emily Williams', 'A004', '1995-03-18', '111-22-3333', 'M003', 75.00, 'S001'),
    ('P005', 'Daniel Brown', 'A005', '1982-07-04', '444-55-6666', 'M004', 0.00, 'S001'),
    ('P006', 'Olivia Martinez', 'A006', '2000-11-30', '777-88-9999', 'M005', 125.00, 'S001'),
    ('P007', 'William Garcia', 'A007', '1970-02-25', '222-33-4444', 'NAP007', 25.00, 'S001'),
    ('P008', 'Sophia Robinson', 'A008', '1988-09-12', '666-77-8888', 'M006', 0.00, 'S001'),
    ('P009', 'Liam Davis', 'A009', '1993-06-08', '888-99-0000', 'M007', 50.00, 'S001'),
    ('P010', 'Emma Harris', 'A010', '1975-04-20', '333-44-5555', 'NAP010', 75.00, 'S001'),
    ('P011', 'Noah Taylor', 'A011', '2002-01-03', '999-00-1111', 'M008', 0.00, 'S001'),
    ('P012', 'Ava Adams', 'A012', '1998-08-17', '444-55-6666', 'M009', 100.00, 'S001'),
    ('P013', 'James Wilson', 'A013', '1983-12-06', '222-33-4444', 'M010', 125.00, 'S001');

INSERT INTO ProcedureHistory (ProcedureID, ProcedureDescription, Code, Fee, Department)
VALUES
    ('PH001', 'Cardiac Stress Test', 'CST001', 300.00, 'D001'),
    ('PH002', 'Knee X-ray', 'XRY001', 150.00, 'D002'),
    ('PH003', 'Pediatric Check-up', 'CHK001', 80.00, 'D003');

INSERT INTO ProcedureRoleRequirements (RequirementID, RoleCode, ProcedureCode, RequiredRole, RequiredDescription, IsOptional, Quantity, Cost)
VALUES
    ('PRR001', 'R001', 'PH001', 'Doctor', 'Medical doctor', 0, 1, 150.00),
    ('PRR002', 'R002', 'PH002', 'Nurse', 'Nursing staff', 1, 1, 50.00),
    ('PRR003', 'R003', 'PH003', 'Administrator', 'Administrative staff', 1, 1, 0.00);

INSERT INTO ProcedureSupplyRequirements (SupplyID, SupplyCode, ProcedureCode, Item, ItemDescription, Quantity, QuantityUnit, UnitPrice)
VALUES
    ('PSR001', 'SUP001', 'PH001', 'Electrodes', 'Medical electrodes for stress test', 10, 'Pieces', 5.00),
    ('PSR002', 'SUP002', 'PH002', 'X-ray film', 'Film for X-ray imaging', 50, 'Sheets', 2.00),
    ('PSR003', 'SUP003', 'PH003', 'Vaccine', 'Pediatric vaccine for check-up', 5, 'Doses', 10.00);

INSERT INTO Appointment (AppointmentID, AppointmentDate, StartTime, EndTime, PatientID, ProviderID, RoomID, ProcedureCode, Fee)
VALUES
    ('A001', '2023-07-01', '10:00:00', '11:00:00', 'P001', 'S001', 'R001', 'CST001', 1500.00),
    ('A002', '2023-07-05', '14:30:00', '15:30:00', 'P002', 'S002', 'R002', 'XRY001', 800.00),
    ('A003', '2023-07-10', '09:00:00', '09:30:00', 'P001', 'S003', 'R003', 'CHK001', 9990.00);

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