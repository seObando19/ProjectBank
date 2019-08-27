<?php 
	include 'conexion.php';
	//$cnx =  mysqli_connect("localhost","root","","bdusuarios");
	if (isset($_REQUEST['ident']) && isset($_REQUEST['clave']))
	{
		$ident=$_REQUEST['ident'];
		$clave=$_REQUEST['clave'];
		$registros=$cnx->query("select ident,email,clave,nombres from cliente where ident = '$ident' and clave = '$clave'");		
		$json = array();
		foreach ($registros as $fila) 
		{
			$json['datos'][]=$fila;
		}
		//pasar los datos del array a JSON con informacion o vacío
		echo json_encode($json);
	}
	else
	{
		echo "El ident y la clave son obligatorios";
	}

 ?>