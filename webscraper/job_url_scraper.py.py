from selenium import webdriver
import time
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import csv

# specify the path to chromedriver.exe
driver_path = r"/chromedriver_13.exe"

# create a new Chrome browser instance
driver = webdriver.Chrome(executable_path=driver_path)

base_url = "https://de.indeed.com/jobs?q=data+science&start="

page = 0

while True:  # Start of loop
    url = base_url + str(page)
    driver.get(url)

    # Delay for page load
    time.sleep(1)

    urls = [element.get_attribute('href') for element in driver.find_elements(By.CSS_SELECTOR, '.jobTitle>a')]

    if not urls:  # If urls is an empty list, break out of the loop
        break

    # Open the file in append mode ('a')
    with open('urls.csv', 'a', newline='') as file:
        writer = csv.writer(file)

        # Loop through the URLs and write each one to the file
        for url in urls:
            # writerow expects a list
            writer.writerow([url])
    
    page += 10  # Increment the page by 10
