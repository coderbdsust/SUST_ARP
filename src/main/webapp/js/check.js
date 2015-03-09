function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}


function isValidName(str) {

    if (str == null)
        return false;

    for (var i = 0; i < str.length; i++) {
        var charCode = str.charAt(i);
        if (charCode < "A" || (charCode > "Z" && charCode < "a") || charCode > "z") {
            return false;
        }
    }
    return true;
}

function isValidUnitName(evt) {

    var charCode = (evt.which) ? evt.which : event.keyCode
    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
        return true;
    else
        return false;
}

function resetResultQueryForm() {
    
    alert('Reset All Field');
    var startPosition = document.getElementsByName('startPosition');
    var endPosition = document.getElementsByName('endPosition');
    var pdfNo = document.getElementsByName('pdfNo');
    var priority = document.getElementsByName('priority');

    for (var i = 0; i < startPosition.length; i++) {
        startPosition[i].value="";
        endPosition[i].value = "";
        pdfNo[i].value = "";
        priority[i].value = "";
    }
    
}

function checkUnitGroupInput(event) {
    var operation = event.target.value;
    
}

function checkUnitGroupForm(event){
    
    var operation = $('operation').val();
    alert(operation);
    
    if(operation=="Add"){
        alert("Add Operation");
    }
    
    else if(operation=="Delete"){
        alert("Delete Operation");
    }
    
    return false;
    
}
