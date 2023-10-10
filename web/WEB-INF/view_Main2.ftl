<html lang="de">
   
<head>
    <style type="text/css">
        <#include "carSharerCss.css">
    </style>
    <title>View Main</title>
</head>
<body>

    <a href="ViewMain" class="wname">carSharer.com</a>
    <br><br>
    <ul>
        <li><a class="active" href="ViewMain">${lis1}</a></li>
        <li><a href="new_Drive">${lis2}</a></li>
        <li><a href="ViewSearch">${lis3}</a></li>
        <li class="inoutStyle"><a href=${inoutUrl} >${inout}</a></li>
    </ul>
    <br>
<#assign authed="${authed}">
<#if authed=="true">
    <fieldset>
        <legend> <h2>${title1}</h2> </legend>
        <table class="datatable">
            <th>${main5}</th> <th>${main1}</th> <th>${main2}</th> <th>${main3}</th> <th>${Status}</th> 
            <#list fahrt1 as fahrt>
                <tr>
                    <td><a href="drive?fid=${fahrt.fid}">${info}</a></td>
                    <td><img src="Display?transportmittel=${fahrt.transportmittel}" style="width:50px; height:50px;" /></td>
                    <td>${fahrt.startort}</td> <td>${fahrt.zielort}</td> <td>${fahrt.status}</td> 
                </tr>
            </#list>
        </table>
    </fieldset>
</#if>
   <fieldset>
        <legend> <h2> ${title2} </h2> </legend>

        <table class="datatable">
            <tr>
                <th>${main5}</th>
                <th>${main2}</th>
                <th>${main3}</th>
                <th>${main6}</th>
                <th>${main7}</th>
            </tr>
            <#list fahrt2 as fahrta>
            <tr>
                <td><a href="drive?fid=${fahrta.fid}">${info}</a></td>
                <td>${fahrta.startort}</td>
                <td>${fahrta.zielort}</td>
                <td>${fahrta.maxPlaetze}</td>
                <td>${fahrta.fahrtkosten}</td>
            </tr>
            </#list>
        </table>
    </fieldset> 

</body>    

</html>