  
  $(document).ready(function(){
    	loadAjaxUnitGroup();
    	loadAjaxQuota();
    	loadAjaxPositionType();
    });


    function loadAjaxUnitGroup() {
        console.log('Load Ajax Unit Group');
        $(document).ready(function(){
        	$.get("jsonServlet?methodName=unitGroup", function(response){
        		if(response!=null){
                    $('#unitGroupTbody').empty();
        			$.each(response, function(key, value){
        					var unitName = value['unit_name'];
        					var groupName = value['group_name'];
        					var unitGroupId = value['merit_list_name_id'];
        					console.log(unitGroupId + " " + unitName + " " + groupName);
        					var tbodyTr = "<tr><td>"+unitName+"</td>"+"<td>"+groupName+"</td>"+"<td><input type='checkbox' name='checkBox' value="+unitGroupId+"></td></tr>";
        					//console.log(tbodyTr);
        					$('#unitGroupTbody').append(tbodyTr);
        			});
        		}
        	});
        });

    }

    function loadAjaxPositionType() {
    	console.log('Load Ajax Position Type');
        $(document).ready(function(){
        	$.get("jsonServlet?methodName=positionType", function(response){
        		if(response!=null){
        			$('#positionTbody').empty();
        			$.each(response, function(key, value){
        					var posTypeName = value['postype_name'];
        					var posTypeId = value['postype_id'];
                            console.log(posTypeId + " " + posTypeName);
        					var tbodyTr = "<tr><td>"+posTypeName+"</td><td><input type='checkbox' name='checkBox' value="+posTypeId+"></td></tr>";
        				//	console.log(tbodyTr);
        					$('#positionTbody').append(tbodyTr);
        			});
        		}
        	});
        });
    }

    function loadAjaxQuota() {
        console.log('Load Ajax Quota');

        $(document).ready(function(){
        	$.get("jsonServlet?methodName=quota", function(response){
        		if(response!=null){
        			$('#quotaTbody').empty();
        			$.each(response, function(key, value){
        					var quotaName = value['quota_name'];
        					var quotaId = value['quota_id'];
                            console.log(quotaId + " " + quotaName);
        					var tbodyTr = "<tr><td>"+quotaName+"</td><td><input type='checkbox' name='checkBox' value="+quotaId+"></td></tr>";
        				//	console.log(tbodyTr);
        					$('#quotaTbody').append(tbodyTr);
        			});
        		}
        	});
        });
    }


  function unitGroupController(event){
    
    console.log('UnitGroup Controller');
    var opValue = event.target.value;
    console.log(opValue);

    $.ajax({
          url: "adminDataServlet?operation="+opValue,
          type: "post",
          data: $('#UGVForm, #UGIForm').serialize(),
        }).done(function(){
            console.log("Unit Group Data Saved!");
            console.log("Finished UnitGroup Task");
            $('#unitName').val('');
            $('#groupNo').val('');
            loadAjaxUnitGroup();
        });

    
  }	

  function positionTypeController(event){
       console.log("PosType Controller!");
       var opValue = event.target.value;
       console.log(opValue);

       $.ajax({
          url: "adminDataServlet?operation="+opValue,
          type: "post",
          data: $('#PVForm, #PIForm').serialize(),
          
        }).done(function(){
            console.log("PosType Data Saved!");
            console.log("Finished  PosType Task");
            loadAjaxPositionType();
            $('#positionType').val('');
        });
  }

  function quotaController(event){
    console.log('Quota Controller');
    var opValue = event.target.value;
    console.log(opValue);

    $.ajax({
          url: "adminDataServlet?operation="+opValue,
          type: "post",
          data: $('#QVForm, #QIForm').serialize(),
          
        }).done(function(){
            console.log("Quota Data Saved!");
            console.log("Finished Quota Task");
            loadAjaxQuota();
            $('#quotaName').val('');
        });
    
  }