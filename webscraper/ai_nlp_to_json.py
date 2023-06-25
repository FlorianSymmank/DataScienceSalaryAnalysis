import os
import openai
import json
import time

# Set the API key
openai.api_key = ''

# Create a directory to store the structured job ads
if not os.path.exists("job-ads-structured"):
    os.makedirs("job-ads-structured")

# Loop through all .txt files in the unstructured job ads directory
for filename in os.listdir("job-ads-unstructured"):
    if filename.endswith(".txt"):
        
        # Skip file if it already exists in the "job-ads-structured" directory
        if os.path.exists(os.path.join("job-ads-structured", filename)):
            continue

        with open(os.path.join("job-ads-unstructured", filename), 'r', encoding='utf-8') as f:
            content = f.read()
            messages = [
                {"role": "system", "content": "You are a helpful assistant.  Format your response as JSON. Reply with only the answer in JSON form and include no other commentary."},
                {"role": "user", "content": f'Here is a job posting:\n"""\n{content}\n"""\n\nTransform it into a json file of this form:\n\n{{"job_title": string,"company": string,"location": string,"salary_euro":{{"amount":integer, "period": string}} OR null,"employment_type": string,"requirements":  string,"skills": array[string],"date_posted": yyyy-mm-dd,}}\n\nOnly output the raw json data!'}
            ]
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
            output = response['choices'][0]['message']['content']

            try:
                # Write the output to a new file in the structured job ads directory
                with open(os.path.join("job-ads-structured", filename), 'w', encoding='utf-8') as f:
                    f.write(output)

            except:
                pass
