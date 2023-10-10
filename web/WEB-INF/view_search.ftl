<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fahrt Suche</title>
    <style type="text/css">
        <#include "carSharerCss.css">
        <#include "bootstrap.min.css">
    </style>
</head>
<body class="vs">
<div class="vs">
    <a href="ViewMain" class="wname" id="a1">carSharer.com</a>
    <br><br>
    <ul id="ul2">
        <li><a href="ViewMain">${lis1}</a></li>
        <li><a href="new_Drive">${lis2}</a></li>
        <li><a class="active" href="ViewSearch">${lis3}</a></li>
        <li class="inoutStyle"><a href=${inoutUrl} >${inout}</a></li>
    </ul>
    <br>
<div class="vsdiv1">
<form action="ViewSearch" method="post">
    <label for="start">${main2}:</label>
    <input type="text" name="start" id="ziel"> &ensp;

    <label for="ziel">${main3}:</label>
    <input type="text" name="ziel" id="ziel">
    <br><br>

    <label for="time">${newD2}</label>
    <input type="date" name="date" id="time"> &ensp; &ensp;

    <input type="submit" name="suche" id="suche" value=${lis3}>
</form>

<br>  
  
 <div class="row">
    <#list fahrts as fahrta>
        <div class="dataFrame col-lg-4 col-md-12">
            <center>
            <img src="Display?transportmittel=${fahrta.transportmittel}" style="width:80%; height:200px;"/>
            </center>
            ${det3} ${fahrta.startort}
            ${det4} ${fahrta.zielort}
            ${price}: ${fahrta.fahrtkosten} $
        </div>
    </#list>
</div>
</div>
</div>
</body>
</html>