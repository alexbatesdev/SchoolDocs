import Scrape as s
import PySimpleGUI as gui

Neumont = {'BACKGROUND': '#1f1f1f',
             'TEXT': '#ffdd00',
             'INPUT': '#000000',
             'TEXT_INPUT': '#ffdd00',
             'SCROLL': '#505F69',
             'BUTTON': ('#1f1f1f', '#ffdd00'),
             'PROGRESS': ('#505F69', '#32414B'),
             'BORDER': 1, 'SLIDER_DEPTH': 0, 'PROGRESS_DEPTH': 0,
             }
# Add your dictionary to the PySimpleGUI themes
gui.theme_add_new('Neumont', Neumont)
# Switch your theme to use the newly added one
gui.theme('Neumont')

gui.popup_annoying("Proceed?")


layout = [[gui.Text("Web Scraper")],
          [gui.Text("This application is like a spider, it traverses the web to get you our academic calendar", key='-IN-')],
          [gui.Multiline(key="-OUTPUT-", expand_x=True, size=(20, None))],
          [gui.Button('Scrape'),
           gui.Button('Save'),
           gui.Button('Exit')]]

window = gui.Window('Web Scraper', layout, finalize = True)

while True:  # Event Loop
    event, values = window.read()
    print(event, values)
    if event == gui.WIN_CLOSED or event == "Exit":
        break
    if event == "Scrape":
        events = s.events()
        CheeseClumps = "Academic Calendar: "
        for item in events:
            CheeseClumps = CheeseClumps + "\n" + item + " --- " + events[item]
        window["-OUTPUT-"].update(value = CheeseClumps)
    if event == "Save":
        output = ""
        for line in values["-OUTPUT-"]:
            output = output + line
        with open('NAC.txt', 'w') as f:
            f.write(output)

window.close()