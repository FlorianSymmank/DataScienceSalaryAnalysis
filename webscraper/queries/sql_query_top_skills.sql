SELECT skill, COUNT(*) AS frequency
FROM Skills
WHERE LOWER(skill) <> 'data science'
GROUP BY LOWER(skill)
ORDER BY frequency DESC
LIMIT 20;