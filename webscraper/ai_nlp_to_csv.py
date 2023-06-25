import os
import openai
import time
import csv

# Set the API key
openai.api_key = ''


# Create the CSV file to store the structured job ads
with open('job-ads-structured.csv', 'w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    writer.writerow(["filename", "experience_level", "employment_type", "job_title", "remote_ratio", "salary"]) # add header row

# Open the CSV file
with open('job_title,employment_type,salary,id.csv', 'r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    
    for row in csv_reader:
        filename = row['id']  
        salary = row['salary'] 

        if filename.endswith(".txt"):
            
            with open(os.path.join("job-ads-unstructured", filename), 'r', encoding='utf-8') as f:
                content = f.read()

                # Prepare the message content
                messages = [
                    {"role": "system", "content": "You are a helpful assistant.  Format your response as comma separated values. Reply with only the comma separated values and include no other commentary."},
                    {"role": "user", "content": f"""
{content}

instructions for a manual extraction of specific fields from a job posting. These fields are experience_level, employment_type, job_title, and remote_ratio.

Experience Level (EN, ML, SE, EX): This depends on the job requirements and responsibilities mentioned in the job ad. The following mapping should be used:

Junior: EN
Mid-Level: ML
Senior: SE
Executive: EX
Employment Type (PT, FT, CT, FL): This should be mentioned explicitly in the job ad. The following mapping should be used:

Teilzeit: PT
Vollzeit: FT
Vertrag: CT
Freiberuflich: FL
Job Title: This is typically the position for which the company is hiring. Any prefix such as "junior", "senior", or "intern" should be removed as the experience level field already covers this information.

Remote Ratio: This value should be an integer between 0 and 100, representing the percentage of work that can be done remotely. The exact value will depend on the details provided in the job ad.

The extracted values will be represented in a comma-separated format like so: experience_level, employment_type, job_title, remote_ratio.

respond to this message with only the comma separated values, if you don't have a value for any field assume something that fits the datatype, never use NULL or equivalent
"""}
                ]

                # Create a model and get the response
                while True:
                    try:
                        response = openai.ChatCompletion.create(
                            model="gpt-3.5-turbo",
                            messages=messages
                        )
                        break
                    except:
                        print("Rate limit exceeded. Waiting before retrying...")
                        time.sleep(30)  

                # Extract the response
                output = response['choices'][0]['message']['content']

                try:
                    # Append the output to the CSV file
                    with open('job-ads-structured.csv', 'a', newline='', encoding='utf-8') as file:
                        writer = csv.writer(file)
                        writer.writerow([filename] + output.split(", ") + [salary])  # Add salary to the row

                except:
                    pass
