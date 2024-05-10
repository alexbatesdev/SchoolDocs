import PySimpleGUI as sg
import os

# -----------------------------------
# Introducing PySimpleGUI
# https://pysimplegui.readthedocs.io/en/latest/

# PySimpleGUI code is simpler and shorter than writing directly
# to python GUI tools because because PySimpleGUI implements 
# much of the "boilerplate code" for you. 
# Additionally, interfaces are simplified to require as little 
# code as possible to get the desired result.

# Basic UI elements
# -----------------------------------

# PySimpleGUI uses basic elements 
#   buttons, labels, windows, etc.
# The basic building block of PySimpleGUI is the Window()

# -----------------------------------
# Creating a Window

'''
sg.theme('darkpurple3')
sg.Window(title='Basic Window', layout=[[]], margins=(400,200)).read()
'''

# There are many arguments you can give to the Window that
# will set its properties.

# .read() returns any events that are triggered in the
# Window() as a string as well as a values dictionary.

# -----------------------------------
# Adding elements

'''
# A nested list to hold the elements
layout = [[sg.Text('My simple app')], [sg.Button('Ok')]]

window = sg.Window('Demo', layout)

# create an event loop
while True:
    event, values = window.read()

    # program will end if the user closed the window
    # or presses the Ok button
    if event == 'Ok' or event == sg.WIN_CLOSED:
        break

window.close()
'''

# PySimpleGUI uses nested Python lists to lay out its 
# elements. In this case, you add a Text() element 
# and a Button() element. Then you create the window 
# and pass in your custom layout.

# A GUI needs to run inside a loop and wait for 
# and event (the user to do something). The event
# loop waits until .read() returns an event and values.

# -----------------------------------
# More about elements and events
# Text, Input, Button
'''
layout = [
    [sg.Text('This is text')],
    [sg.Text(key='-OUTPUT-', size=(40,1))],
    [sg.Input(key='-INPUT-')],    
    [sg.Button('Ok')]
]

window = sg.Window('Sample Elements', layout)

while True:
    event, values = window.read()
    
    # Write to the output text
    if event == 'Ok':
        window['-OUTPUT-'].update(
            f"This is your input:{values['-INPUT-']}",
            text_color='yellow')
    if event == sg.WINDOW_CLOSED:
        break

sg.Window.close()
'''

# The key= parameter is like a dictionary key. This key will be used
# as a dictionary later in the code.
# The size() parameter defines the size of the element in characters.
#    40 chars wide by 1 char high

# This is an element and function call reference for all elements:
# https://pysimplegui.readthedocs.io/en/latest/call%20reference/


# -----------------------------------
# How to Layout your elements
'''
# Horizontal layout (just put the elements in the same list)

# layout = [[sg.Button('Button 1'), sg.Button('Button 2')]]
# layout = [[sg.Button(f'OK {num}') for num in range(1, 6)]]

# Vertical layout (put the elements in different lists)

# layout = [
#     [sg.Button('Button 1')],
#     [sg.Button('Button 2')]
# ]

# Use Column() for complex layouts. It is a container element
# made for creating stacked sets of elements

# column_1 = [
#     [sg.Text('Column 1a')], [sg.Text('Column 1b')], [sg.Text('Column 1c')]
# ]
# column_2 = [
#     [sg.Text('Column 2a')], [sg.Text('Column 2b')]
# ]
# layout = [
#     [sg.Column(column_1), sg.Column(column_2)]
# ]

window = sg.Window("Demo", layout)
while  True:
    event, values = window.read()
    if event == sg.WIN_CLOSED:
        break
window.close()
'''

# -----------------------------------
# Putting it all together for an application

'''
column_1 = [    
    [sg.Text("Select a file"),
     sg.In(size=(25, 1), enable_events=True, key="-FOLDER-"),
     sg.FolderBrowse()],
    [sg.Listbox(
        values=[], 
        enable_events=True, 
        size=(40, 20), 
        key="-FILE LIST-")]
]
column_2 = [
    [sg.Text(size=(40, 3), key="-TEXT-")],
    [sg.Image(key="-IMAGE-")],
    [sg.Multiline(size=(30,5), key='-TEXTBOX-')]
]

layout = [
    [    
        sg.Column(column_1),
        sg.VSeparator(),
        sg.Column(column_2)
    ]
]

window = sg.Window("Demo", layout)
while True:
    event, values = window.read()
    
    # Folder was selected, make list of files in the folder
    if event == '-FOLDER-':
        folder = values['-FOLDER-']
        try:
            file_list = os.listdir(folder)
        except:
            file_list = []
        file_names = [
            f for f in file_list
            if os.path.isfile(os.path.join(folder, f)) and
            f.lower().endswith(('.png', '.gif'))
        ]
        window['-FILE LIST-'].update(file_names)

    # A file was chosen
    if event == '-FILE LIST-':  
        try:
            file_name = os.path.join(
                values['-FOLDER-'], 
                values['-FILE LIST-'][0])
            window['-TEXT-'].update(file_name)
            window['-IMAGE-'].update(filename=file_name)
        except:
            pass

    if event == sg.WIN_CLOSED:
        break
window.close()
'''