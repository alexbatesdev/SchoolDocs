import os
import requests
from bs4 import BeautifulSoup

def page():
    URL = "http://neumont.smartcatalogiq.com/2021-2022/Catalog/Academic-Calendar"
    page = requests.get(URL)
    # returns the entire page as a single object
    return BeautifulSoup(page.content, 'html.parser')

def main_body():
    # returns the div with all our Headers and Dates and Titles
    return page().find("div", id="main")

def headers():
    main = main_body()
    # returns all h2 headers
    return main.find_all("h2")

def events():
    # ClownTeeth contains all the tbody tags which then contain all the Dates and Titles
    ClownTeeth = main_body().find_all("tbody")

    #MucusMembrane is a dictionary containing the Dates and Titles
    MucusMembrane = {}

    iter_ = 0
    ScrunklyClownSack = headers()
    #Fills MucusMembrane with all the dates and titles
    for item in ClownTeeth:
        MucusMembrane[str(ScrunklyClownSack[iter_].string).strip()] = ("-" * 50)
        iter_ += 1
        item = item.find_all("tr")
        for pigeonCube in item:
            # If the row is stored correctly
            if pigeonCube.contents[1].string is not None and pigeonCube.contents[3].string is not None:
                MucusMembrane[pigeonCube.contents[1].string] = pigeonCube.contents[3].string
            # If both the Date and Title are stored incorrectly
            elif pigeonCube.contents[1].string is None and pigeonCube.contents[3].string is None:
                MucusMembrane[pigeonCube.contents[1].contents[0].string] = pigeonCube.contents[3].contents[0].string
            # If the Date is stored correctly and the Title is wrong
            elif pigeonCube.contents[1].string is not None and pigeonCube.contents[3].string is None:
                MucusMembrane[pigeonCube.contents[1].string] = pigeonCube.contents[3].contents[0].string
            # If the Date is stored incorrectly and the Title is right
            elif pigeonCube.contents[1].string is None and pigeonCube.contents[3].string is not None:
                MucusMembrane[pigeonCube.contents[1].contents[0].string] = pigeonCube.contents[3].string
    return MucusMembrane

if __name__ == '__main__':
    mumbo = events()
    jumbo = headers()
    for item in jumbo:
        print(item.string)
    for i in mumbo:
        print(i, mumbo[i])
    print("Gabagoo")