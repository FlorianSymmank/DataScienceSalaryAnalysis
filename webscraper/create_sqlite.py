import sqlite3
import os
import json
import pandas as pd
import re

# Initialize an empty dataframe
df = pd.DataFrame()

# Initialize an empty list to store skills data
skills_data = []

# Read each JSON file into a pandas dataframe
for filename in os.listdir("job-ads-structured"):
    if filename.endswith(".txt"):
        with open(os.path.join("job-ads-structured", filename), 'r', encoding='utf-8') as f:
            try:
                data = json.load(f)
                temp_df = pd.json_normalize(data)
                temp_df['id'] = filename # Add unique identifier to each job ad
                if 'skills' in temp_df.columns and temp_df['skills'][0] is not None:
                    for skill in temp_df['skills'][0]: # iterate over the skills
                        skills_data.append([filename, skill])
                temp_df.drop(columns=['skills'], inplace=True, errors='ignore')
                df = pd.concat([df, temp_df], ignore_index=True)
            except json.JSONDecodeError:
                print(f"Skipping file {filename} due to JSONDecodeError")

# Rename the 'salary_euro.amount' and 'salary_euro.period' columns
df.rename(columns={'salary_euro.amount': 'salary_euro_amount', 'salary_euro.period': 'salary_euro_period'}, inplace=True)

# Remove the 'salary_euro' column
df.drop(columns=['salary_euro'], inplace=True)

# Ensure all object columns are of type string
for col in df.columns:
    if df[col].dtype == 'object':
        df[col] = df[col].apply(lambda x: str(x) if x is not None else x)


print(df['location'])


# Remove numbers from the location column
df['location'] = df['location'].apply(lambda x: re.sub(r'\d+', '', x))
print(df['location'])

# Remove trailing whitespace from the location column
df['location'] = df['location'].str.strip()
print(df['location'])

# Lowercase all characters in the location column
df['location'] = df['location'].str.lower()
print(df['location'])


# Create a connection to the SQLite database
# If the database does not exist, it will be created
conn = sqlite3.connect('sqlite_database.db')

# Convert the DataFrame to a SQL database
df.to_sql('Data', conn, if_exists='replace', index=False)

# Convert the skills data to a dataframe and save it to the database
df_skills = pd.DataFrame(skills_data, columns=['job_id', 'skill'])
df_skills.to_sql('Skills', conn, if_exists='replace', index=False)

# Close the connection to the database
conn.close()