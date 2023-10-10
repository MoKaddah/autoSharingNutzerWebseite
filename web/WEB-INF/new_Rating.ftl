<!DOCTYPE html>
<html dir="ltr">
    <head>
        <style type="text/css">
            <#include "carSharerCss.css">
        </style>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="carSharerCss.css">
        <title>Fahrt Bewerten</title>
    </head>
    <body>
<center>
        <a href="ViewMain" class="wname">carSharer.com</a>
        <br><br>
    <form method="post" action="NewRating?action=NewRating&fid=${fid}">

        <label for="btext">${rateDic}</label><br>
        <textarea name="btext" id="btext" cols="30" rows="10" required></textarea>
        <br><br>
        
        <label for="bRating">${rate}:</label><br>
        <input type="radio" name="bRating" id="1" value="1">
        <label for="1">1</label>
        <input type="radio" name="bRating" id="2" value="2">
        <label for="2">2</label>
        <input type="radio" name="bRating" id="3" value="3">
        <label for="3">3</label>
        <input type="radio" name="bRating" id="4" value="4">
        <label for="4">4</label>
        <input type="radio" name="bRating" id="5" value="5">
        <label for="5">5</label>
        <br><br>

        <input type="submit" value=${add}>

    </form>
</center>
    </body>
</html>