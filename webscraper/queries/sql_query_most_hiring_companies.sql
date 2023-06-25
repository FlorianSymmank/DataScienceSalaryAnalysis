SELECT company, COUNT(*) AS frequency
FROM Data
GROUP BY company
ORDER BY frequency DESC
LIMIT 25;