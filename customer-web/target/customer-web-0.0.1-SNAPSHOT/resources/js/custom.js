function insertRow(val) {

	var sessionID = document.getElementById("sessionId");
	var tableElem = document.getElementById("tblAccounts");
	var rowLen = tableElem.rows.length;
	var row = tableElem.insertRow(rowLen - 1);

	row.insertCell(0).innerHTML = val.iban;
	row.insertCell(1).innerHTML = val.swift;
	row.insertCell(2).innerHTML = val.corporationID;
	row.insertCell(3).innerHTML = sessionID.value;
	row.insertCell(4).innerHTML = '<input type="button"  value = "Delete" >';
}

$(".delete").click(function(event) {
	var ID = $(this).attr("data");
	var tr = $(this).closest("tr");
	$.ajax({
		type : 'DELETE',
		url : "/customer-web" + "/delete/" + ID,
		dataType : "json",
		async : true,
		success : function() {
			tr.remove();
			console.log("Success");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("error");
		}
	});

});

$("#create").click(
		function(event) {

			var iban = document.getElementById("iban");
			var swift = document.getElementById("swift");

			if (validation(iban, swift) === true) {
				var jsobObj = {
					"iban" : iban.value,
					"swift" : swift.value
				};

				$.ajax({
					type : 'PUT',
					url : "/customer-web" + "/put/" + swift.value + "/"
							+ iban.value + "",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					data : JSON.stringify(jsobObj),
					async : true,
					success : function(data) {
						insertRow(data);
						document.getElementById("iban").value = '';
						document.getElementById("swift").value = '';
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(jqXHR.status + " " + jqXHR.responseText);
						alert("error");
					}
				});
			}

		});

function validation(iban, swift) {

	var dataTbl = document.getElementById("tblAccounts");
	for (r = 0; r < dataTbl.rows.length; r++) {
		var tableIban = dataTbl.rows[r].cells[0].innerHTML;
		if (iban.value === tableIban) {
			alert(iban + ": exists.");
			return false;
		}
	}

	var checkfields = checknull(iban, swift);
	if (!checkfields)
		return false;

	return true;
}

function checknull(iban, swift) {

	if (iban.value === 'undefined' || iban.value == null || iban.value == "") {
		alert("iban can not be empty");
		return false;
	}

	if (swift.value === 'undefined' || swift.value == null || swift.value == "") {
		alert("fill swift please");
		return false;
	}

	return true;
}
