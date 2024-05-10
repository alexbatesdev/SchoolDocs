SELECT PlanID, COUNT(*) AS TotalCount FROM PlanMembershipJoin 
GROUP BY PlanID;

SELECT PatientInsurancePlan, SUM(Total) AS 'Received Insurance Pay' FROM Transactions 
WHERE PatientInsurancePlan IS NOT NULL 
GROUP BY PatientInsurancePlan;

SELECT Patients.PatientID, Patients.FirstName, Patients.LastName, SUM(Transactions.RemainingBalance) AS 'Remaining Balance' FROM Transactions 
LEFT JOIN PaymentPlans ON PaymentPlans.PlanID = Transactions.PaymentPlanID 
LEFT JOIN Appointment ON Appointment.AppointmentID = Transactions.AppointmentID 
LEFT JOIN Patients ON Patients.PatientID = Transactions.PatientID 
WHERE PaymentPlans.PaidPrinciple <= Appointment.Fee 
GROUP BY Patients.PatientID, Patients.LastName;

SELECT ProcedureCode, COUNT(*) AS 'Total Appointments' FROM Appointment 
GROUP BY ProcedureCode;

SELECT Staff.LastName, ProcedureCode, COUNT(*) AS 'Total Appointments' FROM Appointment 
Left JOIN Staff ON Staff.StaffID = Appointment.ProviderID 
GROUP BY LastName, ProcedureCode;

SELECT SUM(InsurancePay) AS TotalDeniedInsurancePay FROM Claims 
WHERE ClaimStatus = 'S005'

SELECT * FROM Patients 
LEFT JOIN PlanMembershipJoin ON PlanMembershipJoin.MembershipID = Patients.InsuranceMembershipID LEFT JOIN InsurancePlan ON InsurancePlan.PlanID = PlanMembershipJoin.PlanID 
WHERE Patients.DeductibleProgress >= InsurancePlan.Deductible

-- Tax Report

SELECT Staff.StaffID, Staff.LastName, PayTypes.PayType, PayTypes.AssociatedTaxDocument, Staff.SSN, Payroll.StaffTIN, Addresses.Line1, Addresses.Line2, Addresses.City, Addresses.Region, Addresses.PostalCode, Addresses.Country, Staff.Salary, 
SUM(GrossWages), SUM(SocialTaxableWages), SUM(SocialTax), SUM(MedicareTaxableWages), SUM(MedicareTax), SUM(LocalTaxableWages), SUM(LocalTax), SUM(StateTaxableWages), SUM(StateTax), SUM(FederalTax), 
Staff.TaxFilingStatus, Staff.HasOtherJob, Staff.OtherJobIncome, Staff.DependantTaxAdjustments, Staff.TaxDeductions, Staff.ExtraTaxWithholding  FROM Payroll
LEFT JOIN Staff ON Staff.StaffID = Payroll.StaffID 
LEFT JOIN PayTypes ON PayTypes.PayTypeID = Staff.PayType 
LEFT JOIN Addresses ON Addresses.AddressID = Staff.HomeAddress 
WHERE Payroll.IssueDate >= '2023-01-01' AND Payroll.IssueDate <= '2024-01-01'
GROUP BY StaffID, Staff.PayType, PayTypes.AssociatedTaxDocument, Payroll.StaffTIN 