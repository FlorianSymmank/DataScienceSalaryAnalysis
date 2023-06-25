SELECT location, COUNT(*) AS frequency
FROM Data
GROUP BY location
ORDER BY frequency DESC
LIMIT 20;