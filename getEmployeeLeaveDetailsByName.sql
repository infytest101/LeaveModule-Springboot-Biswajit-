CREATE DEFINER=`root`@`localhost` PROCEDURE `getEmployeeLeaveDetailsByName`(IN employeeName VARCHAR(255))
BEGIN

 SET @n := 0;
 SELECT @n := @n+1 SL ,
(case when la.leave_id=1 then 'CL' when la.leave_id=2 then 'ML'  else 'EL' END) As leaveType,
la.total_leave AS Entitled,
SUM(CASE WHEN lb.status = 'APPROVED' OR lb.status = 'REJECTED' THEN 1 else 0 END) AS applied,
SUM(CASE WHEN lb.status = 'APPROVED' THEN 1 ELSE 0 END) AS Granted,
SUM(CASE WHEN lb.status = 'REJECTED' THEN 1 ELSE 0 END) AS Reject,
la.total_leave - SUM(CASE WHEN lb.status = 'APPROVED' THEN 1 ELSE 0 END) AS Balance FROM tbl_employee_dtls ed JOIN
tbl_emp_band eb ON ed.band_id = eb.band_id LEFT JOIN tbl_leave_allocation la ON ed.band_id = la.band_id
LEFT JOIN tbl_leave_apply lb ON ed.emp_id = lb.emp_id AND la.leave_id = lb.leave_id
WHERE ed.name = employeeName GROUP BY ed.name, la.leave_id
UNION
 SELECT @n := @n+1 SL ,'Total' AS leave_type,
SUM(Entitled) AS Entitled,SUM(applied) AS applied,SUM(Granted) AS Granted,
SUM(Reject) AS Reject,SUM(Balance) AS Balance
FROM(SELECT ed.name AS employee_name,la.leave_id,
(case when la.leave_id=1 then 'CL' when la.leave_id=2 then 'ML'  else 'EL' END) As leaveType,
la.total_leave AS Entitled,
SUM(CASE WHEN lb.status = 'APPROVED' OR lb.status = 'REJECTED' THEN 1 else 0 END) AS applied,
SUM(CASE WHEN lb.status = 'APPROVED' THEN 1 ELSE 0 END) AS Granted,
SUM(CASE WHEN lb.status = 'REJECTED' THEN 1 ELSE 0 END) AS Reject,
la.total_leave - SUM(CASE WHEN lb.status = 'APPROVED' THEN 1 ELSE 0 END) AS Balance FROM
tbl_employee_dtls ed JOIN tbl_emp_band eb ON ed.band_id = eb.band_id LEFT JOIN tbl_leave_allocation la ON ed.band_id = la.band_id
LEFT JOIN tbl_leave_apply lb ON ed.emp_id = lb.emp_id AND la.leave_id = lb.leave_id
WHERE ed.name = employeeName GROUP BY ed.name, la.leave_id) AS subquery;
END