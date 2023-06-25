from selenium import webdriver
import time
import csv
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import os

# specify the path to chromedriver.exe
driver_path = r"/chromedriver_13.exe"

# create a new Chrome browser instance
driver = webdriver.Chrome(executable_path=driver_path)

page = 0

# Create a directory
if not os.path.exists("job-ads-unstructured"):
    os.makedirs("job-ads-unstructured")

with open('urls_filtered.csv', 'r', newline='') as file:
    reader = csv.reader(file)

    for url in reader:
        driver.get(url[0])  # URL is the first (and only) column

        # Wait a bit
        time.sleep(2)

        # Find the element and get its innerText
        try:
            element = driver.find_element(By.CSS_SELECTOR, ".jobsearch-JobComponent")
            text = element.get_attribute('innerText')

            # Store into a text file
            filename = str(time.time()) + '.txt'  # Use current timestamp as filename
            with open(os.path.join("job-ads-unstructured", filename), "w", encoding='utf-8') as text_file:
                text_file.write(text)
        except Exception as e:
            print(f"Error processing URL {url[0]}: {str(e)}")
