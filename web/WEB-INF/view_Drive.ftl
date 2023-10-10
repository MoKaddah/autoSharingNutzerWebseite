<!DOCTYPE html>
<html lang="en">
<head>
    <style type="text/css">
        <#include "carSharerCss.css">
    </style>
    <meta charset="UTF-8">
    <title>Fahrt Details</title>
</head>

<body>
<a href="ViewMain" class="wname">carSharer.com</a>
    <br><br>

<fieldset>
    <legend> <h2>${det1}</h2> </legend>
    <p>${det2} ${fahrt.fahrtdatum}</p>
    <p>${det3} ${fahrt.startort}</p>
    <p>${det4} ${fahrt.zielort}</p>
    <p>${main6} ${fahrt.freePlaces}</p>
    <p>${main7} ${fahrt.fahrtkosten}</p>
    <p>Status:${fahrt.status}</p>
    <p>${det5} ${fahrt.beschreibung}</p>   
</fieldset>
    
<fieldset>
    <legend> <h2>${det6}</h2></legend>
   
    <form method="POST" >
        <label for="anz">${det7}</label>
        <input type="number" id="anz" name="n"  required min="1" max="2"><br><br>
        <center>
            <button class="btn" type="submit" id="submit" formaction="drive?action=drive&fid=${fahrt.fid}&type=add " name="form1">${det8}</button>
            <#assign reserved="${reserved}">
            <#if reserved=="yes">
                <button class="btn" type="submit" id="submit" name="form1" formaction="drive?action=drive&fid=${fahrt.fid}&type=cancel ">Cancel</button>
            </#if>
        </center>
    </form>
    
</fieldset>

<fieldset>
    <legend> <h2>Rating</h2> </legend>
    Rating: ${Rating} / 5<br>
    <#list texts as text>
        ${name}: ${text}<br>
    </#list>
    <br>
    <a href="NewRating?fid=${fahrt.fid}" id="ratingURL">Add new rating</a>
</fieldset>

</body>
</html>