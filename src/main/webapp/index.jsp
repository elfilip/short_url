<html>
<script>
	function getHash(url){
	xhttp= new XMLHttpRequest();
	xhttp.open('GET','http://localhost:8080/short_url/rest/save/'+url,false);
	xhttp.send();
	result=xhttp.responseText;
	return result;
	}
	function getUrl(hash){
	xhttp= new XMLHttpRequest();
	xhttp.open('GET','http://localhost:8080/short_url/rest/get/'+hash,false);
	xhttp.send();
	result=xhttp.responseText;
	return result;
	}
		function getAll(){
	xhttp= new XMLHttpRequest();
	xhttp.open('GET','http://localhost:8080/short_url/rest/get',false);
	xhttp.send();
	result=xhttp.responseText;
	return result;
	}
</script>
<body>
	<h2>URL Shortener</h2>	
	<b>Type your URL:</b> <input id="URL"><hr/>
	<label id="myLabel"></label><hr/>
	<button onclick="document.getElementById('myLabel').innerHTML=getHash(document.getElementById('URL').value);">Action</button>
	<hr/>
	<hr/>
	<b>Type your Hash:</b><input id="hash"><hr/>
	<label id="myLabel2"></label><hr/>
	<button onclick="document.getElementById('myLabel2').innerHTML=getUrl(document.getElementById('hash').value);">Action</button>
    
    <button onclick="window.location.replace('http://'+ getUrl(document.getElementById('hash').value));">Redirect</button>
	
	<hr/>
	<hr/>
	<b>All entries:</b>
	<button onclick="document.getElementById('myLabel3').innerHTML=getAll();">Action</button>
	<hr/>
	<label id="myLabel3"></label><hr/>
	</body>
</html>
