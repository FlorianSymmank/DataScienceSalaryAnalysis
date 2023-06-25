SELECT COUNT(*) AS count
FROM Data
WHERE salary_euro_period IN ('pro Monat', 'monthly net payment', 'month', 'yearly', 'monthly');

SELECT 
    CASE
        WHEN salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base') THEN salary_euro_amount * 1
        WHEN salary_euro_period IN ('pro Monat', 'monthly net payment', 'month', 'yearly', 'monthly') THEN salary_euro_amount * 12
    END AS salary
FROM Data
WHERE salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base', 'pro Monat', 'monthly net payment', 'month', 'yearly', 'monthly');



SELECT AVG(salary) AS mean_salary
FROM (
    SELECT 
        CASE
            WHEN salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base') THEN salary_euro_amount * 1
            WHEN salary_euro_period IN ('pro Monat', 'monthly net payment', 'month', 'yearly', 'monthly') THEN salary_euro_amount * 12
        END AS salary
    FROM Data
    WHERE salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base', 'pro Monat', 'monthly net payment', 'month', 'yearly', 'monthly')
) _;


CREATE TEMPORARY TABLE NormalizedSalary AS
    SELECT 
        CASE
            WHEN salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base') THEN salary_euro_amount
            WHEN salary_euro_period IN ('pro Monat', 'monthly net payment', 'month', 'monthly') THEN salary_euro_amount * 12
        END AS salary
    FROM Data
    WHERE salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base', 'pro Monat', 'monthly net payment', 'month', 'monthly');

SELECT salary AS mean_salary
FROM NormalizedSalary
ORDER BY salary
LIMIT 1
OFFSET (SELECT COUNT(*) FROM NormalizedSalary) / 2;


SELECT job_title, employment_type, salary, id
FROM (
    SELECT 
        job_title, employment_type, id,
        CASE
            WHEN salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base') THEN salary_euro_amount * 1
            WHEN salary_euro_period IN ('pro Monat', 'monthly net payment', 'month', 'yearly', 'monthly') THEN salary_euro_amount * 12
        END AS salary
    FROM Data
    WHERE salary_euro_period IN ('pro Jahr', 'per year', 'year', 'yearly', 'Base', 'pro Monat', 'monthly net payment', 'month', 'yearly', 'monthly')
) _;