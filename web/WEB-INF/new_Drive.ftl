<!DOCTYPE html>
<html lang="en">
<head>
    <style type="text/css">
        <#include "carSharerCss.css">
</style>
    <title>Fahrt erstellen</title>
</head>

<body>

    <a href="ViewMain" class="wname">carSharer.com</a>
    <br><br>
    <ul>
        <li><a href="ViewMain">${lis1}</a></li>
        <li><a class="active" href="new_Drive">${lis2}</a></li>
        <li><a href="ViewSearch">${lis3}</a></li>
        <li class="inoutStyle"><a href=${inoutUrl} >${inout}</a></li>
    </ul>
    <br>

    <!--<label class="brat">Fahrt erstellen</label> -->

    <form action="new_Drive" method="post" onsubmit="submit()">
        <p for="startort">${det3}
        <input type="text" id="startort" name="startort">
        </p>
        <div class="gap-10"></div>

        <p for="zielort">${det4}
        <input type="text" id="zielort" name="zielort">
        </p>
        <div class="gap-10"></div>

        <p for="maxPlaetze">${newD3}
        <input type="number" id="maxPlaetze" name="maxPlaetze" placeholder="Anzahl" required min="0" max="10">
        </p>
        <div class="gap-10"></div>

        <p for="FahrtKosten">${main7}
        <input type="number" id="FahrtKosten" name="FahrtKosten" placeholder="Anzahl" required min="0" max="10">
        </p>
        <div class="gap-10"></div>

        <p>${newD1}
        <input type="radio" id="auto" name="fahrt1" value="Auto">
        <label for="auto">Auto</label>
        <input type="radio" id="bus" name="fahrt1" value="Bus">
        <label for="bus">Bus</label>
        <input type="radio" id="kleintransporter" name="fahrt1" value="Kleintransporter">
        <label for="kleintransporter">${newD5}</label>
        </p>

        <p>${newD2}
            <input type="date" id="Fahrtdatum" name="Fahrtdatum">          
            <input type="time" id="fahrtzeitzeit" name="Fahrtzeit">
        </p>
        <label for="Description">${det5}</label><br>
        <textarea name="beschreibung" id="beschreibung" cols="40" rows="4" maxlength="50" ></textarea> <br> <br> 
        <center>
            <input  type="submit" value=${newD4}>
        </center>
    </form>

</body>
</html>