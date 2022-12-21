# Project 3 : GWack Slack Simulator

## Help Received

Please document any help you received in completing this lab. Note that the what you submit should be your own work. Refer to the syllabus for more details. 

[ANSWER HERE]

## Describe the OOP design of your GWack Slack Simulator

Please provide a short description of your programming progress

[My GWackClientGUI class is defined so that it extends jframe and implements ItemListener. 
It was made so that it would extend from jframe so that when the constructor was created, 
I was able to use the super() method to create a jframe and be able to use all of the jframe 
methods on GWackGUI. I also needed to be able to listen for when the Jcombobox for the themes 
changed, so I implemented the ItemListener class as well so that I would be able to overwrite 
the itemstatechanged method to change the theme when the jcombobox changes. This class exists 
with all of the panels and other privately defined graphical components. Any changes to these 
graphical elements are handled through public methods. (seteditable(), updatememberstxt()).

My Client class is the class that handles the client-side connections when connecting to a server 
using GWackClientGUI. Client extends thread so that I am able to run the client simultaneously with 
graphical components. Extending threrad allows me to write a separate run() function that involves 
first sending the handshake, then sending and recieving data. This class also has private class 
variables that can be altered through the call of methods. This class also implements the KeyListener 
class, which is used to overwrite the keyReleased(KeyEvent e) method. This was needed to enable 
sending messages using the enter key, while also being able to send messages with the send button.
]

## What additional features did you attempt and how can we test them

[
I added 4 different themes into my client gui
There is a light mode and dark mode
a mode called sun in your eyes which blinds the user with green and yellow
rgx(is a weapon skin name from a game i play called valorant) is a theme with blue black and gray color scheme,

You can test them through the dropdown menu in the GWackClientGUI
]


