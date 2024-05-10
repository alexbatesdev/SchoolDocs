# Basic Web Troubleshooting Guide

Have you ever found yourself writing HTML, CSS, and/or JavaScript and finding yourself going in circles trying to find and eradicate a bug somewhere in your code?
Well this nifty troubleshooting guide will help you with fixing common mistakes and finding those pesky bugs!

## Common Mistake 0: A typo as small as a bug

First things to look for any time your code isn't working are typos. They are tiny and 

## Common Mistake 1: The code is right but browser is not willing

If your code is right, but changes aren't being shown then you likely need to clear your cache!

This is because your browser saved the old version of the website to save time instead of getting the new one!

To clear your cache you can navigate somewhere deep in your settings and select an option, OR you could press Ctrl+Shift+R to clear your cache and refresh the page at the same time! :D

## Common Mistake 2: Does your code know about your code?

The second most common issue I see is people not linking their files together!

When you make a stylesheet to go alongside your html file you need to tell your html file where that stylesheet is.
The same goes for javascript as well!

The head of your html document should look something like this:
```html
<head>
	<title>My Super Cool Website</title>
	<link rel="stylesheet" src="./css/index.css" />
	<script src="./js/index.js"></script>
</head>
```

## Common Mistake 3: Premature JavaScript Execution

Is your javascript file linked in your header, cache is cleared, and yet your window.onload javascript shenanigans aren't working?

This is likely due to your javascript trying to run BEFORE your HTML has had a chance to render!
You can't change the background of a p tag if there is no p tag!

To fix this there are 2 options, the old way and the good way.

The old way was to put your script tag at the end of your html file so it executed last. The good way is by adding the `defer` keyword to your script tag for the same effect!
```html
<head>
	<title>My Super Cool Website</title>
	<link rel="stylesheet" src="./css/index.css" />
	<script src="./js/index.js" defer></script>
</head> <!-- woah right here -> ^^^^^^ <- defer! --!>
```

## The Real Troubleshooting Begins: A sneaky bug is in our midst.....

This point is the end of silly mistakes, and the beginning of actual troubleshooting. When troubleshooting `Inspect Element` is our best friend. Regardless of which browser you use, you should always be able to access the developer tools known as inspect element. Usually that menu is behind your right click menu, or a keyboard shortcut. This varies between browser, and if you don't know how to get to Inspect Element on yours go google it. 

Inspect element has 2 main sections we care about "Elements" and "Console".
Elements displays what HTML code your browser is rendering, and Console shows any output from JavaScript as well as other error messages.

## My Page Doesn't Look Right!

If our problem is visual then chances are it's an HTML or CSS problem.
To troubleshoot this we want to open inspect element and find exactly where our HTML went wrong.
If we forget to close an HTML tag or make some other typo, then the browser can omit that tag or render it in unpredictable ways. We want to use Inspect Element to look through the HTML code to try and find these kinds of differences between the final code and the code we expect.

Another common mistake of this type is incorrect CSS . If we told some text to be yellow but it isn't, we would want to use Inspect Element to see why. If we select our text with inspect element we can see exactly what CSS is being applied to it. This could illuminate if we have targeted the right piece of text, or if we are overriding the "yellow" command with another color later in out CSS file.

In the scenario where we can clearly see that our CSS is applying, but not how we like, we can actually edit the CSS live in the browser with inspect element. This instant feedback from our tweaking can be immensely helpful for troubleshooting and also just regular design tinkering.

## My Page Doesn't Behave Properly

This means you are probably messing around with JavaScript :) Yay for programming!

Troubleshooting JavaScript is an entirely different process, which is likely why so many beginners are afraid of it.

This is when we want to use Inspect Element to look at the console. 
The first thing you should do when running into problems with javascript is to check the console for errors.
If there is an error, read what it says, often times they aren't too jargon-y to understand. If it is, check the numbers in the error, they will lead you to the line of code that went awry. If the error doesn't lead to your code, and you don't understand what it's saying is wrong this is the time to go to google or chatGPT for some more info on your problem (You will do this a lot).

Any variable that you have declared at the root of your javascript file will be accessible in the console, this means you can view and edit it live while on your page. I don't use this a lot but I thought it could be helpful to mention.

If we aren't getting errors, but things aren't going how we'd like then it's time to bust out console.log() or alert().

I like to add one of these to the very first line of a method that I am unsure if it's being called. After that I will sprinkle in console logs all throughout my program with different labels. These help you understand the order that things are happening in. If you set the console logs to print out the data you are trying to manipulate it can help you have a fuller picture of where things are going wrong. I often time find myself with variables being equal to Null when I was expecting data, this troubleshooting step finds these problems.

With enough console.logs() and error googling you should be able to troubleshoot anything you come across